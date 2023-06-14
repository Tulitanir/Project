package edu.nechaev.project.services;

import edu.nechaev.project.dto.AuthenticationRequest;
import edu.nechaev.project.dto.AuthenticationResponse;
import edu.nechaev.project.dto.Role;
import edu.nechaev.project.dto.RoleBinding;
import edu.nechaev.project.models.Member;
import edu.nechaev.project.models.MemberRole;
import edu.nechaev.project.repositories.RoleBindingRepository;
import edu.nechaev.project.repositories.RoleRepository;
import edu.nechaev.project.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final RoleRepository roleRepository;
    private final RoleBindingRepository roleBindingRepository;
    private PasswordEncoder bCryptPasswordEncoder;
    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            String email = request.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));

            Member member = memberService.findByEmail(email);
            if (member == null) {
                throw new UsernameNotFoundException("Пользователя с email: " + email + " не существует");
            }

            String token = jwtTokenProvider.createToken(email, member.getMemberRoles());
            String refreshToken = jwtTokenProvider.createRefreshToken(email, member.getMemberRoles());

            return new AuthenticationResponse(member, token, refreshToken);
        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Неверный email или пароль");
        }
    }
    @Transactional
    public AuthenticationResponse register(Member member, MultipartFile multipartFile) {
        if (memberService.findByEmail(member.getEmail()) != null)
            throw new RuntimeException("Пользователь уже зарегистрирован");

        member.setActive(true);
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));

        if (multipartFile != null) {
            String filePath = "D:\\Project\\pfps\\" + member.getEmail() + ".jpg";

            File image = new File(filePath);
            try {
                multipartFile.transferTo(image);

                int newWidth = 250, newHeight = 250;

                BufferedImage inputImage = ImageIO.read(image);

                BufferedImage outputImage = new BufferedImage(newWidth, newHeight, inputImage.getType());

                Graphics2D graphics2D = outputImage.createGraphics();
                graphics2D.drawImage(inputImage, 0, 0, newWidth, newHeight, null);
                graphics2D.dispose();

                ImageIO.write(outputImage, "jpg", image);
            } catch (IOException e) {
                throw new RuntimeException(e.getLocalizedMessage());
            }

            member.setImage(filePath);
        }

        Member res = memberService.save(member);
        Role role = roleRepository.findByName("member");
        RoleBinding roleBinding = new RoleBinding();
        roleBinding.setMember(res.getId());
        roleBinding.setRole(role.getId());
        roleBindingRepository.save(roleBinding);

        MemberRole memberRole = new MemberRole();
        memberRole.setName("member");
        res.getMemberRoles().add(memberRole);

        String token = jwtTokenProvider.createToken(res.getEmail(), res.getMemberRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(res.getEmail(), res.getMemberRoles());

        return new AuthenticationResponse(res, token, refreshToken);
    }

}
