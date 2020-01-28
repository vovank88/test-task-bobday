package com.vov.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String code;
  private String name;
  private String symbol;

}
