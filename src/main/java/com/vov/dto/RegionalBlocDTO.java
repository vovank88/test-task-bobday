package com.vov.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RegionalBlocDTO {
  private String acronym;
  private String name;
  private String[] otherAcronyms;
  private String[] otherNames;
}
