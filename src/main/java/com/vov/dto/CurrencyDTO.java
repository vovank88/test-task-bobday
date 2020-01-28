package com.vov.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class CurrencyDTO {
  private String code;
  private String name;
  private String symbol;
}
