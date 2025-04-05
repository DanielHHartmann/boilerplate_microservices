package com.danielhharmann.service_reserva.infrastructure.repository;

import com.danielhharmann.service_reserva.domain.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
