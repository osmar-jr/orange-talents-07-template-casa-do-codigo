package br.com.zupacademy.osmarjunior.casadocodigo.dto;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Autor;

import java.util.List;
import java.util.stream.Collectors;

public class DetalheAutorDto {

    private String nome;
    private String descricao;

    public DetalheAutorDto(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }


    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<DetalheAutorDto> converter(List<Autor> autores) {
        return autores.stream().map(DetalheAutorDto::new).collect(Collectors.toList());
    }
}
