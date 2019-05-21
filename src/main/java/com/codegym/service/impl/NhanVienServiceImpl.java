package com.codegym.service.impl;

import com.codegym.model.NhanVien;
import com.codegym.repository.NhanVienRepository;
import com.codegym.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class NhanVienServiceImpl implements NhanVienService {
    @PersistenceContext
    public EntityManager em;
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Override
    public Page<NhanVien> findAll(Pageable pageable) {
        return nhanVienRepository.findAll(pageable);
    }

    @Override
    public void save(NhanVien nhanVien) {
        nhanVienRepository.save(nhanVien);

    }

    @Override
    public void delete(Long id) {
        nhanVienRepository.delete(id);
    }

    @Override
    public NhanVien findeById(Long id) {
        return nhanVienRepository.findOne(id);
    }

    @Override
    public List<NhanVien> searchNhanVien(String name) {
        TypedQuery<NhanVien> query = em.createQuery("select  n from NhanVien n where n.hoTen like CONCAT('%',:keyword,'%') ",NhanVien.class);
        query.setParameter("keyword",name);
        return query.getResultList();
    }
}
