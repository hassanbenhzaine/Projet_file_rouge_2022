package com.youcode.crm.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.youcode.crm.enums.USER_ROLE;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Builder
@AllArgsConstructor
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Employee firstname cannot be blank")
    private String firstName;

    @NotBlank(message = "Employee lastname cannot be blank")
    private String lastName;

    @Email
    private String email;

    @NotBlank(message = "Employee password cannot be blank")
    private String password;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "USER_ROLE")
    private USER_ROLE userRole;

    @NotBlank(message = "Employee cin cannot be blank")
    private String cin;

    private String sex;

    @Past
    private LocalDate birthdate;

    private Double salary;

    @Size(min = 10, max = 15)
    private String phone;

    private Boolean isLocked = false;
    private Boolean isEnabled = false;

    @ManyToOne(cascade = {REMOVE, MERGE})
    private Department department;

    @OneToMany(mappedBy = "author",cascade = {REMOVE, MERGE})
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = {REMOVE, MERGE})
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = {REMOVE, MERGE})
    @JsonIgnore
    private Set<Absenteeism> absenteeisms = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    public boolean isAdmin(){
        return this.getUserRole().equals(USER_ROLE.ADMIN);
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
