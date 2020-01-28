package com.vov.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class LanguageDTO {
  @JsonProperty("iso639_1")
  private String iso1;
  @JsonProperty("iso639_2")
  private String iso2;
  private String name;
  private String nativeName;
}
