package com.tttn.qlkho.Model;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Ten_NCC")
    private String TenNCC;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "Address")
    private String Address;

    @Column(name = "SDT",nullable = false, unique = true)
    private String SDT;

    @OneToMany(mappedBy = "nhacungcap", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<CT_SP_NCC> ct_sp_ncc;
}