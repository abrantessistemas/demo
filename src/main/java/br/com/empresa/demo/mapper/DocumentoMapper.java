package br.com.empresa.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.empresa.demo.dto.DocumentoDTO;
import br.com.empresa.demo.model.Documento;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentoMapper {

	public static final DocumentoMapper INSTANCE = Mappers.getMapper(DocumentoMapper.class);

	public abstract Documento toDocumento(DocumentoDTO documentoDTO);

	public abstract DocumentoDTO toDocumentoDTO(Documento documento);
}
