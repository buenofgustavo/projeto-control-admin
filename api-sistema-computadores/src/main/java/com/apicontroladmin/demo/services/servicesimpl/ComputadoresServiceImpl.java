package com.apicontroladmin.demo.services.servicesimpl;

import com.apicontroladmin.demo.domain.DTO.ComputadoresDTO;
import com.apicontroladmin.demo.domain.entidades.Computadores;
import com.apicontroladmin.demo.domain.entidades.ComputadoresUnique;
import com.apicontroladmin.demo.domain.repositories.ComputadoresRepository;
import com.apicontroladmin.demo.domain.repositories.ComputadoresUniqueRepository;
import com.apicontroladmin.demo.services.servicesinterface.ComputadoresService;
import com.apicontroladmin.demo.services.servicesinterface.ComputadoresUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputadoresServiceImpl implements ComputadoresService {

    @Autowired
    ComputadoresRepository computadoresRepository;

    @Override
    public void createComputadores(ComputadoresDTO computadoresDTO) {
        Computadores newComputadores = new Computadores(computadoresDTO);
        computadoresRepository.save(newComputadores);
    }
}
