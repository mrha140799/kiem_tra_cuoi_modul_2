package com.codegym.service;

import com.codegym.model.NhanVien;
import com.codegym.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamService {
    Iterable<Team> findAll();
    void save(Team team);
    void delete(Long id);
    Team findById(Long id);
}
