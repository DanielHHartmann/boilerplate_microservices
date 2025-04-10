package com.danielhharmann.service_reserva.application.service;

import com.danielhharmann.service_reserva.domain.model.Reserva;
import com.danielhharmann.service_reserva.infrastructure.config.RabbitConfig;
import com.danielhharmann.service_reserva.infrastructure.repository.ReservaRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class ReservaService {
    private final RabbitTemplate rabbitTemplate;
    private final ReservaRepository reservaRepository;
    private final WebClient webClientSala;

    public ReservaService(ReservaRepository reservaRepository, RabbitTemplate rabbitTemplate) {
        this.reservaRepository = reservaRepository;
        this.webClientSala = WebClient.create("http://service-sala:8080/salas");
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reserva salvarReserva(Reserva reserva) {
        Long usuarioId = reserva.getUsuarioId();
        Long salaId = reserva.getSalaId();

        if (!usuarioExiste(usuarioId)) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        if (!salaExiste(salaId)) {
            throw new IllegalArgumentException("Sala não encontrada!");
        }

        return reservaRepository.save(reserva);
    }

    private boolean usuarioExiste(Long usuarioId) {
        Boolean response = (Boolean) rabbitTemplate.convertSendAndReceive(
                RabbitConfig.USER_EXISTENCE_QUEUE, usuarioId);
        return Boolean.TRUE.equals(response);
    }

    private boolean salaExiste(Long salaId) {
        try {
            webClientSala.get()
                    .uri("/{id}", salaId)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException e) {
            return e.getStatusCode() != HttpStatus.NOT_FOUND;
        }
    }
}
