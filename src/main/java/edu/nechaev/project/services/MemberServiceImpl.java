package edu.nechaev.project.services;

import edu.nechaev.project.dto.AuthenticationResponse;
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
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public List<Member> getAll() {
        return (List<Member>) memberRepository.findAll();
    }

    @Override
    public Member findById(int id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public String getImage(int id) {
        return memberRepository.findImageById(id).orElseThrow(RuntimeException::new);
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
