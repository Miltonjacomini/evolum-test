package br.com.evoluum.controllers;

import br.com.evoluum.exceptions.ResourceNotFoundException;
import br.com.evoluum.services.MunicipioSearchService;
import br.com.evoluum.services.impl.MunicipioCSVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
public class MunicipioController {

    final Logger logger = LoggerFactory.getLogger(MunicipioController.class);

    protected static final String URL_GET_JSON = "/estados";
    protected static final String URL_GET_JSON_IN_CSV = "/estados/csv";
    protected static final String URL_GET_JSON_BY_NAME = "/estados/{nameCidade}";

    private MunicipioCSVService municipioCSVService;
    private MunicipioSearchService municipioSearchService;

    @Autowired MunicipioController(MunicipioCSVService municipioCSVService,
                                   MunicipioSearchService municipioSearchService) {
        this.municipioCSVService = municipioCSVService;
        this.municipioSearchService = municipioSearchService;
    }

    @GetMapping(value = URL_GET_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllMunicipios() {
        logger.debug("Gerando lista de municipios em Objetos");
        return new ResponseEntity<>(municipioSearchService.findAllMunicipios(), HttpStatus.OK);
    }

    @GetMapping(URL_GET_JSON_BY_NAME)
    public ResponseEntity<?> getIdEstadoByName(@PathVariable @Valid @NotNull String nameCidade) {

        int idCidadeByName = municipioSearchService.getIdCidadeByName(nameCidade);

        if (idCidadeByName == 0)
            throw new ResourceNotFoundException("Cidade não encontrada pelo nome, "+  nameCidade);

        return new ResponseEntity<>(idCidadeByName, HttpStatus.OK);
    }

    @GetMapping(value = URL_GET_JSON_IN_CSV)
    public void findAllMunicipiosInCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print(municipioCSVService.getCSVInOutputStream());
    }
}
