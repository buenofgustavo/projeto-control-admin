package com.apicontroladmin.demo.services.servicesimpl;

import com.apicontroladmin.demo.domain.DTO.ComputadoresDTO;
import com.apicontroladmin.demo.domain.entidades.ComputadoresUnique;
import com.apicontroladmin.demo.services.servicesinterface.ComputadoresService;
import com.apicontroladmin.demo.services.servicesinterface.ComputadoresUniqueService;
import com.apicontroladmin.demo.services.servicesinterface.TransactionalComputadoresService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionalComputadoresServiceImpl implements TransactionalComputadoresService {

    @Autowired
    ComputadoresService computadoresService;

    @Autowired
    ComputadoresUniqueService computadoresUniqueService;

    @Override
    @Transactional
    public void criarComputadores(ComputadoresDTO computadoresDTO) {
        computadoresService.createComputadores(computadoresDTO);
        computadoresUniqueService.createComputadoresUnique(computadoresDTO);
    }
}
