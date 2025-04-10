package com.danielhharmann.service_usuario.infrastructure.listener;
import com.danielhharmann.service_usuario.infrastructure.config.RabbitConfig;
import com.danielhharmann.service_usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UsuarioExistenceListener {

    private final UsuarioRepository usuarioRepository;

    public UsuarioExistenceListener(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @RabbitListener(queues = RabbitConfig.USER_EXISTENCE_QUEUE)
    public boolean handleUserExistenceRequest(Long userId) {
        return usuarioRepository.existsById(userId);
    }
}