package com.codegym.service;

import com.codegym.model.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NhanVienService {
    Page<NhanVien> findAll(Pageable pageable);
    void save(NhanVien nhanVien);
    void delete(Long id);
    NhanVien findeById(Long id);
    List<NhanVien> searchNhanVien(String name);
}
