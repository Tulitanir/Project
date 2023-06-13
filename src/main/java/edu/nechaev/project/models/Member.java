package edu.nechaev.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.nechaev.project.dto.MemberBinding;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table
@Data
public class Member {
    @Id
    private int id;
    private String name, surname, phone, pfpPath, email;

    @JsonIgnore
    private String password;

    @ReadOnlyProperty
    @MappedCollection(idColumn = "member", keyColumn = "role_id")
    private List<Role> roles = new ArrayList<>();
    private boolean isActive;
    @JsonIgnore
    @Transient
    private Set<MemberBinding> memberBindings;

}