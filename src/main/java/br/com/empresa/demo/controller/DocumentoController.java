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

import br.com.empresa.demo.model.Documento;
import br.com.empresa.demo.services.DocumentoService;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

	@Autowired
	private DocumentoService service;

	@GetMapping
	public ResponseEntity<List<Documento>> listarDocumentos() {
		List<Documento> documentos = service.listarDocumentos();
		return new ResponseEntity<>(documentos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Documento> encontrarDocumentoPorId(@PathVariable Long id) {
		Documento documento = service.encontrarPorId(id);
		return documento != null ? new ResponseEntity<>(documento, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')") // Apenas usuários logados (ADMIN ou USER) podem criar documentos
	public ResponseEntity<Documento> criarDocumento(@RequestBody Documento documento) {
		Documento novoDocumento = service.salvarDocumento(documento);
		return new ResponseEntity<>(novoDocumento, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')") // Apenas usuários com papel 'ADMIN' podem deletar documentos
	public ResponseEntity<Void> deletarDocumento(@PathVariable Long id) {
		service.deletarDocumento(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
