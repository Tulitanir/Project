package edu.nechaev.project.services;

import edu.nechaev.project.dto.Member;
import edu.nechaev.project.dto.UserRole;

import java.util.List;

public interface MemberService {
    List<Member> getAll();

    Member findById(long id);

    Iterable<UserRole> findByRole(String role);

    Member save(Member member);

    String getImage(long id);

    Member findByEmail(String email);

    void delete(long id);
}
