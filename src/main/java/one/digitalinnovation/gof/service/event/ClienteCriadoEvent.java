package one.digitalinnovation.gof.service.event;

import one.digitalinnovation.gof.model.Cliente;
import org.springframework.context.ApplicationEvent;

/**
 * Evento para o padrão <b>Observer</b> do Spring.
 * Notifica quando um novo cliente é registrado.
 */
public class ClienteCriadoEvent extends ApplicationEvent {
    private final Cliente cliente;

    public ClienteCriadoEvent(Object source, Cliente cliente) {
        super(source);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
