package br.com.evoluum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MicroRegiao {

    private int id;
    private String nome;
    private MesoRegiao mesorregiao;
}
