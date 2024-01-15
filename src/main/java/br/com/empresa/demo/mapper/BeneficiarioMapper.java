package br.com.empresa.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.empresa.demo.dto.BeneficiarioDTO;
import br.com.empresa.demo.model.Beneficiario;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BeneficiarioMapper {

	public static final BeneficiarioMapper INSTANCE = Mappers.getMapper(BeneficiarioMapper.class);

	public abstract Beneficiario toBeneficiario(BeneficiarioDTO beneficiarioDTO);

	public abstract BeneficiarioDTO toBeneficiarioDTO(Beneficiario beneficiario);
}
