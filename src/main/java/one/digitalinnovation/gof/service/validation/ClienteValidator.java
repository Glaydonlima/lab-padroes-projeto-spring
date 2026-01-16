package one.digitalinnovation.gof.service.validation;

import one.digitalinnovation.gof.model.Cliente;

/**
 * Padrão <b>Chain of Responsibility</b> para validar regras de negócio de um
 * Cliente.
 * 
 * @author GlaydonLima
 */
public abstract class ClienteValidator {
    protected ClienteValidator proximo;

    public void setProximo(ClienteValidator proximo) {
        this.proximo = proximo;
    }

    public abstract void validar(Cliente cliente);

    protected void next(Cliente cliente) {
        if (proximo != null) {
            proximo.validar(cliente);
        }
    }
}
