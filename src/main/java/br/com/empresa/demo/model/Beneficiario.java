package br.com.empresa.demo.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String telefone;
	private Date dataNascimento;
	private Date dataInclusao;
	private Date dataAtualizacao;
	@OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Documento> documentos;

}