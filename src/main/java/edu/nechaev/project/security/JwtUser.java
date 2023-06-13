package edu.nechaev.project.security;

import edu.nechaev.project.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {
    private final int id;
    private final String name, surname, patronymic, phone, pfpPath, email, password;
    private final Collection<GrantedAuthority> grantedAuthorities;
    private final boolean isActive;

    public JwtUser(int id, String name, String surname, String patronymic, String phone, String pfpPath, String email, String password, Collection<GrantedAuthority> grantedAuthorities, boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.pfpPath = pfpPath;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public String getPfpPath() {
        return pfpPath;
    }

    public String getEmail() {
        return email;
    }
}
