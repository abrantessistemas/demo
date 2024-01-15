package br.com.empresa.demo.dto;

import java.util.Date;
import java.util.List;

import br.com.empresa.demo.model.Documento;

public record BeneficiarioDTO(String nome, String telefone, Date dataNascimento, List<Documento> documentos) {
}
