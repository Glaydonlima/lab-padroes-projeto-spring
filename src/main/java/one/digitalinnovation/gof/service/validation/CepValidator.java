package one.digitalinnovation.gof.service.validation;

import one.digitalinnovation.gof.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class CepValidator extends ClienteValidator {
    @Override
    public void validar(Cliente cliente) {
        if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null
                || cliente.getEndereco().getCep().length() != 8) {
            throw new RuntimeException("CEP inválido! O CEP deve conter 8 dígitos.");
        }
        next(cliente);
    }
}
