package com.codegym.repository;

import com.codegym.model.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeamRepository  extends PagingAndSortingRepository<Team , Long> {
}
