package br.com.zupacademy.osmarjunior.casadocodigo.form;

import br.com.zupacademy.osmarjunior.casadocodigo.annotation.CpfOrCnpj;
import br.com.zupacademy.osmarjunior.casadocodigo.annotation.EstadoIsInPais;
import br.com.zupacademy.osmarjunior.casadocodigo.annotation.ExistsId;
import br.com.zupacademy.osmarjunior.casadocodigo.annotation.UniqueValue;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Cliente;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Estado;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Pais;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.osmarjunior.casadocodigo.repository.PaisRepository;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ClienteFormRequest {

    @NotBlank
    private String nome;

    @NotBlank @Email @UniqueValue(classDomain = Cliente.class, attributeName = "email")
    private String email;

    @NotBlank
    private String sobrenome;

    @NotBlank @UniqueValue(classDomain = Cliente.class, attributeName = "documento")
    @CpfOrCnpj
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull @ExistsId(classDomain = Pais.class, attributeName = "id")
    private Long paisId;

    private Long estadoId;

    @Deprecated
    public ClienteFormRequest() {
    }

    public ClienteFormRequest(@NotBlank String nome,
                              @NotBlank String email,
                              @NotBlank String sobrenome,
                              @NotBlank String documento,
                              @NotBlank String endereco,
                              @NotBlank String complemento,
                              @NotBlank String cidade,
                              @NotNull Long paisId,
                              @NotNull Long estadoId) {
        this.nome = nome;
        this.email = email;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
    }
    public ClienteFormRequest(@NotBlank String nome,
                              @NotBlank String email,
                              @NotBlank String sobrenome,
                              @NotBlank String documento,
                              @NotBlank String endereco,
                              @NotBlank String complemento,
                              @NotBlank String cidade,
                              @NotNull Long paisId) {
        this.nome = nome;
        this.email = email;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
    }

    /*
    * Getters para o EstadoPertenceAoPaisValidator
    * */
    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    /*
     * Setters para o Jackson, não está conseguindo injetar pelo construtor
     * */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public Cliente converterToModel(PaisRepository paisRepository, EstadoRepository estadoRepository) {

        Optional<Pais> optionalPais = paisRepository.findById(paisId);
        Assert.state(optionalPais.isPresent(), "País informado não existe.");

        Pais pais = optionalPais.get();

        if (estadoId == null) {
            return new Cliente(this.nome, this.email, this.sobrenome,
                    this.documento, this.endereco, this.complemento, this.cidade, pais);
        }

        Optional<Estado> optionalEstado = estadoRepository.findByIdAndPais(estadoId, pais);
        Assert.state(optionalEstado.isPresent(), "Estado não pertence ao país ID: "
                + pais.getId() );

        Estado estado = optionalEstado.get();

        return new Cliente(this.nome, this.email,
                this.sobrenome, this.documento,
                this.endereco, this.complemento,
                this.cidade, pais, estado);
    }
}
