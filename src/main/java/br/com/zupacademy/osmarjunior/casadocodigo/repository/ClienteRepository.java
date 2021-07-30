package br.com.zupacademy.osmarjunior.casadocodigo.repository;

import br.com.zupacademy.osmarjunior.casadocodigo.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
