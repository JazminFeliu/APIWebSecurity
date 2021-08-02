package com.example.apiwebsecurity.security.auth;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class MiSimpleGrantedAuthority implements GrantedAuthority {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer authority_id;
    @Column(nullable = false)
    private String role;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<AppUser> usuarios;

    public MiSimpleGrantedAuthority(String role){
        this.role = role;
    }

    public String getAuthority(){
        return this.role;
    }

    public MiSimpleGrantedAuthority() {
    }

    public Integer getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(Integer authority_id) {
        this.authority_id = authority_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<AppUser> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<AppUser> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "MiSimpleGrantedAuthority{" +
                "role='" + role + '\'' +
                '}';
    }
}
