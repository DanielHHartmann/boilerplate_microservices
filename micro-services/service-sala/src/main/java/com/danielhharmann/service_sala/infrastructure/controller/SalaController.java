package com.danielhharmann.service_sala.infrastructure.controller;

import com.danielhharmann.service_sala.application.service.SalaService;
import com.danielhharmann.service_sala.domain.model.Sala;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public List<Sala> listar() {
        return salaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarSala(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.buscaPorId(id));
    }


    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody Sala sala) {
        salaService.salvarSala(sala);
        return ResponseEntity.ok("Sala salva com sucesso!");
    }
}
