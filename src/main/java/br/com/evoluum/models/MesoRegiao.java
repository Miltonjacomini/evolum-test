package br.com.evoluum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MesoRegiao {

    private int id;
    private String nome;
    private Estado UF;
}
