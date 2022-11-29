/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.afifpermana.gaji.services.service;

import com.afifpermana.gaji.services.entity.Gaji;
import com.afifpermana.gaji.services.repository.GajiRepository;
import com.afifpermana.gaji.services.vo.Golongan;
import com.afifpermana.gaji.services.vo.Karyawan;
import com.afifpermana.gaji.services.vo.ResponseTemplateVO;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Apip
 */
@Service
public class GajiService {
    @Autowired
    private SimpleDateFormat formatTanggal;
    
    @Autowired
    private GajiRepository gajiRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public Gaji saveGaji(Gaji gaji){
        String tglSekarang = formatTanggal.format(new Date());   
        Golongan golongan = restTemplate.getForObject("http://localhost:8081/golongan/" 
                + gaji.getGolonganId(), Golongan.class);
        long totalGaji = golongan.getGolonganGajiPokok() + gaji.getTunjanganAnak() + gaji.getTunjanganSuamiIstri();
        gaji.setTotalGaji(totalGaji);
        gaji.setGajiTanggal(tglSekarang);
        return gajiRepository.save(gaji);
    }
    
    public ResponseTemplateVO getGaji(Long gajiId){
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Gaji gaji = gajiRepository.findByGajiId(gajiId);
        Karyawan karyawan = restTemplate.getForObject("http://localhost:8080/karyawan/" 
              + gaji.getKaryawanId(), Karyawan.class);
        Golongan golongan = restTemplate.getForObject("http://localhost:8081/golongan/" 
                + gaji.getGolonganId(), Golongan.class);
        vo.setGaji(gaji);
        vo.setKaryawan(karyawan);
        vo.setGolongan(golongan);
        return vo;
    }
}
