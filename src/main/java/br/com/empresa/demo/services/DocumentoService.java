package br.com.empresa.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.demo.model.Documento;
import br.com.empresa.demo.repository.DocumentoRepository;

@Service
public class DocumentoService {
	@Autowired
	private DocumentoRepository repository;

	public List<Documento> listarDocumentos() {
		return repository.findAll();
	}

	public Documento encontrarPorId(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Documento salvarDocumento(Documento documento) {
		return repository.save(documento);
	}

	public void deletarDocumento(Long id) {
		repository.deleteById(id);
	}
}
