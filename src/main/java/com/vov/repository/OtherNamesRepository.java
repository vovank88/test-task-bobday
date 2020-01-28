package com.vov.repository;

import com.vov.model.OtherNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherNamesRepository extends JpaRepository<OtherNames, Integer> {
}
