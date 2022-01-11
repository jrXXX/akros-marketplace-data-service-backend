package ch.akros.marketplace.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ch.akros.marketplace.api.model.FieldTypeChooseDTO;
import ch.akros.marketplace.api.model.FieldTypeResponseDTO;
import ch.akros.marketplace.api.model.VWThemeResponseDTO;
import ch.akros.marketplace.entity.FieldType;
import ch.akros.marketplace.entity.FieldTypeChoose;
import ch.akros.marketplace.entity.VWTheme;
import ch.akros.marketplace.repository.FieldTypeRepository;
import ch.akros.marketplace.repository.VWThemeRepository;

@Service
public class ThemeService {
	@Autowired
	private VWThemeRepository vwThemeRepository;

	@Autowired
	private FieldTypeRepository fieldTypeRepository;

	public List<VWThemeResponseDTO> listThemes() {
		return vwThemeRepository.findAll(Sort.by(Sort.Direction.ASC, "description")).stream()
				.map(this::toVWThemeResponseDTO).collect(Collectors.toList());
	}

	// TODO : mapper
	private VWThemeResponseDTO toVWThemeResponseDTO(VWTheme vwTheme) {
		VWThemeResponseDTO result = new VWThemeResponseDTO();
		result.setThemeId(vwTheme.getThemeId());
		result.setDescription(vwTheme.getDescription());
		result.setOfferCount(vwTheme.getOfferCount());
		result.setSearchCount(vwTheme.getSearchCount());
		return result;
	}

	public List<FieldTypeResponseDTO> listFieldTypes(Long themeId) {
		return fieldTypeRepository.listFieldTypesOfTheme(themeId).stream().map(this::toFieldTypeResponseDTO)
				.collect(Collectors.toList());
	}

	// TODO : mapper
	private FieldTypeResponseDTO toFieldTypeResponseDTO(FieldType fieldType) {
		FieldTypeResponseDTO result = new FieldTypeResponseDTO();
		result.setFieldTypeId(fieldType.getFieldTypeId());
		result.setFieldTypeDefinitionId(fieldType.getFieldTypeDefinition().getFieldTypeDefinitionId());
		result.setFieldTypeDefinitionDescription(fieldType.getFieldTypeDefinition().getDescription());
		result.setDescription(fieldType.getDescription());
		result.setShortDescription(fieldType.getShortDescription());
		result.setMinValue(fieldType.getMinValue());
		result.setMaxValue(fieldType.getMaxValue());
		result.setSearch(fieldType.isSearch());
		result.setOffer(fieldType.isOffer());
		result.setFieldTypeChooses(
				fieldType.getFieldTypeChooses().stream().sorted((e1, e2) -> e1.getSortNumber() - e2.getSortNumber())
						.map(this::toFieldTypeChoosesDTO).collect(Collectors.toList()));
		return result;
	}

	private FieldTypeChooseDTO toFieldTypeChoosesDTO(FieldTypeChoose fieldTypeChoose) {
		FieldTypeChooseDTO result = new FieldTypeChooseDTO();
		result.setFieldTypeChooseId(fieldTypeChoose.getFieldTypeChooseId());
		result.setDescription(fieldTypeChoose.getDescription());
		result.setSortNumber(fieldTypeChoose.getSortNumber());
		return result;
	}
}
