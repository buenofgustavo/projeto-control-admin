package com.apicontroladmin.demo.services.servicesimpl;

import com.apicontroladmin.demo.domain.DTO.ComputadoresDTO;
import com.apicontroladmin.demo.domain.entidades.ComputadoresUnique;
import com.apicontroladmin.demo.domain.repositories.ComputadoresUniqueRepository;
import com.apicontroladmin.demo.services.servicesinterface.ComputadoresUniqueService;
import com.apicontroladmin.demo.services.servicesinterface.LogComputadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputadoresUniqueServiceImpl implements ComputadoresUniqueService {

    @Autowired
    ComputadoresUniqueRepository computadoresUniqueRepository;

    @Autowired
    LogComputadoresService logComputadoresService;

    @Override
    public void createComputadoresUnique(ComputadoresDTO computadoresDTO) {
        ComputadoresUnique existingComputador = computadoresUniqueRepository.findByEnderecoMac(computadoresDTO.enderecoMac());

        if (existingComputador != null) {

            if (!existingComputador.getNomeComputador().equals(computadoresDTO.nomeComputador())){
                String mensagem = "Computador " + existingComputador.getNomeComputador() + " alterado para " + computadoresDTO.nomeComputador();
                String tipo = "alterarnome";
                logComputadoresService.createLogAutomaticoComputadores(mensagem, computadoresDTO.enderecoMac(), computadoresDTO.nomeComputador(), tipo);

            }

            existingComputador.setNomeUsuario(computadoresDTO.nomeUsuario());
            existingComputador.setNomeComputador(computadoresDTO.nomeComputador());
            existingComputador.setLocalizacao(computadoresDTO.localizacao());
            existingComputador.setMemoriaRam(computadoresDTO.memoriaRam());
            existingComputador.setCapacidadeArmazenamento(computadoresDTO.capacidadeArmazenamento());
            existingComputador.setMarca(computadoresDTO.marca());
            existingComputador.setModelo(computadoresDTO.modelo());
            existingComputador.setProcessador(computadoresDTO.processador());
            existingComputador.setSistemaOperacional(computadoresDTO.sistemaOperacional());
            existingComputador.setMakroInstalado(computadoresDTO.makroInstalado());
            existingComputador.setVersaoMakro(computadoresDTO.versaoMakro());
            computadoresUniqueRepository.save(existingComputador);
        } else {
            ComputadoresUnique newComputadoresUnique = new ComputadoresUnique(computadoresDTO);
            String mensagem = "Computador " + computadoresDTO.nomeComputador() + " cadastrado pela primeira vez ";
            String tipo = "primeirocadastro";
            newComputadoresUnique.setStatus("ALERTA");
            logComputadoresService.createLogAutomaticoComputadores(mensagem, computadoresDTO.enderecoMac(), computadoresDTO.nomeComputador(), tipo);
            computadoresUniqueRepository.save(newComputadoresUnique);
        }
    }

}
