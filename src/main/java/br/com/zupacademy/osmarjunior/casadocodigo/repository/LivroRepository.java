package br.com.zupacademy.osmarjunior.casadocodigo.repository;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
