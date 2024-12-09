package com.centralti.tdm.services.servicesinterface;

import com.centralti.tdm.domain.usuarios.DTO.ColaboradorEventosDTO;
import com.centralti.tdm.domain.usuarios.DTO.LancamentoDpDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface LancamentoDpService {

    List<ColaboradorEventosDTO> importacaoLancamentosDp(MultipartFile files, String mes, Integer ano);
    void atualizacaoLancamentosDp(LancamentoDpDTO lancamentoDpDTO);

}
