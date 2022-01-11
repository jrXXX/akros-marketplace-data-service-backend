package ch.akros.marketplace.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "THEME")
public class Theme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "THEME_ID")
	private Long themeId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	@OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
	private List<FieldType> fieldTypes;
}
