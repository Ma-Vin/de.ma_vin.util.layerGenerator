package de.ma_vin.util.sample.content.dao.single.indirect;

import de.ma_vin.util.layer.generator.annotations.model.BaseDao;
import de.ma_vin.util.sample.content.dao.IIdentifiableDao;
import de.ma_vin.util.sample.content.dao.RootDao;
import de.ma_vin.util.sample.content.domain.single.indirect.SingleRefIndirectParent;
import de.ma_vin.util.sample.given.IdGenerator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Generated dao class of SingleRefIndirectParent
 */
@BaseDao("de.ma_vin.util.sample.content.dao")
@Data
@Entity
@EqualsAndHashCode(exclude = {"parentRoot"})
@Table(name = "SingleRefIndirectParents")
@ToString(exclude = {"parentRoot"})
public class SingleRefIndirectParentDao implements IIdentifiableDao {

	@Column
	private String description;

	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@JoinColumn(name = "ParentRootId", nullable = false)
	@OneToOne(targetEntity = RootDao.class)
	private RootDao parentRoot;

	@Override
	public String getIdentification() {
		return IdGenerator.generateIdentification(id, SingleRefIndirectParent.ID_PREFIX);
	}

	@Override
	public void setIdentification(String identification) {
		id = IdGenerator.generateId(identification, SingleRefIndirectParent.ID_PREFIX);
	}

}
