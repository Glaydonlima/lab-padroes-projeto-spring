package one.digitalinnovation.gof.service.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteEventListener {

    @EventListener
    public void onClienteCriado(ClienteCriadoEvent event) {
        System.out.println("### OBSERVER: Cliente criado com sucesso! Nome: " + event.getCliente().getNome());
    }
}
