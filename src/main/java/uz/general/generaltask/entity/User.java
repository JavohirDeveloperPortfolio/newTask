package uz.general.generaltask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements UserDetails {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String status;

    @CreatedDate
    public Time createdDate;

    @LastModifiedDate
    public Time updatedDate;

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

    public User(String name, String username, String password, String email, String phoneNumber,String status, List<Role> roles, Attachment attachment) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.roles = roles;
        this.attachment = attachment;
    }
}
