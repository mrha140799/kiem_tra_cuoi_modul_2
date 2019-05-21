package com.codegym.repository;


import com.codegym.model.NhanVien;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NhanVienRepository extends PagingAndSortingRepository<NhanVien , Long> {
}
