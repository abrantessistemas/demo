package br.com.empresa.demo.dto;

import br.com.empresa.demo.model.Beneficiario;

public record DocumentoDTO(String tipoDocumento, String descricao, Beneficiario beneficiario) {
}
