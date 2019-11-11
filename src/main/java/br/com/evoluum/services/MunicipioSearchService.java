package br.com.evoluum.services;

import br.com.evoluum.models.MunicipioDTO;

import java.util.List;

public interface MunicipioSearchService {

    String NOT_FOUND_MUNICIPIO = "Municipio não encontrado";

    int getIdCidadeByName(String name);

    List<MunicipioDTO> findAllMunicipios();
}
