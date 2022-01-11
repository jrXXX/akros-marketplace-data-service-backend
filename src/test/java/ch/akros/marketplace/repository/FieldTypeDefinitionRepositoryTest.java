package ch.akros.marketplace.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import ch.akros.marketplace.AkrosMarketplacedataServiceApplication;

@SpringBootTest
@ContextConfiguration(classes = AkrosMarketplacedataServiceApplication.class)
@TestPropertySource(locations = "classpath:application-test.yml")
public class FieldTypeDefinitionRepositoryTest {
	@Autowired
	private FieldTypeDefinitionRepository fieldTypeDefinitionRepository;

	@Test
	public void allEntriesExistsInFieldTypeDefinition() throws IOException {
		assertEquals(12, fieldTypeDefinitionRepository.count());
	}
}
