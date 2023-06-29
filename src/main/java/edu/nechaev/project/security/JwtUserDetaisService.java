package edu.nechaev.project.security;

import edu.nechaev.project.dto.Member;
import edu.nechaev.project.services.MemberService;
import edu.nechaev.project.services.impl.MemberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetaisService implements UserDetailsService {

    private final MemberService memberServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberServiceImpl.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(member);

        return jwtUser;
    }
}
