package br.com.empresa.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.demo.configuration.TokenService;
import br.com.empresa.demo.dto.AuthenticationDTO;
import br.com.empresa.demo.dto.LoginResponseDTO;
import br.com.empresa.demo.dto.RegisterDTO;
import br.com.empresa.demo.model.Usuario;
import br.com.empresa.demo.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((Usuario) auth.getPrincipal());

		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if (this.repository.findByUsername(data.login()) != null)
			return ResponseEntity.badRequest().build();

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());

		this.repository.save(newUser);

		return ResponseEntity.ok().build();
	}
}