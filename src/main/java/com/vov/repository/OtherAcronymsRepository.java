package com.vov.repository;

import com.vov.model.OtherAcronyms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherAcronymsRepository extends JpaRepository<OtherAcronyms, Integer> {
}
