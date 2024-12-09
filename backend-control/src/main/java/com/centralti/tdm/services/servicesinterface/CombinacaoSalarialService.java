package com.centralti.tdm.services.servicesinterface;

import com.centralti.tdm.domain.usuarios.DTO.CombinacaoSalarialDTO;
import org.springframework.stereotype.Service;

@Service
public interface CombinacaoSalarialService {

    void createdCombinacao(CombinacaoSalarialDTO combinacaoSalarialDTO);

}
