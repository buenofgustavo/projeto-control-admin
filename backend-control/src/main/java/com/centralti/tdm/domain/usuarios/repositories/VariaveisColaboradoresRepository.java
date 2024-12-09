package com.centralti.tdm.domain.usuarios.repositories;

import com.centralti.tdm.domain.usuarios.entidades.VariaveisColaboradores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VariaveisColaboradoresRepository extends JpaRepository<VariaveisColaboradores, String> {
    Optional<VariaveisColaboradores> findVariaveisColaboradoresByCpfAndMesAndAno(String cpf, String mes, Integer ano);
    List<VariaveisColaboradores> findVariaveisColaboradoresByMesAndAno( String mes, Integer ano);
}
