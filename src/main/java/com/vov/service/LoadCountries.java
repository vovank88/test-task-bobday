package com.vov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vov.dto.*;
import com.vov.model.*;
import com.vov.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class LoadCountries {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private CurrencyRepository currencyRepository;

  @Autowired
  private LanguageRepository languageRepository;

  @Autowired
  private RegionalBlocRepository regionalBlocRepository;

  @Autowired
  private TranslationRepository translationRepository;

  @Autowired
  private TopLevelDomainRepository topLevelDomainRepository;

  @Autowired
  private LatlngRepository latlngRepository;

  @PostConstruct
  @Transactional(rollbackFor = Exception.class)
  public void initCountries() {
    List<CountryDTO> countryDTOList = new ArrayList<>(getCountries());
    deleteSaveCountries(countryDTOList);
  }


  private List<CountryDTO> getCountries() {
    List<CountryDTO> countryList = null;
    ResponseEntity<String> response = restTemplate.getForEntity("https://restcountries.eu/rest/v2/all", String.class);

    if (response.getStatusCodeValue() == 200 && response.getBody() != null) {
      try {
        countryList = new ArrayList<>(objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, CountryDTO.class)));
      } catch (JsonProcessingException e) {
        log.error("Error deserialize json: {}.", e.getMessage());
      }
    }
    return countryList;
  }

  private Currency saveCurrency(CurrencyDTO currencyDTO) {
    Currency currency = new Currency();
    currency.setCode(currencyDTO.getCode());
    currency.setName(currencyDTO.getName());
    currency.setSymbol(currencyDTO.getSymbol());
    return currencyRepository.save(currency);
  }

  private Language saveLanguage(LanguageDTO languageDTO) {
    Language language = new Language();
    language.setIso1(languageDTO.getIso1());
    language.setIso2(languageDTO.getIso2());
    language.setName(languageDTO.getName());
    language.setNativeName(languageDTO.getNativeName());
    return languageRepository.save(language);
  }

  private RegionalBloc saveRegionalBloc(RegionalBlocDTO regionalBlocDTO) {

    RegionalBloc regionalBloc = new RegionalBloc();
    regionalBloc.setAcronym(regionalBlocDTO.getAcronym());
    regionalBloc.setName(regionalBlocDTO.getName());

    if (regionalBlocDTO.getOtherAcronyms() != null) {
      Set<OtherAcronyms> otherAcronymsSet = new HashSet<>();
      for (String str : regionalBlocDTO.getOtherAcronyms()) {
        otherAcronymsSet.add(new OtherAcronyms(null, str));
      }
      regionalBloc.setOtherAcronyms(otherAcronymsSet);
    }

    if (regionalBlocDTO.getOtherNames() != null) {
      Set<OtherNames> otherNamesSet = new HashSet<>();
      for (String str : regionalBlocDTO.getOtherNames()) {

        otherNamesSet.add(new OtherNames(null, str));
      }
      regionalBloc.setOtherNames(otherNamesSet);
    }

    return regionalBlocRepository.save(regionalBloc);
  }

  private Translation saveTranslation(TranslationDTO translationDTO) {
    Translation translation = new Translation();
    translation.setBr(translationDTO.getBr());
    translation.setDe(translationDTO.getDe());
    translation.setEs(translationDTO.getEs());
    translation.setFa(translationDTO.getFa());
    translation.setFr(translationDTO.getFr());
    translation.setHr(translationDTO.getHr());
    translation.setIt(translationDTO.getIt());
    translation.setJa(translationDTO.getJa());
    translation.setPt(translationDTO.getPt());
    translation.setNl(translationDTO.getNl());
    return translationRepository.save(translation);
  }


  private Country saveCountry(CountryDTO countryDTO) {
    Country country = new Country();
    country.setName(countryDTO.getName());
    country.setFlag(countryDTO.getFlag());
    country.setPopulation(countryDTO.getPopulation());

    country.setTranslations(saveTranslation(countryDTO.getTranslations()));

    if (countryDTO.getCurrencies() != null) {
      Set<Currency> currencySet = new HashSet<>();
      for (CurrencyDTO currencyDTO : countryDTO.getCurrencies()) {
        currencySet.add(saveCurrency(currencyDTO));
      }
      if (currencySet.size() > 0) {
        country.setCurrencies(currencySet);
      }
    }

    if (countryDTO.getLanguages() != null) {
      Set<Language> languageSet = new HashSet<>();
      for (LanguageDTO languageDTO : countryDTO.getLanguages()) {
        languageSet.add(saveLanguage(languageDTO));
      }
      if (languageSet.size() > 0) {
        country.setLanguages(languageSet);
      }
    }

    if (countryDTO.getRegionalBlocs() != null) {
      Set<RegionalBloc> regionalBlocSet = new HashSet<>();
      for (RegionalBlocDTO regionalBlocDTO : countryDTO.getRegionalBlocs()) {
        regionalBlocSet.add(saveRegionalBloc(regionalBlocDTO));
      }
      country.setRegionalBlocs(regionalBlocSet);
    }

    if (countryDTO.getTranslations() != null) {
      country.setTranslations(saveTranslation(countryDTO.getTranslations()));
    }

    if (countryDTO.getTopLevelDomain() != null) {
      Set<TopLevelDomain> topLevelDomainSet = new HashSet<>();
      for (String str : countryDTO.getTopLevelDomain()) {
        TopLevelDomain topLevelDomain = new TopLevelDomain();
        topLevelDomain.setTopLevelDomain(str);
        topLevelDomainSet.add(topLevelDomainRepository.save(topLevelDomain));
      }
      country.setTopLevelDomain(topLevelDomainSet);
    }

    if (countryDTO.getLatlng() != null) {
      Set<Latlng> latlngSet = new HashSet<>();
      for (BigDecimal nmbr : countryDTO.getLatlng()) {
        Latlng latlng = new Latlng();
        latlng.setLatlng(nmbr);
        latlngSet.add(latlngRepository.save(latlng));
      }
      country.setLatlng(latlngSet);
    }
    return countryRepository.save(country);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteAll() {
    countryRepository.deleteAll();
    currencyRepository.deleteAll();
    languageRepository.deleteAll();
    latlngRepository.deleteAll();
    topLevelDomainRepository.deleteAll();
    regionalBlocRepository.deleteAll();
    translationRepository.deleteAll();
  }

  private void deleteSaveCountries(List<CountryDTO> countryList) {
    deleteAll();
    for (CountryDTO country : countryList) {
      try {
        saveCountry(country);
        log.info("Country by name={} saved.", country.getName());
      } catch (Exception e) {
        log.error("Error save country by name={}.", country.getName());
        throw e;
      }

    }
  }

}
