package br.com.empresa.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.empresa.demo.model.Usuario;
import br.com.empresa.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> listarUsuarios() {
		return repository.findAll();
	}

	public Usuario encontrarPorId(Long id) {
		return repository.findById(id).orElse(null);
	}

	public UserDetails encontrarPorNomeDeUsuario(String username) {
		return repository.findByUsername(username);
	}

	public Usuario salvarUsuario(Usuario user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return repository.save(user);
	}

	public void deletarUsuario(Long id) {
		repository.deleteById(id);
	}
}
