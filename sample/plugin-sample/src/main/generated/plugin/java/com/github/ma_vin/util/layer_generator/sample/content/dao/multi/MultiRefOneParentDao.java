package com.github.ma_vin.util.layer_generator.sample.content.dao.multi;

import com.github.ma_vin.util.layer_generator.annotations.model.BaseDao;
import com.github.ma_vin.util.layer_generator.sample.content.dao.IIdentifiableDao;
import com.github.ma_vin.util.layer_generator.sample.content.dao.RootDao;
import com.github.ma_vin.util.layer_generator.sample.content.domain.multi.MultiRefOneParent;
import com.github.ma_vin.util.layer_generator.sample.given.IdGenerator;
import jakarta.persistence.*;
import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Generated dao class of MultiRefOneParent
 */
@BaseDao("com.github.ma_vin.util.layer_generator.sample.content.dao")
@Data
@Entity
@EqualsAndHashCode(exclude = {"parentRoot", "multiRef"})
@Table(name = "MultiRefOneParents")
@ToString(exclude = {"parentRoot", "multiRef"})
public class MultiRefOneParentDao implements IIdentifiableDao {

	@Column
	private String description;

	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	/**
	 * Instances with two parents
	 */
	@OneToMany(mappedBy = "parentMultiRefOneParent", targetEntity = MultiRefTwoParentsDao.class)
	private Collection<MultiRefTwoParentsDao> multiRef;

	@JoinColumn(name = "ParentRootId", nullable = false)
	@ManyToOne(targetEntity = RootDao.class)
	private RootDao parentRoot;

	@Override
	public String getIdentification() {
		return IdGenerator.generateIdentification(id, MultiRefOneParent.ID_PREFIX);
	}

	@Override
	public void setIdentification(String identification) {
		id = IdGenerator.generateId(identification, MultiRefOneParent.ID_PREFIX);
	}

}