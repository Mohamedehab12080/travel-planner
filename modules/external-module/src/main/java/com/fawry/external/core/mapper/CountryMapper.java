// external-module/src/main/java/com/fawry/external/core/mapper/CountryMapper.java
package com.fawry.external.core.mapper;

import com.fawry.external.model.dto.CountryDTO;
import com.fawry.external.model.vto.CountrySuggestionVTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CountryMapper {

    @Mapping(target = "name", expression = "java(countryDTO.getName().getCommon())")
    @Mapping(target = "code", source = "countryDTO.cca2")
    @Mapping(target = "capital", source = "countryDTO.capitals", qualifiedByName = "extractFirstCapital")
    @Mapping(target = "currencyName", source = "countryDTO.currencies", qualifiedByName = "extractCurrencyName")
    @Mapping(target = "currencyCode", source = "countryDTO.currencies", qualifiedByName = "extractCurrencyCode")
    @Mapping(target = "flagUrl", source = "countryDTO.flags.png")
    @Mapping(target = "languages", source = "countryDTO.languages", qualifiedByName = "extractLanguages")
    @Mapping(target = "timezones", ignore = true)
//    @Mapping(target = "timezones", source = "countryDTO.timezones", qualifiedByName = "extractTimezones")
    @Mapping(target = "alreadyExists", source = "alreadyExists")
    CountrySuggestionVTO toCountrySuggestionVTO(CountryDTO countryDTO,boolean alreadyExists);

    @Mapping(target = "name", expression = "java(countryDTO.getName().getCommon())")
    @Mapping(target = "code", source = "countryDTO.cca2")
    @Mapping(target = "capital", source = "countryDTO.capitals", qualifiedByName = "extractFirstCapital")
    @Mapping(target = "currencyName", source = "countryDTO.currencies", qualifiedByName = "extractCurrencyName")
    @Mapping(target = "currencyCode", source = "countryDTO.currencies", qualifiedByName = "extractCurrencyCode")
    @Mapping(target = "flagUrl", source = "countryDTO.flags.png")
    @Mapping(target = "languages", source = "countryDTO.languages", qualifiedByName = "extractLanguages")
    @Mapping(target = "timezones", ignore = true)
//    @Mapping(target = "timezones", source = "countryDTO.timezones", qualifiedByName = "extractTimezones")
    @Mapping(target = "alreadyExists", source = "alreadyExists")
    CountrySuggestionVTO toCountrySuggestionVTOWithExists(CountryDTO countryDTO, boolean alreadyExists);

    @Named("extractFirstCapital")
    default String extractFirstCapital(List<String> capitals) {
        if (capitals == null || capitals.isEmpty()) {
            return null;
        }
        return capitals.get(0);
    }

    @Named("extractCurrencyName")
    default String extractCurrencyName(Map<String, CountryDTO.CurrencyDTO> currencies) {
        if (currencies == null || currencies.isEmpty()) {
            return null;
        }
        CountryDTO.CurrencyDTO firstCurrency = currencies.values().iterator().next();
        return firstCurrency != null ? firstCurrency.getName() : null;
    }

    @Named("extractCurrencyCode")
    default String extractCurrencyCode(Map<String, CountryDTO.CurrencyDTO> currencies) {
        if (currencies == null || currencies.isEmpty()) {
            return null;
        }
        return currencies.keySet().iterator().next();
    }

    @Named("extractLanguages")
    default String extractLanguages(Map<String, String> languages) {
        if (languages == null || languages.isEmpty()) {
            return null;
        }
        return String.join(", ", languages.values());
    }

    @Named("extractTimezones")
    default String extractTimezones(List<String> timezones) {
        if (timezones == null || timezones.isEmpty()) {
            return null;
        }
        return String.join(", ", timezones);
    }
}