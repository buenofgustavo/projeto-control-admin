package com.apicontroladmin.demo.services.servicesinterface;

import com.apicontroladmin.demo.domain.DTO.ComputadoresDTO;
import org.springframework.stereotype.Service;

@Service
public interface ComputadoresUniqueService {
    void createComputadoresUnique(ComputadoresDTO computadoresDTO);
}