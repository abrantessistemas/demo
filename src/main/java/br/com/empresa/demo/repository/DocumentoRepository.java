package br.com.empresa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.demo.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
	List<Documento> findAllByBeneficiario(Long beneficiario);

}
