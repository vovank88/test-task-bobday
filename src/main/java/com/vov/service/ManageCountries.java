package com.vov.service;

import com.vov.dto.*;
import com.vov.model.*;
import com.vov.repository.CountryRepository;
import com.vov.repository.TopLevelDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ManageCountries {

  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private TopLevelDomainRepository topLevelDomainRepository;

  private CountryDTO seiallizeCountry(Country country) {
    CountryDTO countryDTO = new CountryDTO();

    countryDTO.setName(country.getName());
    countryDTO.setFlag(country.getFlag());
    countryDTO.setPopulation(country.getPopulation());

    if (country.getTranslations() != null) {
      TranslationDTO translationDTO = new TranslationDTO();
      translationDTO.setBr(country.getTranslations().getBr());
      translationDTO.setDe(country.getTranslations().getDe());
      translationDTO.setEs(country.getTranslations().getEs());
      translationDTO.setFa(country.getTranslations().getFa());
      translationDTO.setFr(country.getTranslations().getFr());
      translationDTO.setHr(country.getTranslations().getHr());
      translationDTO.setIt(country.getTranslations().getIt());
      translationDTO.setJa(country.getTranslations().getJa());
      translationDTO.setPt(country.getTranslations().getPt());
      translationDTO.setNl(country.getTranslations().getNl());
      countryDTO.setTranslations(translationDTO);
    }

    if (country.getCurrencies() != null && country.getCurrencies().size() > 0) {
      Set<CurrencyDTO> currencyDTOSet = new HashSet<>();
      for (Currency currency : country.getCurrencies()) {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCode(currency.getCode());
        currencyDTO.setName(currency.getName());
        currencyDTO.setSymbol(currency.getSymbol());
        currencyDTOSet.add(currencyDTO);
      }
      countryDTO.setCurrencies(currencyDTOSet);
    }

    if (country.getLanguages() != null && country.getLanguages().size() > 0) {
      Set<LanguageDTO> languageDTOSet = new HashSet<>();
      for (Language language : country.getLanguages()) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setIso1(language.getIso1());
        languageDTO.setIso2(language.getIso2());
        languageDTO.setName(language.getName());
        languageDTO.setNativeName(language.getNativeName());
        languageDTOSet.add(languageDTO);
      }
      countryDTO.setLanguages(languageDTOSet);
    }

    if (country.getLatlng() != null && country.getLatlng().size() > 0) {
      BigDecimal[] arrayLatlng = new BigDecimal[country.getLatlng().size()];
      int i = 0;
      for (Latlng latlng : country.getLatlng()) {
        arrayLatlng[i] = latlng.getLatlng();
        i++;
      }
      countryDTO.setLatlng(arrayLatlng);
    }


    if (country.getTopLevelDomain() != null && country.getTopLevelDomain().size() > 0) {
      String[] arrayTopLevelDomain = new String[country.getTopLevelDomain().size()];
      int i = 0;
      for (TopLevelDomain topLevelDomain : country.getTopLevelDomain()) {
        arrayTopLevelDomain[i] = topLevelDomain.getTopLevelDomain();
        i++;
      }
      countryDTO.setTopLevelDomain(arrayTopLevelDomain);
    }

    if (country.getRegionalBlocs() != null && country.getRegionalBlocs().size() > 0) {
      Set<RegionalBlocDTO> regionalBlocDTOSet = new HashSet<>();
      for (RegionalBloc regionalBloc : country.getRegionalBlocs()) {
        RegionalBlocDTO regionalBlocDTO = new RegionalBlocDTO();
        regionalBlocDTO.setAcronym(regionalBloc.getAcronym());
        regionalBlocDTO.setName(regionalBloc.getName());

        if (regionalBloc.getOtherAcronyms() != null && regionalBloc.getOtherAcronyms().size() > 0) {
          String[] arrayOtherAcronyms = new String[regionalBloc.getOtherAcronyms().size()];
          int i = 0;
          for (OtherAcronyms otherAcronyms : regionalBloc.getOtherAcronyms()) {
            arrayOtherAcronyms[i] = otherAcronyms.getOtherAcronyms();
            i++;
          }
          regionalBlocDTO.setOtherAcronyms(arrayOtherAcronyms);
        }

        if (regionalBloc.getOtherNames() != null && regionalBloc.getOtherNames().size() > 0) {
          String[] arrayOtherNames = new String[regionalBloc.getOtherNames().size()];
          int i = 0;
          for (OtherNames otherNames : regionalBloc.getOtherNames()) {
            arrayOtherNames[i] = otherNames.getOtherNames();
            i++;
          }
          regionalBlocDTO.setOtherNames(arrayOtherNames);
        }

        regionalBlocDTOSet.add(regionalBlocDTO);
      }
      countryDTO.setRegionalBlocs(regionalBlocDTOSet);
    }


    return countryDTO;
  }

  @Transactional(rollbackFor = Exception.class)
  public CountryDTO getCountryByName(String name) {
    CountryDTO countryDTO = null;
    Country country = countryRepository.findByNameIgnoreCase(name);
    if (country != null) {
      countryDTO = seiallizeCountry(country);
    }
    return countryDTO;
  }

  @Transactional(rollbackFor = Exception.class)
  public List<CountryDTO> getCountryByDomain(String domain) {
    List<CountryDTO> countryDTOList = new ArrayList<>();

    List<TopLevelDomain> topLevelDomainList = topLevelDomainRepository.findAllByTopLevelDomainContaining(domain);
    if (topLevelDomainList != null && topLevelDomainList.size() > 0) {

      List<Country> countryList = countryRepository.findAllByTopLevelDomainIn(topLevelDomainList);
      if (countryList != null && countryList.size() > 0) {
        countryList.forEach(country -> countryDTOList.add(seiallizeCountry(country)));
      }
    }
    return countryDTOList;
  }
}
