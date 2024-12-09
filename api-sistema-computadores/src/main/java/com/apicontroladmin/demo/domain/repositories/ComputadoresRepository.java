package com.apicontroladmin.demo.domain.repositories;

import com.apicontroladmin.demo.domain.entidades.Computadores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputadoresRepository extends JpaRepository<Computadores, String> {
}
