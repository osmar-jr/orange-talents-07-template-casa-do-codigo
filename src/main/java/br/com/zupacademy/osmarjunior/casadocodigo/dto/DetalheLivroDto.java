package br.com.zupacademy.osmarjunior.casadocodigo.dto;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Livro;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalheLivroDto {

    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroDePaginas;
    private String isbn;
    private String autor;
    private String descricaoAutor;
    private LocalDate dataLancamento;


    public DetalheLivroDto(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataLancamento = livro.getDataLancamento();

        this.autor = livro.getAutor().getNome();
        this.descricaoAutor = livro.getAutor().getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescricaoAutor() {
        return descricaoAutor;
    }
}
