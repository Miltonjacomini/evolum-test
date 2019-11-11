package br.com.evoluum.services.impl;

import br.com.evoluum.exceptions.ResourceNotFoundException;
import br.com.evoluum.models.Municipio;
import br.com.evoluum.models.MunicipioDTO;
import br.com.evoluum.services.MunicipioSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class MunicipioSearchServiceImpl implements MunicipioSearchService {

    final Logger logger = LoggerFactory.getLogger(MunicipioSearchService.class);
    final String GET_ALL_MUNICIPIOS = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios";

    private RestTemplate rest;

    @Autowired MunicipioSearchServiceImpl(RestTemplate rest) {
        this.rest = rest;
    }

    @Cacheable("municipios")
    @Override
    public List<MunicipioDTO> findAllMunicipios() {
        logger.info("Access endpoint get all municipios");
        ResponseEntity<Municipio[]> result = this.rest.getForEntity(GET_ALL_MUNICIPIOS, Municipio[].class);

        if (result.getStatusCode() != HttpStatus.OK) {
            logger.error("Não foi possível encontrar todos os municípios");
            throw new ResourceNotFoundException(NOT_FOUND_MUNICIPIO);
        }

        return Stream.of(result.getBody()).map(Municipio::toDTO).collect(toList());
    }

    @Override
    public int getIdCidadeByName(String name) {
        logger.debug("Busca Cidade Id por Nome");
        List<MunicipioDTO> municipios = findAllMunicipios();

        Optional<MunicipioDTO> municipioFound =
                municipios.stream().filter(municipio -> municipio.getNomeCidade().equalsIgnoreCase(name)).findAny();

        if (!municipioFound.isPresent())
            return 0;

        return municipioFound.get().getIdMunicipio();
    }

}
