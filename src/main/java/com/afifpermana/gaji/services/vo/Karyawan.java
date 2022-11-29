/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.afifpermana.gaji.services.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Apip
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Karyawan {
    private Long karyawanId;
    private String karyawanName;
    private String karyawanAlamat;
}
