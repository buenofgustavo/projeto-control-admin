package com.apicontroladmin.demo.domain.repositories;

import com.apicontroladmin.demo.domain.entidades.LogComputadores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogComputadoresRepository extends JpaRepository<LogComputadores, String> {
}
