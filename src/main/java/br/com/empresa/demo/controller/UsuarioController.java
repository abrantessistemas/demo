package br.com.empresa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.demo.model.Usuario;
import br.com.empresa.demo.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> users = service.listarUsuários();
        users.forEach(user -> user.setPassword(null));
        
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> encontrarUsuarioPorId(@PathVariable Long id) {
		Usuario user = service.encontrarPorId(id);
		return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario user) {
		Usuario novoUsuario = service.salvarUsuário(user);
		return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
		service.deletarUsuário(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
