package com.apicontroladmin.demo.domain.repositories;

import com.apicontroladmin.demo.domain.entidades.ComputadoresUnique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputadoresUniqueRepository extends JpaRepository<ComputadoresUnique, String> {
    ComputadoresUnique findByEnderecoMac(String EnderecoMac);
}
