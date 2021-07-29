package br.com.zupacademy.osmarjunior.casadocodigo.dto;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Livro;

import java.util.List;
import java.util.stream.Collectors;

public class LivroDto {

    private Long id;
    private String titulo;

    public LivroDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public static List<LivroDto> conveterToDto(List<Livro> livros) {
        return livros.stream().map(LivroDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

}
