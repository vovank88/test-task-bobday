package com.vov.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

@Data
@Entity
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<TopLevelDomain> topLevelDomain;

  private BigInteger population;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Latlng> latlng;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Currency> currencies;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Language> languages;

  @OneToOne(fetch = FetchType.EAGER)
  private Translation translations;

  private String flag;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<RegionalBloc> regionalBlocs;
}
