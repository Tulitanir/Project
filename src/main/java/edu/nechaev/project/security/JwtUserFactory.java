package edu.nechaev.project.security;

import edu.nechaev.project.dto.Member;
import edu.nechaev.project.dto.MemberRole;
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
                member.getPhone(),
                member.getImage(),
                member.getEmail(),
                member.getPassword(),
                mapToGrantedAuthorities(member.getMemberRoles()),
                member.isActive()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<MemberRole> memberRoles) {
        return memberRoles.stream()
                .map(memberRole -> new SimpleGrantedAuthority(memberRole.getName()))
                .collect(Collectors.toList());
    }
}
