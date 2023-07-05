package com.tttn.qlkho.Model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CT_Kho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_kho") // // thông qua khóa ngoại id
    private Kho kho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_vitrikho") // // thông qua khóa ngoại id
    private Vitrikho vtKho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_ctpn") // // thông qua khóa ngoại id
    private CTPN ctphieunhap;

    @Column(name = "soluongnhapkho")
    private String soluongnhapkho;
}
