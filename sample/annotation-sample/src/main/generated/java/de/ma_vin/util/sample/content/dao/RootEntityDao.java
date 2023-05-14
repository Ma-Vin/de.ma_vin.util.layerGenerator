package de.ma_vin.util.sample.content.dao;

import de.ma_vin.util.layer.generator.annotations.model.BaseDao;
import de.ma_vin.util.sample.content.domain.RootEntity;
import de.ma_vin.util.sample.given.IdGenerator;
import jakarta.persistence.*;
import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Generated dao class of RootEntity
 */
@BaseDao("de.ma_vin.util.sample.content.dao")
@Data
@Entity
@EqualsAndHashCode(exclude = {"subEntities"})
@Table(name = "RootEntitys")
@ToString(exclude = {"subEntities"})
public class RootEntityDao implements IIdentifiableDao {

	@Column
	private String description;

	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Column
	private String rootName;

	@OneToMany(mappedBy = "parentRootEntity", targetEntity = SubEntityDao.class)
	private Collection<SubEntityDao> subEntities;

	@Override
	public String getIdentification() {
		return IdGenerator.generateIdentification(id, RootEntity.ID_PREFIX);
	}

	@Override
	public void setIdentification(String identification) {
		id = IdGenerator.generateId(identification, RootEntity.ID_PREFIX);
	}

}
