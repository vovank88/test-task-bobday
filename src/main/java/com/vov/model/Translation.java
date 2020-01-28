package com.vov.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Translation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String de;
  private String es;
  private String fr;
  private String ja;
  private String it;
  private String br;
  private String pt;
  private String nl;
  private String hr;
  private String fa;
}
