package edu.nechaev.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table
@Data
@NoArgsConstructor
public class Member {
    @Id
    private int id;
    private String name, surname, phone, image, email;
    @JsonIgnore
    private String password;
    @ReadOnlyProperty
    @MappedCollection(idColumn = "member", keyColumn = "role_id")
    private List<MemberRole> memberRoles = new ArrayList<>();
    private boolean isActive;

    public Member(String name, String surname, String phone, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}