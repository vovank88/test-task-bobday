package com.vov.repository;

import com.vov.model.TopLevelDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopLevelDomainRepository extends JpaRepository<TopLevelDomain, Integer> {
  List<TopLevelDomain> findAllByTopLevelDomainContaining(String topLevelDomain);
}
