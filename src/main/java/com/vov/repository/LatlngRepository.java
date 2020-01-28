package com.vov.repository;

import com.vov.model.Latlng;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatlngRepository extends JpaRepository<Latlng, Integer> {
}
