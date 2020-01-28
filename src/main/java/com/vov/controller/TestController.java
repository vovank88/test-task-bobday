package com.vov.controller;

import com.vov.dto.CountryDTO;
import com.vov.service.LoadCountries;
import com.vov.service.ManageCountries;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private LoadCountries loadCountries;

  @Autowired
  private ManageCountries manageCountries;

  @PostMapping("/getCountryByName")
  @ApiOperation(value = "Get countries by name case ignore")
  public CountryDTO getCountryByName(@ApiParam("Country name") @RequestBody String name) {
    return manageCountries.getCountryByName(name);
  }

  @DeleteMapping("/deleteAll")
  @ApiOperation(value = "Delete all countries")
  public void deleteAll() {
    loadCountries.deleteAll();
  }

  @GetMapping("/reload")
  @ApiOperation(value = "Reload all countries")
  public void reloadCpountries() {
    loadCountries.initCountries();
  }

  @PostMapping("/getCountryByDomain")
  @ApiOperation(value = "Get countries by domain")
  public List<CountryDTO> getCountryByDomain(@ApiParam("Country domain (like support)") @RequestBody String domain) {
    return manageCountries.getCountryByDomain(domain);
  }
}
