package edu.nechaev.project.services;

import edu.nechaev.project.dto.AuthenticationResponse;
import edu.nechaev.project.models.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {
    List<Member> getAll();

    Member findById(int id);
    Member save(Member member);

    String getImage(int id);

    Member findByEmail(String email);

    void delete(int id);
}
