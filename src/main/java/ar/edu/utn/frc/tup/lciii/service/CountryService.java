package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.CountryDto;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryService {


        private  RestTemplate restTemplate;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        private Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .code((String) countryData.get("cca3"))
                        .borders((List<String> )countryData.get("borders"))
                        .build();
        }


        private CountryDto mapToDTO(Country country) {
                return new CountryDto(country.getCode(), country.getName());
        }

        public List<CountryDto> getAllCountryDto (String name){
                if (name!=null){
                        return getFindName(name);
                }
                return getAllCountries().stream().map(this::mapToDTO).collect(Collectors.toList());
        }

        public List<CountryDto> getFindName (String name){
                List<Country> countries = this.getAllCountries();
                List<Country> filterCountries = countries.stream().filter(c->c.getName().equals(name)|| c.getCode().equals(name)).toList();
                List<CountryDto> filterCountryDto = filterCountries.stream().map(c->mapToDTO(c)).collect(Collectors.toList());
                return filterCountryDto;
        }

        public List<CountryDto> getAllCountriesByContinent(String continent) {
                List<Country> countries = this.getAllCountries();
                List<Country> filterCountries = countries.stream().filter(c->c.getRegion().equals(continent)).toList();
                List<CountryDto> filterCountryDto = filterCountries.stream().map(c->mapToDTO(c)).collect(Collectors.toList());
                return filterCountryDto;
        }

        public List<CountryDto> getAllCountriesByLanguage(String language) {
                List<Country> countries = this.getAllCountries();
                List<Country> filterCountries = countries.stream().filter(c->{
                        Map<String, String> languages = c.getLanguages();
                        if (languages ==null) {
                                return false;
                        }
                        if(languages.containsValue(language)){
                                return true;
                        } else{
                                return false;
                        }

                }).toList();
                List<CountryDto> filterCountryDto = filterCountries.stream().map(c->mapToDTO(c)).collect(Collectors.toList());
                return filterCountryDto;
        }

        public CountryDto getMostBorder (){
                List<Country> countries = this.getAllCountries();
                List<Country> filterCountries = countries.stream().sorted(Comparator.comparing(
                        Country->Country.getBorders().size())).toList();
                CountryDto filterCountryDto = mapToDTO(filterCountries.get(0));
                return filterCountryDto;
        }
}