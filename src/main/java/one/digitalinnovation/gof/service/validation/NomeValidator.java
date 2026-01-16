package one.digitalinnovation.gof.service.validation;

import one.digitalinnovation.gof.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NomeValidator extends ClienteValidator {
    @Override
    public void validar(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome do cliente é obrigatório!");
        }
        next(cliente);
    }
}
