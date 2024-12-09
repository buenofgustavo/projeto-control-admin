package com.centralti.tdm.domain.usuarios.repositories;

import com.centralti.tdm.domain.usuarios.entidades.LogColaboradores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogColaboradoresRepository extends JpaRepository<LogColaboradores, Long> {

    LogColaboradores findByCpfColaborador(String cpf);

}