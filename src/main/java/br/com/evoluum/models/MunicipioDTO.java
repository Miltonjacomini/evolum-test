package br.com.evoluum.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MunicipioDTO {

    @JsonIgnore
    private int idMunicipio;
    private int idEstado;
    private String siglaEstado;
    private String regiaoNome;
    private String nomeCidade;
    private String nomeMesorregiao;
    private String nomeFormatado;

    @Override
    public String toString() {
        return String.valueOf(this.idEstado).concat(";")
                      .concat(this.siglaEstado).concat(";")
                      .concat(this.regiaoNome).concat(";")
                      .concat(this.nomeCidade).concat(";")
                      .concat(this.nomeMesorregiao).concat(";")
                      .concat(this.nomeFormatado).concat(";");
    }

}
