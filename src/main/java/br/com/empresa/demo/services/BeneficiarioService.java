package br.com.empresa.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.demo.model.Beneficiario;
import br.com.empresa.demo.repository.BeneficiarioRepository;

@Service
public class BeneficiarioService {
	@Autowired
	private BeneficiarioRepository repository;

	public List<Beneficiario> listarBeneficiarios() {
		return repository.findAll();
	}

	public Beneficiario encontrarPorId(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Beneficiario salvarBeneficiario(Beneficiario beneficiario) {
		return repository.save(beneficiario);
	}

	public void deletarBeneficiario(Long id) {
		repository.deleteById(id);
	}
}
