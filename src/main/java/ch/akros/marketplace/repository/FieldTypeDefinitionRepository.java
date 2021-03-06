package ch.akros.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.akros.marketplace.entity.FieldTypeDefinition;

@Repository
public interface FieldTypeDefinitionRepository extends JpaRepository<FieldTypeDefinition, Long> {
}
