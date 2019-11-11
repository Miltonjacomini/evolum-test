package br.com.evoluum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Estado {

    private int id;
    private String sigla;
    private String nome;
    private Regiao regiao;
}
