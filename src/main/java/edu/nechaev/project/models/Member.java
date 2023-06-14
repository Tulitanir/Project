package edu.nechaev.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table
@Data
@Builder
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
}