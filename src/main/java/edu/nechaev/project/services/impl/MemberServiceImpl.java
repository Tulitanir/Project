package edu.nechaev.project.services.impl;

import edu.nechaev.project.dto.Member;
import edu.nechaev.project.dto.UserRole;
import edu.nechaev.project.repositories.MemberRepository;
import edu.nechaev.project.repositories.UserRoleRepository;
import edu.nechaev.project.services.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final UserRoleRepository userRoleRepository;
    public List<Member> getAll() {
        return (List<Member>) memberRepository.findAll();
    }
    public Member findById(long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Iterable<UserRole> findByRole(String role) {
        return userRoleRepository.findByRole(role);
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public String getImage(long id) {
        return memberRepository.findImageById(id).orElseThrow(RuntimeException::new);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }

    public void delete(long id) {
        memberRepository.deleteById(id);
    }
}
