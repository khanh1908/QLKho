package com.tttn.qlkho.Model;
import java.sql.Date;
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
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TenSanPham", unique = true)
    private String TenSanPham;

    @Column(name = "HanSuDung")
    private Date HanSuDung;

    @Column(name = "NgaySanXuat")
    private Date NgaySanXuat;

    @Column(name = "TrangThai")
    private String TrangThai;

    @Column(name = "SoLuong")
    private int SoLuong;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "id_danhmuc") // // thông qua khóa ngoại id
    private DanhMuc danhmuc;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "id_vitrikho") // // thông qua khóa ngoại id
    private Vitrikho vitrikho;

    // @OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
    // @JsonIgnore
    // private Collection<CT_SP_NCC> ct_sp_ncc;
}