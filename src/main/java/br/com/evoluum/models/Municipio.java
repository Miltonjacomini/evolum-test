package br.com.evoluum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Municipio {

    private int id;
    private String nome;
    private MicroRegiao microrregiao;

    public static MunicipioDTO toDTO(Municipio municipio) {
        return MunicipioDTO.builder()
                .idMunicipio(municipio.getId())
                .idEstado(municipio.getIdEstado())
                .nomeCidade(municipio.getNomeCidade())
                .siglaEstado(municipio.getSiglaEstado())
                .regiaoNome(municipio.getRegiaoNome())
                .nomeMesorregiao(municipio.getMesorRegiaoNome())
                .nomeFormatado(municipio.getCidadeUfFormatado())
                .build();
    }

    private String getCidadeUfFormatado() {
        return this.getNome().concat(" / ").concat(this.getMicrorregiao().getMesorregiao().getUF().getSigla());
    }

    private String getRegiaoNome() {
        return this.getMicrorregiao().getMesorregiao().getUF().getRegiao().getNome();
    }

    private String getMesorRegiaoNome() {
        return this.getMicrorregiao().getMesorregiao().getNome();
    }

    private String getSiglaEstado() {
        return this.getMicrorregiao().getMesorregiao().getUF().getSigla();
    }

    private String getNomeCidade() {
        return this.getNome();
    }

    private int getIdEstado() {
        return this.getMicrorregiao().getMesorregiao().getUF().getId();
    }
}
