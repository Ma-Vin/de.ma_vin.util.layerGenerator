package de.ma_vin.util.sample.content.dao.parent;

import de.ma_vin.util.layer.generator.annotations.model.BaseDao;
import de.ma_vin.util.sample.content.dao.RootDao;
import de.ma_vin.util.sample.content.domain.parent.ExtendingClass;
import de.ma_vin.util.sample.given.IdGenerator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Generated dao class of ExtendingClass
 */
@BaseDao("de.ma_vin.util.sample.content.dao")
@Data
@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"parentRoot"})
@SuppressWarnings("java:S2160")
@Table(name = "ExtendingClasss")
@ToString(callSuper = true, exclude = {"parentRoot"})
public class ExtendingClassDao extends SuperClassDao {

	@Column
	private String additionalDescription;

	@JoinColumn(name = "ParentRootId", nullable = false)
	@ManyToOne(targetEntity = RootDao.class)
	private RootDao parentRoot;

	@Override
	public String getIdentification() {
		return IdGenerator.generateIdentification(id, ExtendingClass.ID_PREFIX);
	}

	@Override
	public void setIdentification(String identification) {
		id = IdGenerator.generateId(identification, ExtendingClass.ID_PREFIX);
	}

}
