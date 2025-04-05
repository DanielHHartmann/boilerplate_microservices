package com.danielhharmann.service_reserva.infrastructure.controller;

import com.danielhharmann.service_reserva.application.service.ReservaService;
import com.danielhharmann.service_reserva.domain.model.Reserva;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("reservas")
public class ReservaController {
    final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<String> salvarReserva(@RequestBody Reserva reserva) {
        if (reserva.getUsuarioId() == null || reserva.getSalaId() == null) {
            return ResponseEntity.badRequest().body("Usuário e Sala são obrigatórios.");
        }

        if (reserva.getDataHora() == null) {
            return ResponseEntity.badRequest().body("Data da reserva é necessária.");
        }

        reservaService.salvarReserva(reserva);
        return ResponseEntity.ok("Reserva feita com sucesso!");
    }

}
