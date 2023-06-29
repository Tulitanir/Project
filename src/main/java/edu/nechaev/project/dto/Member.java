package edu.nechaev.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table("member_subscription")
@Data
@NoArgsConstructor
public class Member {
    @Id
    private long id;
    private String name, surname, phone, image, email;
    @JsonIgnore
    private String password;
    @ReadOnlyProperty
    @MappedCollection(idColumn = "member", keyColumn = "role_id")
    private List<MemberRole> memberRoles = new ArrayList<>();
    private boolean isActive;
    private Timestamp expirationDate;

    public Member(String name, String surname, String phone, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Member(long id, String name, String surname, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}