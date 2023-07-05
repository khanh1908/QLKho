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
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TenSanPham")
    private String TenSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_danhmuc") // // thông qua khóa ngoại id
    private DanhMuc danhmuc;

    @OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<CT_SP_NCC> ct_sp_ncc;
}