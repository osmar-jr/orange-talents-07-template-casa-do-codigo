package br.com.zupacademy.osmarjunior.casadocodigo.form;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Estado;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Pais;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.PaisRepository;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class EstadoFormRequest {

    @NotBlank
    private String nome;
    private Long paisId;

    @Deprecated
    public EstadoFormRequest() {
    }

    public EstadoFormRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public EstadoFormRequest(@NotBlank String nome, @NotNull Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }

    /*
     *  Jackson só consegui injetar valores via set,
     * ainda não identifiquei o motivo do construtor não ser reconhecido
     * */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public Estado converterToModel(EstadoRepository estadoRepository, PaisRepository paisRepository) {

        if (paisId == null){
            return new Estado(this.nome);
        }

        Optional<Pais> optionalPais = paisRepository.findById(paisId);
        Assert.state(optionalPais.isPresent(), "País informado não foi encontrado.");

        Optional<Estado> optionalEstado = estadoRepository.findByNomeAndPaisId(this.nome, paisId);
        Assert.state(optionalEstado.isEmpty(), "O estado informado já existe para o país com id: " + paisId);

        Pais pais = optionalPais.get();
        return new Estado(this.nome, pais);
    }
}
