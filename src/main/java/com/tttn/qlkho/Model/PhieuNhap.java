package com.tttn.qlkho.Model;
import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    
    @Column(name = "ngay_nhap",nullable = false)
    private Date ngayNhap;

    @Column(name = "trang_thai")
    private int trangthai;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "id_user") // // thông qua khóa ngoại id
    private User user;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "id_ncc") // // thông qua khóa ngoại id
    private NhaCungCap nhacungcap;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JsonIgnore
    // @JoinColumn(name = "id_kho") // // thông qua khóa ngoại id
    // private Kho kho;

}