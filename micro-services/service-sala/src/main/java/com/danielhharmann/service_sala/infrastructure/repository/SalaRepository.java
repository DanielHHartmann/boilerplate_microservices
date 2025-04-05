package com.danielhharmann.service_sala.infrastructure.repository;

import com.danielhharmann.service_sala.domain.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {}
