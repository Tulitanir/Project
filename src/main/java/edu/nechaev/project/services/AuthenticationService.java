package edu.nechaev.project.services;

import edu.nechaev.project.dto.AuthenticationRequest;
import edu.nechaev.project.dto.AuthenticationResponse;
import edu.nechaev.project.models.Member;
import edu.nechaev.project.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            String email = request.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));

            Member member = memberService.findByEmail(email);
            if (member == null) {
                throw new UsernameNotFoundException("Пользователя с email: " + email + " не существует");
            }

            String token = jwtTokenProvider.createToken(email, member.getRoles());
            String refreshToken = jwtTokenProvider.createRefreshToken(email, member.getRoles());

            return new AuthenticationResponse(member, token, refreshToken);
        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Неверный email или пароль");
        }
    }
}
