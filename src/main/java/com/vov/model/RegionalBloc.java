package com.vov.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class RegionalBloc {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String acronym;
  private String name;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<OtherAcronyms> otherAcronyms;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<OtherNames> otherNames;
}
