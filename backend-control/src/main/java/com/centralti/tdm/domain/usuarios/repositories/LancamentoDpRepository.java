package com.centralti.tdm.domain.usuarios.repositories;

import com.centralti.tdm.domain.usuarios.entidades.GestaoAtivos;
import com.centralti.tdm.domain.usuarios.entidades.LancamentoDp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LancamentoDpRepository extends JpaRepository<LancamentoDp, Integer> {

    Optional<LancamentoDp> findLancamentoDpByCpfAndMesAndAno(String cpf, String mes, Integer ano);
    List<LancamentoDp> findAllByMesAndAno(String mes, Integer ano);
}


