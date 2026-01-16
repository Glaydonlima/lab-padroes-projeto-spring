package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.service.EnderecoServiceAdapter;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaCepAdapter implements EnderecoServiceAdapter {

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Endereco consultar(String cep) {
        return viaCepService.consultarCep(cep);
    }
}
