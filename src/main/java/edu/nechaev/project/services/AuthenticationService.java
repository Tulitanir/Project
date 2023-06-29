package edu.nechaev.project.services;

import edu.nechaev.project.dto.*;
import edu.nechaev.project.repositories.MemberPostRepository;
import edu.nechaev.project.repositories.RefreshStorageRepository;
import edu.nechaev.project.repositories.RoleBindingRepository;
import edu.nechaev.project.repositories.RoleRepository;
import edu.nechaev.project.security.AccessToken;
import edu.nechaev.project.security.JwtTokenProvider;
import edu.nechaev.project.security.TokenExpiredException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
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
    private RefreshStorageRepository refreshStorageRepository;
    private final MemberPostRepository memberPostRepository;

    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            String email = request.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));

            Member member = memberService.findByEmail(email);
            if (member == null) {
                throw new UsernameNotFoundException("Пользователя с email: " + email + " не существует");
            }

            AccessToken token = jwtTokenProvider.createToken(email, member.getMemberRoles());
            String refreshToken = jwtTokenProvider.createRefreshToken(email, member.getMemberRoles());
            refreshStorageRepository.save(new RefreshStorage(member.getEmail(), refreshToken));

            return new AuthenticationResponse(member, token.getToken(), refreshToken, token.getExpiresIn());
        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Неверный email или пароль");
        }
    }
    @Transactional
    public AuthenticationResponse register(MemberPost member, MultipartFile multipartFile) {
        if (memberService.findByEmail(member.getEmail()) != null)
            throw new RuntimeException("Пользователь уже зарегистрирован");

        member.setActive(true);
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));

        String imagePath = null;

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

            imagePath = filePath;
            member.setImage(imagePath);
        }

        MemberPost res2 = memberPostRepository.save(member);
        Member res = new Member(res2.getId(), res2.getName(), res2.getSurname(), res2.getPhone(), res2.getEmail(), res2.getPassword());
        res.setImage(imagePath);
        Role role = roleRepository.findByName("member");
        RoleBinding roleBinding = new RoleBinding();
        roleBinding.setMember(res2.getId());
        roleBinding.setRole(role.getId());
        roleBindingRepository.save(roleBinding);

        MemberRole memberRole = new MemberRole();
        memberRole.setName("member");
        res.getMemberRoles().add(memberRole);

        AccessToken token = jwtTokenProvider.createToken(res.getEmail(), res.getMemberRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(res.getEmail(), res.getMemberRoles());

        refreshStorageRepository.save(new RefreshStorage(res.getEmail(), refreshToken));

        return new AuthenticationResponse(res, token.getToken(), refreshToken, token.getExpiresIn());
    }

    public AuthenticationResponse refreshToken(@NonNull String refreshToken) {
        if (jwtTokenProvider.validateToken(refreshToken)) {
            String email = jwtTokenProvider.getUsername(refreshToken);
            String saveRefreshToken = refreshStorageRepository
                    .findById(email)
                    .orElseThrow(TokenExpiredException::new)
                    .getRefreshToken();
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                Member member = memberService.findByEmail(email);
                if (member == null) {
                    throw new UsernameNotFoundException("Пользователь с email: " + email + " не существует");
                }
                AccessToken accessToken = jwtTokenProvider.createToken(email, member.getMemberRoles());
                String refrToken = jwtTokenProvider.createRefreshToken(email, member.getMemberRoles());
                refreshStorageRepository.save(new RefreshStorage(email, refrToken));
                return new AuthenticationResponse(member, accessToken.getToken(), refrToken, accessToken.getExpiresIn());
            }
        }
        throw new TokenExpiredException("Конец");
    }
}
