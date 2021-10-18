package com.example.financeiro.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.financeiro.api.event.RecursoEvent;
import com.example.financeiro.api.model.Categoria;
import com.example.financeiro.api.repository.CategoriaRepository;

@RestController // Ja converte para JSON
@RequestMapping("/categorias") // Criando um identificador para rota.
public class CategoriaResource {

	@Autowired // procurar uma categoria para implantação de categoriaRepository.
	private CategoriaRepository categoriaRepository; // injetando classe CategoriaRepository

	/* Criando método Listar */
	@GetMapping // mapeamento do Get para URL "/categorias".
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and hasAuthority('SCOPE_read')" )
	public List<Categoria> pesquisarTodasCategorias() {
		return categoriaRepository.findAll();
	}

	/* Criando método Salvar */

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody Categoria categoria,
			HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);

		publisher.publishEvent(new RecursoEvent(this, response, categoriaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);

		// Método para recuperar um recurso, ele mostra a URL, irá ser refatorado ao
		// decorrer do projeto.

		// Quando adicionar o @Valid, irá validar a categoria que não pode ser null e
		// retornar o erro 400.
		// OBS, no campo que for notnull colocar anotação @Notnull. ex: Model -> nome.

		// este metodo retornará o que foi criado no BD para
		// quem está consumindo API.
		/*
		 * Ao inserir essa dependência no app.properties, exibirá uma msg de erro
		 * spring.jackson.deserialization.fail-on- unknown-properties=true
		 */

	}

	/*
	 * Criando método para buscar uma categoria, seria pra retornar erro 404 quando
	 * não encontrar uma rota.
	 */

	@GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and hasAuthority('SCOPE_read')")
	public ResponseEntity<Categoria> pesquisarCategoriaId(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}

//	outra forma de fazer o método para error 404.

//	@GetMapping("/{id}")
//	public ResponseEntity<Categoria>buscarPeloId(@PathVariable Long id) {
//		 Categoria categoria = categoriaRepository.findById(id).orElse(null);
//		 return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
//	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CATEGORIA') and hasAuthority('SCOPE_write')")
	public void deleteCategoriaId(@PathVariable Long id) {
		categoriaRepository.deleteById(id);
	}

}
