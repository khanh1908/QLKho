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
public class Vitrikho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Cot")
    private String Cot;

    @Column(name = "Hang")
    private String Hang;

    @Column(name = "Ke")
    private String Ke;

    @Column(name = "SoLuong")
    private int SoLuong;

    // @OneToMany(mappedBy = "VitriKho", cascade = CascadeType.ALL)
    // @JsonIgnore
    // private Collection<CT_Kho> ct_kho;
    // @OneToMany(mappedBy = "vitrikho", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng PhieuNhapKho (1 NHANVIEN co nhiều phiếu nhập kho)
    // @JsonIgnore
    // // MapopedBy trỏ tới private User user ở trong PhieuNhapKho.
    // private Collection<CT_Kho> ctkho;
}