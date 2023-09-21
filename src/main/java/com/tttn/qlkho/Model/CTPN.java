package com.tttn.qlkho.Model;

import java.math.BigDecimal;
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
public class CTPN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "id_PN") // // thông qua khóa ngoại id
    private PhieuNhap phieunhap;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "id_sp") // // thông qua khóa ngoại id
    private SanPham sanpham;

    @Column(name = "soluong")
    private int soluong;

     @Column(name = "gia")
    private BigDecimal gia;

    // @OneToMany(mappedBy = "ctpn", cascade = CascadeType.ALL)
    // @JsonIgnore
    // private Collection<CT_Kho> ct_kho;
    // @OneToMany(mappedBy = "ctpn", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // @JsonIgnore
    // // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    // private Collection<CT_Kho> ct_kho;
}