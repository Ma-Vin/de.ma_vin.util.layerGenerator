package de.ma_vin.util.sample.content.domain;

import de.ma_vin.util.sample.content.domain.domain.OnlyDomain;
import de.ma_vin.util.sample.content.domain.domain.dao.DomainAndDao;
import de.ma_vin.util.sample.content.domain.domain.dto.DomainAndDto;
import de.ma_vin.util.sample.content.domain.filtering.Filtered;
import de.ma_vin.util.sample.content.domain.filtering.FilteredNotOwner;
import de.ma_vin.util.sample.content.domain.filtering.FilteredOnlyDaoField;
import de.ma_vin.util.sample.content.domain.filtering.FilteredOnlyDaoFieldNotOwner;
import de.ma_vin.util.sample.content.domain.filtering.SomeDifferentFilteringNotOwner;
import de.ma_vin.util.sample.content.domain.filtering.SomeFilteringOwner;
import de.ma_vin.util.sample.content.domain.multi.MultiRefOneParent;
import de.ma_vin.util.sample.content.domain.multi.MultiRefTwoParents;
import de.ma_vin.util.sample.content.domain.multi.indirect.MultiRefIndirectParent;
import de.ma_vin.util.sample.content.domain.multi.indirect.MultiRefIndirectSelfReference;
import de.ma_vin.util.sample.content.domain.multi.indirect.MultiRefOtherIndirectParent;
import de.ma_vin.util.sample.content.domain.parent.ExtendingClass;
import de.ma_vin.util.sample.content.domain.single.SingleRefOneParent;
import de.ma_vin.util.sample.content.domain.single.SingleRefTwoParents;
import de.ma_vin.util.sample.content.domain.single.indirect.SingleRefIndirectParent;
import de.ma_vin.util.sample.content.domain.single.indirect.SingleRefOtherIndirectParent;
import de.ma_vin.util.sample.extending.ExtendedMultiRefTwoParents;

public class DomainObjectFactory {

	private DomainObjectFactory() {
	}

	public static DomainAndDao createDomainAndDao() {
		return new DomainAndDao();
	}

	public static DomainAndDto createDomainAndDto() {
		return new DomainAndDto();
	}

	public static ExtendingClass createExtendingClass() {
		return new ExtendingClass();
	}

	public static Filtered createFiltered() {
		return new Filtered();
	}

	public static FilteredNotOwner createFilteredNotOwner() {
		return new FilteredNotOwner();
	}

	public static FilteredOnlyDaoField createFilteredOnlyDaoField() {
		return new FilteredOnlyDaoField();
	}

	public static FilteredOnlyDaoFieldNotOwner createFilteredOnlyDaoFieldNotOwner() {
		return new FilteredOnlyDaoFieldNotOwner();
	}

	public static MultiRefIndirectParent createMultiRefIndirectParent() {
		return new MultiRefIndirectParent();
	}

	public static MultiRefIndirectSelfReference createMultiRefIndirectSelfReference() {
		return new MultiRefIndirectSelfReference();
	}

	public static MultiRefOneParent createMultiRefOneParent() {
		return new MultiRefOneParent();
	}

	public static MultiRefOtherIndirectParent createMultiRefOtherIndirectParent() {
		return new MultiRefOtherIndirectParent();
	}

	public static MultiRefTwoParents createMultiRefTwoParents() {
		return new ExtendedMultiRefTwoParents();
	}

	public static OnlyDomain createOnlyDomain() {
		return new OnlyDomain();
	}

	public static Root createRoot() {
		return new Root();
	}

	public static RootExt createRootExt() {
		return new RootExt();
	}

	public static SingleRefIndirectParent createSingleRefIndirectParent() {
		return new SingleRefIndirectParent();
	}

	public static SingleRefOneParent createSingleRefOneParent() {
		return new SingleRefOneParent();
	}

	public static SingleRefOtherIndirectParent createSingleRefOtherIndirectParent() {
		return new SingleRefOtherIndirectParent();
	}

	public static SingleRefTwoParents createSingleRefTwoParents() {
		return new SingleRefTwoParents();
	}

	public static SomeDifferentFilteringNotOwner createSomeDifferentFilteringNotOwner() {
		return new SomeDifferentFilteringNotOwner();
	}

	public static SomeFilteringOwner createSomeFilteringOwner() {
		return new SomeFilteringOwner();
	}

}
