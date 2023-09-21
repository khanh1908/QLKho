package com.tttn.qlkho.Model;

import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "password",nullable = false)
	private String password;

    @Column(name = "ho")
    private String ho;

    @Column(name = "ten")
    private String ten;

    @Column(name = "Address")
    private String Address;

    @Column(name = "CCCD",nullable = true, unique = true)
    private String CCCD;

    @Column(name = "user_name",nullable = false, unique = true)
    private String userName;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(name = "Sdt", length = 12, unique = true)
    private String SDT;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @JsonIgnore
    // private Collection<PhieuNhap> PhieuNhap;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    // @JsonIgnore
    // private Collection<PhieuXuat> PhieuXuat;

    public boolean isAdmin() {
        return RoleEnum.ADMIN.equals(role);
    }

    public User orElseThrow(Object object) {
        return null;
    }
}
