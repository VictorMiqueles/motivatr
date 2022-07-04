package com.davmt.motivatr.repository;

import com.davmt.motivatr.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthoritiesRepository extends CrudRepository<Authority, Long> {
}