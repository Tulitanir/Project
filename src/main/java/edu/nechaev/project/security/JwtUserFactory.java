package edu.nechaev.project.security;

import edu.nechaev.project.models.Member;
import edu.nechaev.project.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public static JwtUser create(Member member) {
        return new JwtUser(
                member.getId(),
                member.getName(),
                member.getSurname(),
                member.getPatronymic(),
                member.getPhone(),
                member.getPfpPath(),
                member.getEmail(),
                member.getPassword(),
                mapToGrantedAuthorities(member.getRoles()),
                member.isActive()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
