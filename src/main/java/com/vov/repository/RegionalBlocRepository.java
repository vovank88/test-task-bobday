package com.vov.repository;

import com.vov.model.RegionalBloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionalBlocRepository extends JpaRepository<RegionalBloc, Integer> {
}
