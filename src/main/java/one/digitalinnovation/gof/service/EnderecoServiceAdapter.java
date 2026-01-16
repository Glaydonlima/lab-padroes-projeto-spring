package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Endereco;

/**
 * Interface <b>Adapter</b> para padronizar diferentes provedores de endereço.
 */
public interface EnderecoServiceAdapter {
    Endereco consultar(String cep);
}
