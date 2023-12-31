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
public class PhieuXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "trang_thai")
    private int trangthai;

    @Column(name = "ngay_xuat",nullable = false)
    private Date ngayxuat;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "id_user") // // thông qua khóa ngoại id
    private User user;

}