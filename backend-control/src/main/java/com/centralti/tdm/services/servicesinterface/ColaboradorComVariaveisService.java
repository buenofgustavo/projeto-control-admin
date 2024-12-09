package com.centralti.tdm.services.servicesinterface;

import com.centralti.tdm.domain.usuarios.DTO.ColaboradorComVariaveisDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ColaboradorComVariaveisService {
    ColaboradorComVariaveisDTO findByColaborador(String cpf, String mes, Integer ano);
    List<ColaboradorComVariaveisDTO> findAllColaboradores(String mes, Integer ano);
    List<ColaboradorComVariaveisDTO> findAllColaboradoresByDepartamento(String departamento, String mes, Integer ano);
}
