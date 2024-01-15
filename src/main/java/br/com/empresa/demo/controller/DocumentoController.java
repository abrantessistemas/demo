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

import br.com.empresa.demo.dto.DocumentoDTO;
import br.com.empresa.demo.mapper.DocumentoMapper;
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
		if (id == null || id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Documento documento = service.encontrarPorId(id);
		return documento != null ? new ResponseEntity<>(documento, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<Documento> criarDocumento(@RequestBody DocumentoDTO documentoDTO) {
		Documento documento = DocumentoMapper.INSTANCE.toDocumento(documentoDTO);

		if (documento == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		documento.setDataInclusao(new Date());
		documento.setDataAtualizacao(new Date());
		Documento novoDocumento = service.salvarDocumento(documento);

		return new ResponseEntity<>(novoDocumento, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<Documento> atualizarDocumento(@PathVariable Long id, @RequestBody Documento documento) {
		Documento documentoExistente = service.encontrarPorId(id);

		if (documentoExistente != null) {
			documentoExistente.setTipoDocumento(documento.getTipoDocumento());
			documentoExistente.setDescricao(documento.getDescricao());

			documentoExistente.setDataAtualizacao(new Date());

			Documento documentoAtualizado = service.salvarDocumento(documentoExistente);
			return new ResponseEntity<>(documentoAtualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deletarDocumento(@PathVariable Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		service.deletarDocumento(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
