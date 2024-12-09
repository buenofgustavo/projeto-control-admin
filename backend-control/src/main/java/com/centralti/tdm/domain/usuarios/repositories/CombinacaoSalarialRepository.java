package com.centralti.tdm.domain.usuarios.repositories;

import com.centralti.tdm.domain.usuarios.entidades.CombinacaoSalarial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CombinacaoSalarialRepository extends JpaRepository<CombinacaoSalarial, String> {
    Optional<CombinacaoSalarial> findCombinacaoSalarialByCpf(String cpf);
    CombinacaoSalarial findByCpf(String cpf);
}

