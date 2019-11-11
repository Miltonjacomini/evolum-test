package br.com.evoluum.services.impl;

import br.com.evoluum.models.MunicipioDTO;
import br.com.evoluum.services.MunicipioSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class MunicipioCSVService {

    final static Logger logger = LoggerFactory.getLogger(MunicipioCSVService.class);

    private MunicipioSearchService searchService;

    @Autowired MunicipioCSVService(MunicipioSearchService searchService) {
        this.searchService = searchService;
    }

    public OutputStream getCSVInOutputStream() {
        logger.debug("Gerando CSV em formato OutputStream");
        return csvByDTO(searchService.findAllMunicipios());
    }

    private OutputStream csvByDTO(List<MunicipioDTO> municipios) {

        try {

            String firstLine = "idEstado;siglaEstado;regiaoNome;nomeCidade;nomeMesorregiao;nomeFormatado";

            StringBuilder contentCSV = new StringBuilder(firstLine);
            municipios.forEach(municipio -> {
                contentCSV.append("\r\n");
                contentCSV.append(municipio.toString());
            });

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(contentCSV.toString().getBytes());

            return byteArrayOutputStream;

        } catch (IOException e) {
            logger.error("Não foi possível criar o CSV de municipios");
            return null;
        }
    }

}
