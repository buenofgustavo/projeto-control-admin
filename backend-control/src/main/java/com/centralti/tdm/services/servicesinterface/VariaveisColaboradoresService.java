package com.centralti.tdm.services.servicesinterface;

import com.centralti.tdm.domain.usuarios.DTO.VariaveisColaboradoresDTO;
import org.springframework.stereotype.Service;

@Service
public interface VariaveisColaboradoresService {
    public void createdVariaveis(VariaveisColaboradoresDTO variaveisColaboradoresDTO);
    public VariaveisColaboradoresDTO FindVariavel(String cpf, String mes, Integer ano);
    public void updateVariaveis(VariaveisColaboradoresDTO variaveisColaboradoresDTO);
}
