package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.repository.ClienteRepository;
import one.digitalinnovation.gof.repository.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.EnderecoServiceAdapter;
import one.digitalinnovation.gof.service.event.ClienteCriadoEvent;
import one.digitalinnovation.gof.service.validation.CepValidator;
import one.digitalinnovation.gof.service.validation.NomeValidator;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}.
 * Esta classe evoluiu para incorporar múltiplos padrões de projeto pesquisados:
 * - <b>Singleton</b>: Tratada pelo Spring como um bean único.
 * - <b>Facade</b>: Oferece uma interface simplificada para o sistema.
 * - <b>Chain of Responsibility</b>: Validações encadeadas antes da
 * persistência.
 * - <b>Observer</b>: Notifica o sistema via eventos quando um cliente é criado.
 * - <b>Adapter</b>: Utiliza um adaptador para serviços de endereço externos.
 * 
 * @author GabrielWilliam (Estudante de Padrões de Projeto)
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private EnderecoServiceAdapter enderecoServiceAdapter;
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	// Injeção dos validadores para compor a Chain
	@Autowired
	private NomeValidator nomeValidator;
	@Autowired
	private CepValidator cepValidator;

	@PostConstruct
	public void init() {
		// Configurando a Chain of Responsibility
		nomeValidator.setProximo(cepValidator);
	}

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}

	@Override
	public void inserir(Cliente cliente) {
		// Acionando a Chain of Responsibility
		nomeValidator.validar(cliente);

		salvarClienteComCep(cliente);

		// Padrão Observer: Publicando evento de criação
		eventPublisher.publishEvent(new ClienteCriadoEvent(this, cliente));
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if (clienteBd.isPresent()) {
			nomeValidator.validar(cliente);
			salvarClienteComCep(cliente);
		}
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

	private void salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Usando o Adapter em vez de chamar o serviço diretamente (padrão
			// Adapter/Strategy)
			Endereco novoEndereco = enderecoServiceAdapter.consultar(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

}
