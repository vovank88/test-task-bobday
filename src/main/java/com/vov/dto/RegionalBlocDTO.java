package com.vov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionalBlocDTO {
  private String acronym;
  private String name;
  private String[] otherAcronyms;
  private String[] otherNames;
}
