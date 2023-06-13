package edu.nechaev.project.services;

import edu.nechaev.project.models.Member;

import java.util.List;

public interface MemberService {
    Member register(Member member);

    List<Member> getAll();

    Member findById(int id);

    Member findByEmail(String email);

    void delete(int id);
}
