package br.com.zupacademy.osmarjunior.casadocodigo.repository;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Estado;
import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findByNomeAndPaisId(String nome, Long paisId);

    Optional<Estado> findByIdAndPais(Long id, Pais pais);

    List<Estado> findByNomeAndPaisIdIsNull(String nome);
}
