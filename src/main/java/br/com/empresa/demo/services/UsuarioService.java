package br.com.empresa.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.empresa.demo.model.Usuario;
import br.com.empresa.demo.repository.UsuarioRepository;

public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> listarUsuários() {
		return repository.findAll();
	}

	public Usuario encontrarPorId(Long id) {
		return repository.findById(id).orElse(null);
	}

	public UserDetails encontrarPorNomeDeUsuário(String username) {
		return repository.findByUsername(username);
	}

	public Usuario salvarUsuário(Usuario user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Criptografa a senha antes de salvar
		return repository.save(user);
	}

	public void deletarUsuário(Long id) {
		repository.deleteById(id);
	}
}
