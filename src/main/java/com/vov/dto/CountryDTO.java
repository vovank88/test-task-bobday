package com.vov.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CountryDTO {
  private String name;
  private String[] topLevelDomain;
  private BigInteger population;
  private BigDecimal[] latlng;
  private Set<CurrencyDTO> currencies;
  private Set<LanguageDTO> languages;
  private TranslationDTO translations;
  private String flag;
  private Set<RegionalBlocDTO> regionalBlocs;
}
