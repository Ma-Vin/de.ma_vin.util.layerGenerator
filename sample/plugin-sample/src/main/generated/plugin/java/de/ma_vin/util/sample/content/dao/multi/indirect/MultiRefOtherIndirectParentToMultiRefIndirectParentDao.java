package de.ma_vin.util.sample.content.dao.multi.indirect;

import de.ma_vin.util.layer.generator.annotations.model.BaseDao;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@BaseDao("de.ma_vin.util.sample.content.dao")
@Data
@Entity
@IdClass(MultiRefOtherIndirectParentToMultiRefIndirectParentDao.MultiRefOtherIndirectParentToMultiRefIndirectParentId.class)
@NoArgsConstructor
@SuppressWarnings("java:S1948")
@Table(name = "MultiRefOtherIndirectParentToMultiRefIndirectParents")
public class MultiRefOtherIndirectParentToMultiRefIndirectParentDao {

	@Id
	@JoinColumn(name = "MultiRefIndirectParentId")
	@OneToOne(targetEntity = MultiRefIndirectParentDao.class)
	private MultiRefIndirectParentDao multiRefIndirectParent;

	@Id
	@JoinColumn(name = "MultiRefOtherIndirectParentId")
	@ManyToOne(targetEntity = MultiRefOtherIndirectParentDao.class)
	private MultiRefOtherIndirectParentDao multiRefOtherIndirectParent;

	@AllArgsConstructor
	@Data
	@NoArgsConstructor
	@SuppressWarnings("java:S1068")
	public static class MultiRefOtherIndirectParentToMultiRefIndirectParentId implements Serializable {

		private Long multiRefIndirectParent;

		private Long multiRefOtherIndirectParent;

	}

}
