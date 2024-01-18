package br.com.empresa.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.demo.dto.BeneficiarioDTO;
import br.com.empresa.demo.mapper.BeneficiarioMapper;
import br.com.empresa.demo.model.Beneficiario;
import br.com.empresa.demo.services.BeneficiarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

	@Autowired
	private BeneficiarioService service;

	@GetMapping
	public ResponseEntity<List<Beneficiario>> listarBeneficiarios() {
		List<Beneficiario> beneficiarios = service.listarBeneficiarios();
		return new ResponseEntity<>(beneficiarios, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Beneficiario> encontrarBeneficiarioPorId(@PathVariable Long id) {
		Beneficiario beneficiario = service.encontrarPorId(id);
		return beneficiario != null ? new ResponseEntity<>(beneficiario, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<Beneficiario> criarBeneficiario(@Valid @RequestBody BeneficiarioDTO beneficiarioDTO) {
		Beneficiario beneficiario = BeneficiarioMapper.INSTANCE.toBeneficiario(beneficiarioDTO);

		beneficiario.setDataInclusao(new Date());
		beneficiario.setDataAtualizacao(new Date());
		Beneficiario novoBeneficiario = service.salvarBeneficiario(beneficiario);
		return new ResponseEntity<>(novoBeneficiario, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<Beneficiario> atualizarBeneficiario(@PathVariable Long id,
			@RequestBody Beneficiario beneficiario) {
		Beneficiario beneficiarioExistente = service.encontrarPorId(id);

		if (beneficiarioExistente != null) {
			beneficiarioExistente.setNome(beneficiario.getNome());
			beneficiarioExistente.setTelefone(beneficiario.getTelefone());
			beneficiarioExistente.setDataNascimento(beneficiario.getDataNascimento());

			beneficiarioExistente.setDataAtualizacao(new Date());

			Beneficiario beneficiarioAtualizado = service.salvarBeneficiario(beneficiarioExistente);
			return new ResponseEntity<>(beneficiarioAtualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deletarBeneficiario(@PathVariable Long id) {
		service.deletarBeneficiario(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
