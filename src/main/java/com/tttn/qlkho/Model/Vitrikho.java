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


    @ManyToOne(fetch = FetchType.LAZY)
    // @JsonIgnore
    @JoinColumn(name = "id_kho") // // thông qua khóa ngoại id
    private Kho kho;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JsonIgnore
    @JoinColumn(name = "id_sp") // // thông qua khóa ngoại id
    private SanPham sp;
}