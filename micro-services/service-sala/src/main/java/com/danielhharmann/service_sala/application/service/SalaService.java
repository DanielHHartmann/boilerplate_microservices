package com.danielhharmann.service_sala.application.service;

import com.danielhharmann.service_sala.domain.model.Sala;
import com.danielhharmann.service_sala.infrastructure.repository.SalaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public List<Sala> listarTodos() {
        return salaRepository.findAll();
    }

    public void salvarSala(Sala sala) {
        salaRepository.save(sala);
    }

    public Sala buscaPorId(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrada"));
    }
}

