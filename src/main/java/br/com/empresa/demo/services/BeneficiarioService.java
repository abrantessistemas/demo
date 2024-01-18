package br.com.empresa.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.demo.model.Beneficiario;
import br.com.empresa.demo.repository.BeneficiarioRepository;

@Service
public class BeneficiarioService {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;

	public List<Beneficiario> listarBeneficiarios() {
		return beneficiarioRepository.findAll();
	}

	public Beneficiario encontrarPorId(Long id) {
		Optional<Beneficiario> beneficiarioOptional = beneficiarioRepository.findById(id);
		return beneficiarioOptional
				.orElseThrow(() -> new NoSuchElementException("Beneficiário não encontrado com ID: " + id));
	}

	public Beneficiario salvarBeneficiario(Beneficiario beneficiario) {
		return beneficiarioRepository.save(beneficiario);
	}

	public void deletarBeneficiario(Long id) {
		beneficiarioRepository.deleteById(id);
	}
}
