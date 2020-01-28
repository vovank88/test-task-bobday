package com.vov.repository;

import com.vov.model.Country;
import com.vov.model.TopLevelDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

  Country findByNameIgnoreCase(String name);

  List<Country> findAllByTopLevelDomainIn(List<TopLevelDomain> topLevelDomainList);
}
