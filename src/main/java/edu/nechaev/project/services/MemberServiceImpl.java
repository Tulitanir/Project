package edu.nechaev.project.services;

import edu.nechaev.project.models.Member;
import edu.nechaev.project.dto.Role;
import edu.nechaev.project.dto.RoleBinding;
import edu.nechaev.project.repositories.MemberRepository;
import edu.nechaev.project.repositories.RoleBindingRepository;
import edu.nechaev.project.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final RoleBindingRepository roleBindingRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    @Transactional
    public Member register(Member member) {
        if (findByEmail(member.getEmail()) != null)
            throw new RuntimeException("Пользователь уже зарегистрирован");

        member.setActive(true);
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));

        Member res = memberRepository.save(member);
        Role role = roleRepository.findByName("member");
        RoleBinding roleBinding = new RoleBinding();
        roleBinding.setMember(res.getId());
        roleBinding.setRole(role.getId());
        roleBindingRepository.save(roleBinding);

        return res;
    }

    @Override
    public List<Member> getAll() {
        return (List<Member>) memberRepository.findAll();
    }

    @Override
    public Member findById(int id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void delete(int id) {
        memberRepository.deleteById(id);
    }
}
