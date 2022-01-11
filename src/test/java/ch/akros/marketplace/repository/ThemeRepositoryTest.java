package ch.akros.marketplace.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import ch.akros.marketplace.AkrosMarketplacedataServiceApplication;
import ch.akros.marketplace.constants.IFieldTypeDefinition;
import ch.akros.marketplace.entity.FieldType;
import ch.akros.marketplace.entity.FieldTypeChoose;
import ch.akros.marketplace.entity.FieldTypeDefinition;
import ch.akros.marketplace.entity.Theme;

@SpringBootTest
@ContextConfiguration(classes = AkrosMarketplacedataServiceApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
public class ThemeRepositoryTest {
	@Autowired
	private ThemeRepository themeRepository;

	@Autowired
	private FieldTypeDefinitionRepository fieldTypeDefinitionRepository;

	private List<FieldTypeDefinition> fieldTypeDefinitionList;

	@Test
	public void allEntriesExistsInFieldTypeDefinition() throws IOException {
		fieldTypeDefinitionList = fieldTypeDefinitionRepository.findAll();

		Theme theme = new Theme();

		theme.setDescription("Unterkünfte");
		theme.setShortDescription("Unterkünfte");

		List<FieldType> fieldTypeList = new ArrayList<>();

		fieldTypeList.add(createFieldType(theme, "Titel", "Titel", true, false, 1, 1, 100,
				IFieldTypeDefinition.TEXT_SINGLE_LINE));
		fieldTypeList.add(createFieldType(theme, "Beschreibung", "Beschreibung der Unterkunft", true, false, 2, 1, 1000,
				IFieldTypeDefinition.TEXT_MULTI_LINE));
		fieldTypeList
				.add(createFieldType(theme, "Von", "Frei ab Datum", true, true, 3, 0, 0, IFieldTypeDefinition.DATE));
		fieldTypeList
				.add(createFieldType(theme, "Bis", "Frei bis Datum", false, false, 4, 0, 0, IFieldTypeDefinition.DATE));
		fieldTypeList.add(
				createFieldType(theme, "Zimmer", "Anzahl Zimmer", true, true, 5, 1, 10, IFieldTypeDefinition.NUMBER));
		fieldTypeList.add(createFieldType(theme, "Preis", "Preis der Unterkunft", true, true, 6, 1, 10000,
				IFieldTypeDefinition.PRICE));
		fieldTypeList.add(createFieldType(theme, "Grösse[qm]", "Grösse der Unterkunft in qm", true, true, 7, 1, 1000,
				IFieldTypeDefinition.NUMBER));

		FieldType fieldType = createFieldType(theme, "Art", "Art der Unterkunft", true, true, 8, 0, 0,
				IFieldTypeDefinition.CHOOSE_SINGLE_OPTION);

		List<FieldTypeChoose> fieldTypeChoosesList = new ArrayList<>();
		fieldTypeChoosesList.add(createFieldTypeChoose(fieldType, 1, "Zimmer"));
		fieldTypeChoosesList.add(createFieldTypeChoose(fieldType, 2, "Wohnung"));
		fieldTypeChoosesList.add(createFieldTypeChoose(fieldType, 3, "Haus"));
		fieldTypeChoosesList.add(createFieldTypeChoose(fieldType, 4, "Parkplatz"));

		fieldType.setFieldTypeChooses(fieldTypeChoosesList);
		fieldTypeList.add(fieldType);

		theme.setFieldTypes(fieldTypeList);

		themeRepository.save(theme);
	}

	private FieldType createFieldType(Theme theme, String shortDescription, String description, boolean required,
			boolean searchable, int sortNumber, int minValue, int maxValue, IFieldTypeDefinition fieldTypeDefinition) {
		FieldType fieldType = new FieldType();
		fieldType.setTheme(theme);
		fieldType.setDescription(description);
		fieldType.setShortDescription(shortDescription);
		fieldType.setRequired(required);
		fieldType.setSearchable(searchable);
		fieldType.setSortNumber(sortNumber);
		fieldType.setMinValue(minValue);
		fieldType.setMaxValue(maxValue);
		fieldType.setFieldTypeDefinition(fieldTypeDefinitionList.get(fieldTypeDefinition.ordinal()));
		fieldType.setFieldTypeChooses(null);
		return fieldType;
	}

	private FieldTypeChoose createFieldTypeChoose(FieldType fieldType, int sortNumber, String description) {
		FieldTypeChoose fieldTypeChoose = new FieldTypeChoose();
		fieldTypeChoose.setFieldType(fieldType);
		fieldTypeChoose.setSortNumber(sortNumber);
		fieldTypeChoose.setDescription(description);
		return fieldTypeChoose;
	}

}
