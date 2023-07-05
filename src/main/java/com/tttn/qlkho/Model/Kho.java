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
public class Kho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TenKho")
    private String TenKho;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "Address")
    private String Address;

    @Column(name = "SDT", length = 12,nullable = false, unique = true)
    private String SDT;

    // // @OneToMany(mappedBy = "Kho", cascade = CascadeType.ALL)
    // // @JsonIgnore
    // // private Collection<CT_Kho> ct_kho;
    // @OneToMany(mappedBy = "kho", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // @JsonIgnore
    // // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    // private Collection<CT_Kho> ct_Kho;
}