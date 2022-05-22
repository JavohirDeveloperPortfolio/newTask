package uz.general.generaltask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToOne
    private Attachment attachment;

    private boolean enabled = true;

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public User(String name, String username, String password, String email, String phoneNumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
