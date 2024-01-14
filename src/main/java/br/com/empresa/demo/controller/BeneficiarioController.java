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

import br.com.empresa.demo.model.Beneficiario;
import br.com.empresa.demo.services.BeneficiarioService;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

	@Autowired
	private BeneficiarioService service;

	@GetMapping
	public ResponseEntity<List<Beneficiario>> listarBeneficiarios() {
		List<Beneficiario> beneficiários = service.listarBeneficiarios();
		return new ResponseEntity<>(beneficiários, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Beneficiario> encontrarBeneficiarioPorId(@PathVariable Long id) {
		Beneficiario beneficiário = service.encontrarPorId(id);
		return beneficiário != null ? new ResponseEntity<>(beneficiário, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')") // Apenas usuários logados (ADMIN ou USER) podem criar beneficiários
	public ResponseEntity<Beneficiario> criarBeneficiario(@RequestBody Beneficiario beneficiário) {
		Beneficiario novoBeneficiario = service.salvarBeneficiario(beneficiário);
		return new ResponseEntity<>(novoBeneficiario, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')") // Apenas usuários com papel 'ADMIN' podem deletar beneficiários
	public ResponseEntity<Void> deletarBeneficiario(@PathVariable Long id) {
		service.deletarBeneficiario(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
