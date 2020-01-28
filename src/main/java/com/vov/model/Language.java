package com.vov.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Language {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String iso1;
  private String iso2;
  private String name;
  private String nativeName;
}
