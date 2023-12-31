package com.tttn.qlkho.Model;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CTPX {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "id_PX") // // thông qua khóa ngoại id
    private PhieuXuat phieuxuat;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "id_sp") // // thông qua khóa ngoại id
    private SanPham sanpham;

    @Column(name = "soluong")
    private int soluong;

    @Column(name = "gia")
    private BigDecimal gia;
}