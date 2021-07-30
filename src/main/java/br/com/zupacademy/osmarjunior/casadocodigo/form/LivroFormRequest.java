package br.com.zupacademy.osmarjunior.casadocodigo.form;

import br.com.zupacademy.osmarjunior.casadocodigo.annotation.ExistsId;
import br.com.zupacademy.osmarjunior.casadocodigo.annotation.UniqueValue;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Autor;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Categoria;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Livro;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.CategoriaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class LivroFormRequest {

    @NotNull @NotEmpty @NotBlank
    @UniqueValue(classDomain = Livro.class, attributeName = "titulo")
    private String titulo;

    @NotNull @NotEmpty @NotBlank
    @Length(max = 500)
    private String resumo;

    @NotNull @NotEmpty @NotBlank
    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroDePaginas;

    @NotNull @NotEmpty @NotBlank
    @UniqueValue(classDomain = Livro.class, attributeName = "isbn")
    private String isbn;

    @NotNull
    @Future @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataLancamento;

    @NotNull
    @ExistsId(classDomain = Categoria.class, attributeName = "id")
    private Long categoriaId;

    @NotNull
    @ExistsId(classDomain = Autor.class, attributeName = "id")
    private Long autorId;

    @Deprecated
    public LivroFormRequest() {
    }

    public LivroFormRequest(@NotNull @NotBlank String titulo,
                            @NotNull @NotBlank @Length(max = 500) String resumo,
                            @NotNull @NotBlank String sumario,
                            @NotNull @Min(20) BigDecimal preco,
                            @NotNull @Min(100) Integer numeroDePaginas,
                            @NotNull @NotBlank String isbn,
                            @NotNull @Future LocalDate dataLancamento,
                            @NotNull Long categoriaId,
                            @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    /*
    * Setter para Jackson, pois o mesmo não deserializa a data no formato especificado
    * */
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Livro convertToModel(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        Optional<Autor> optionalAutor = autorRepository.findById(autorId);
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(categoriaId);

        Assert.state(optionalAutor.isPresent(), "Autor com ID " + autorId + " não existe.");
        Assert.state(optionalCategoria.isPresent(), "Categoria com ID " + categoriaId + " não existente.");

        Autor autor = optionalAutor.get();
        Categoria categoria = optionalCategoria.get();

        return new Livro(titulo, resumo, sumario, preco, numeroDePaginas,
                isbn, dataLancamento, categoria, autor);
    }
}
