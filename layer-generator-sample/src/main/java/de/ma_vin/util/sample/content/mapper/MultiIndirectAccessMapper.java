package de.ma_vin.util.sample.content.mapper;

import de.ma_vin.util.layer.generator.annotations.mapper.BaseAccessMapper;
import de.ma_vin.util.sample.content.dao.DaoObjectFactory;
import de.ma_vin.util.sample.content.dao.IIdentifiableDao;
import de.ma_vin.util.sample.content.dao.RootDao;
import de.ma_vin.util.sample.content.dao.multi.indirect.MultiRefIndirectParentDao;
import de.ma_vin.util.sample.content.dao.multi.indirect.MultiRefIndirectSelfReferenceDao;
import de.ma_vin.util.sample.content.dao.multi.indirect.MultiRefIndirectSelfReferenceToMultiRefIndirectSelfReferenceDao;
import de.ma_vin.util.sample.content.dao.multi.indirect.MultiRefOtherIndirectParentDao;
import de.ma_vin.util.sample.content.dao.multi.indirect.MultiRefOtherIndirectParentToMultiRefIndirectParentDao;
import de.ma_vin.util.sample.content.domain.DomainObjectFactory;
import de.ma_vin.util.sample.content.domain.IIdentifiable;
import de.ma_vin.util.sample.content.domain.Root;
import de.ma_vin.util.sample.content.domain.multi.indirect.MultiRefIndirectParent;
import de.ma_vin.util.sample.content.domain.multi.indirect.MultiRefIndirectSelfReference;
import de.ma_vin.util.sample.content.domain.multi.indirect.MultiRefOtherIndirectParent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class which provides methods to convert a data access to a domain object of sub package <i>multi.indirect<i> and the other way around
 */
@BaseAccessMapper
public class MultiIndirectAccessMapper extends AbstractAccessMapper {

	/**
	 * singleton
	 */
	private static MultiIndirectAccessMapper instance;

	/**
	 * Converts a(n) {@link MultiRefIndirectParentDao} to a(n) {@link MultiRefIndirectParent}
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @return an equivalent new created {@link MultiRefIndirectParent}
	 */
	public static MultiRefIndirectParent convertToMultiRefIndirectParent(MultiRefIndirectParentDao multiRefIndirectParent) {
		return convertToMultiRefIndirectParent(multiRefIndirectParent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParentDao} to a(n) {@link MultiRefIndirectParent}
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param mappedObjects          map which contains already mapped objects. If an identification of {@code multiRefIndirectParent} is contained, the
	 *                               found {@link MultiRefIndirectParent} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectParent} or the found one from the given map
	 */
	public static MultiRefIndirectParent convertToMultiRefIndirectParent(MultiRefIndirectParentDao multiRefIndirectParent
			, Map<String, IIdentifiable> mappedObjects) {
		return convertToDomain(multiRefIndirectParent, mappedObjects, DomainObjectFactory::createMultiRefIndirectParent, (dao, domain) -> getInstance().setMultiRefIndirectParentValues(dao, domain)
				, (dao, domain) -> getInstance().setMultiRefIndirectParentSingleReferences(dao, domain, mappedObjects)
				, (dao, domain) -> getInstance().setMultiRefIndirectParentMultiReferences(dao, domain, mappedObjects));
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParentDao} to a(n) {@link MultiRefIndirectParent} and sets the result to the corresponding reference property
	 * at the parent
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param parent                 the parent of converted result
	 * @return an equivalent new created {@link MultiRefIndirectParent}
	 */
	public static MultiRefIndirectParent convertToMultiRefIndirectParent(MultiRefIndirectParentDao multiRefIndirectParent
			, MultiRefOtherIndirectParent parent) {
		return convertToMultiRefIndirectParent(multiRefIndirectParent, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParentDao} to a(n) {@link MultiRefIndirectParent} and sets the result to the corresponding reference property
	 * at the parent
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param parent                 the parent of converted result
	 * @param mappedObjects          map which contains already mapped objects. If an identification of {@code multiRefIndirectParent} is contained, the
	 *                               found {@link MultiRefIndirectParent} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectParent} or the found one from the given map
	 */
	public static MultiRefIndirectParent convertToMultiRefIndirectParent(MultiRefIndirectParentDao multiRefIndirectParent
			, MultiRefOtherIndirectParent parent, Map<String, IIdentifiable> mappedObjects) {
		MultiRefIndirectParent result = convertToMultiRefIndirectParent(multiRefIndirectParent, mappedObjects);
		if (result != null) {
			parent.getMultiIndirectRef().add(result);
		}
		return result;
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParentDao} to a(n) {@link MultiRefIndirectParent} and sets the result to the corresponding reference property
	 * at the parent
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param parent                 the parent of converted result
	 * @return an equivalent new created {@link MultiRefIndirectParent}
	 */
	public static MultiRefIndirectParent convertToMultiRefIndirectParent(MultiRefIndirectParentDao multiRefIndirectParent, Root parent) {
		return convertToMultiRefIndirectParent(multiRefIndirectParent, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParentDao} to a(n) {@link MultiRefIndirectParent} and sets the result to the corresponding reference property
	 * at the parent
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param parent                 the parent of converted result
	 * @param mappedObjects          map which contains already mapped objects. If an identification of {@code multiRefIndirectParent} is contained, the
	 *                               found {@link MultiRefIndirectParent} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectParent} or the found one from the given map
	 */
	public static MultiRefIndirectParent convertToMultiRefIndirectParent(MultiRefIndirectParentDao multiRefIndirectParent, Root parent
			, Map<String, IIdentifiable> mappedObjects) {
		MultiRefIndirectParent result = convertToMultiRefIndirectParent(multiRefIndirectParent, mappedObjects);
		if (result != null) {
			parent.getMultiRefIndirectParent().add(result);
		}
		return result;
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParent} to a(n) {@link MultiRefIndirectParentDao}
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @return an equivalent new created {@link MultiRefIndirectParentDao}
	 */
	public static MultiRefIndirectParentDao convertToMultiRefIndirectParentDao(MultiRefIndirectParent multiRefIndirectParent) {
		return convertToMultiRefIndirectParentDao(multiRefIndirectParent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParent} to a(n) {@link MultiRefIndirectParentDao}
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param mappedObjects          map which contains already mapped objects. If an identification of {@code multiRefIndirectParent} is contained, the
	 *                               found {@link MultiRefIndirectParentDao} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectParentDao} or the found one from the given map
	 */
	public static MultiRefIndirectParentDao convertToMultiRefIndirectParentDao(MultiRefIndirectParent multiRefIndirectParent
			, Map<String, IIdentifiableDao> mappedObjects) {
		return convertToDao(multiRefIndirectParent, mappedObjects, DaoObjectFactory::createMultiRefIndirectParentDao, (domain, dao) -> getInstance().setMultiRefIndirectParentDaoValues(domain, dao)
				, (domain, dao) -> getInstance().setMultiRefIndirectParentDaoSingleReferences(domain, dao, mappedObjects)
				, (domain, dao) -> getInstance().setMultiRefIndirectParentDaoMultiReferences(domain, dao, mappedObjects));
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParent} to a(n) {@link MultiRefIndirectParentDao} and sets the result to the corresponding reference property
	 * at the parent
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param parent                 the parent of converted result
	 * @return an equivalent new created {@link MultiRefIndirectParentDao}
	 */
	public static MultiRefIndirectParentDao convertToMultiRefIndirectParentDao(MultiRefIndirectParent multiRefIndirectParent
			, MultiRefOtherIndirectParentDao parent) {
		return convertToMultiRefIndirectParentDao(multiRefIndirectParent, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParent} to a(n) {@link MultiRefIndirectParentDao} and sets the result to the corresponding reference property
	 * at the parent
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param parent                 the parent of converted result
	 * @param mappedObjects          map which contains already mapped objects. If an identification of {@code multiRefIndirectParent} is contained, the
	 *                               found {@link MultiRefIndirectParentDao} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectParentDao} or the found one from the given map
	 */
	public static MultiRefIndirectParentDao convertToMultiRefIndirectParentDao(MultiRefIndirectParent multiRefIndirectParent
			, MultiRefOtherIndirectParentDao parent, Map<String, IIdentifiableDao> mappedObjects) {
		MultiRefIndirectParentDao result = convertToMultiRefIndirectParentDao(multiRefIndirectParent, mappedObjects);
		if (result != null) {
			MultiRefOtherIndirectParentToMultiRefIndirectParentDao connectionTable = DaoObjectFactory.createMultiRefOtherIndirectParentToMultiRefIndirectParentDao();
			connectionTable.setMultiRefIndirectParent(result);
			connectionTable.setMultiRefOtherIndirectParent(parent);
			parent.getMultiIndirectRef().add(connectionTable);
		}
		return result;
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParent} to a(n) {@link MultiRefIndirectParentDao} and sets the result to the corresponding reference property
	 * at the parent
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param parent                 the parent of converted result
	 * @return an equivalent new created {@link MultiRefIndirectParentDao}
	 */
	public static MultiRefIndirectParentDao convertToMultiRefIndirectParentDao(MultiRefIndirectParent multiRefIndirectParent, RootDao parent) {
		return convertToMultiRefIndirectParentDao(multiRefIndirectParent, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectParent} to a(n) {@link MultiRefIndirectParentDao} and sets the result to the corresponding reference property
	 * at the parent
	 *
	 * @param multiRefIndirectParent the source object which should be converted
	 * @param parent                 the parent of converted result
	 * @param mappedObjects          map which contains already mapped objects. If an identification of {@code multiRefIndirectParent} is contained, the
	 *                               found {@link MultiRefIndirectParentDao} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectParentDao} or the found one from the given map
	 */
	public static MultiRefIndirectParentDao convertToMultiRefIndirectParentDao(MultiRefIndirectParent multiRefIndirectParent, RootDao parent
			, Map<String, IIdentifiableDao> mappedObjects) {
		MultiRefIndirectParentDao result = convertToMultiRefIndirectParentDao(multiRefIndirectParent, mappedObjects);
		if (result != null) {
			result.setParentRoot(parent);
			parent.getMultiRefIndirectParent().add(result);
		}
		return result;
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReferenceDao} to a(n) {@link MultiRefIndirectSelfReference}
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @return an equivalent new created {@link MultiRefIndirectSelfReference}
	 */
	public static MultiRefIndirectSelfReference convertToMultiRefIndirectSelfReference(MultiRefIndirectSelfReferenceDao multiRefIndirectSelfReference
			, boolean includeChildren) {
		return convertToMultiRefIndirectSelfReference(multiRefIndirectSelfReference, includeChildren, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReferenceDao} to a(n) {@link MultiRefIndirectSelfReference}
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param mappedObjects                 map which contains already mapped objects. If an identification of {@code multiRefIndirectSelfReference} is
	 *                                      contained, the found {@link MultiRefIndirectSelfReference} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectSelfReference} or the found one from the given map
	 */
	public static MultiRefIndirectSelfReference convertToMultiRefIndirectSelfReference(MultiRefIndirectSelfReferenceDao multiRefIndirectSelfReference
			, boolean includeChildren, Map<String, IIdentifiable> mappedObjects) {
		return convertToDomain(multiRefIndirectSelfReference, mappedObjects, DomainObjectFactory::createMultiRefIndirectSelfReference, (dao, domain) -> getInstance().setMultiRefIndirectSelfReferenceValues(dao, domain)
				, (dao, domain) -> getInstance().setMultiRefIndirectSelfReferenceSingleReferences(dao, domain, mappedObjects)
				, (dao, domain) -> getInstance().setMultiRefIndirectSelfReferenceMultiReferences(dao, domain, includeChildren, mappedObjects));
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReferenceDao} to a(n) {@link MultiRefIndirectSelfReference} and sets the result to the corresponding
	 * reference property at the parent
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param parent                        the parent of converted result
	 * @return an equivalent new created {@link MultiRefIndirectSelfReference}
	 */
	public static MultiRefIndirectSelfReference convertToMultiRefIndirectSelfReference(MultiRefIndirectSelfReferenceDao multiRefIndirectSelfReference
			, boolean includeChildren, MultiRefIndirectSelfReference parent) {
		return convertToMultiRefIndirectSelfReference(multiRefIndirectSelfReference, includeChildren, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReferenceDao} to a(n) {@link MultiRefIndirectSelfReference} and sets the result to the corresponding
	 * reference property at the parent
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param parent                        the parent of converted result
	 * @param mappedObjects                 map which contains already mapped objects. If an identification of {@code multiRefIndirectSelfReference} is
	 *                                      contained, the found {@link MultiRefIndirectSelfReference} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectSelfReference} or the found one from the given map
	 */
	public static MultiRefIndirectSelfReference convertToMultiRefIndirectSelfReference(MultiRefIndirectSelfReferenceDao multiRefIndirectSelfReference
			, boolean includeChildren, MultiRefIndirectSelfReference parent, Map<String, IIdentifiable> mappedObjects) {
		MultiRefIndirectSelfReference result = convertToMultiRefIndirectSelfReference(multiRefIndirectSelfReference, includeChildren, mappedObjects);
		if (result != null) {
			parent.getSelfRefs().add(result);
		}
		return result;
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReferenceDao} to a(n) {@link MultiRefIndirectSelfReference} and sets the result to the corresponding
	 * reference property at the parent
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param parent                        the parent of converted result
	 * @return an equivalent new created {@link MultiRefIndirectSelfReference}
	 */
	public static MultiRefIndirectSelfReference convertToMultiRefIndirectSelfReference(MultiRefIndirectSelfReferenceDao multiRefIndirectSelfReference
			, boolean includeChildren, Root parent) {
		return convertToMultiRefIndirectSelfReference(multiRefIndirectSelfReference, includeChildren, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReferenceDao} to a(n) {@link MultiRefIndirectSelfReference} and sets the result to the corresponding
	 * reference property at the parent
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param parent                        the parent of converted result
	 * @param mappedObjects                 map which contains already mapped objects. If an identification of {@code multiRefIndirectSelfReference} is
	 *                                      contained, the found {@link MultiRefIndirectSelfReference} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectSelfReference} or the found one from the given map
	 */
	public static MultiRefIndirectSelfReference convertToMultiRefIndirectSelfReference(MultiRefIndirectSelfReferenceDao multiRefIndirectSelfReference
			, boolean includeChildren, Root parent, Map<String, IIdentifiable> mappedObjects) {
		MultiRefIndirectSelfReference result = convertToMultiRefIndirectSelfReference(multiRefIndirectSelfReference, includeChildren, mappedObjects);
		if (result != null) {
			parent.getMultiRefIndirectSelfReference().add(result);
		}
		return result;
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReference} to a(n) {@link MultiRefIndirectSelfReferenceDao}
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @return an equivalent new created {@link MultiRefIndirectSelfReferenceDao}
	 */
	public static MultiRefIndirectSelfReferenceDao convertToMultiRefIndirectSelfReferenceDao(MultiRefIndirectSelfReference multiRefIndirectSelfReference
			, boolean includeChildren) {
		return convertToMultiRefIndirectSelfReferenceDao(multiRefIndirectSelfReference, includeChildren, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReference} to a(n) {@link MultiRefIndirectSelfReferenceDao}
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param mappedObjects                 map which contains already mapped objects. If an identification of {@code multiRefIndirectSelfReference} is
	 *                                      contained, the found {@link MultiRefIndirectSelfReferenceDao} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectSelfReferenceDao} or the found one from the given map
	 */
	public static MultiRefIndirectSelfReferenceDao convertToMultiRefIndirectSelfReferenceDao(MultiRefIndirectSelfReference multiRefIndirectSelfReference
			, boolean includeChildren, Map<String, IIdentifiableDao> mappedObjects) {
		return convertToDao(multiRefIndirectSelfReference, mappedObjects, DaoObjectFactory::createMultiRefIndirectSelfReferenceDao, (domain, dao) -> getInstance().setMultiRefIndirectSelfReferenceDaoValues(domain, dao)
				, (domain, dao) -> getInstance().setMultiRefIndirectSelfReferenceDaoSingleReferences(domain, dao, mappedObjects)
				, (domain, dao) -> getInstance().setMultiRefIndirectSelfReferenceDaoMultiReferences(domain, dao, includeChildren, mappedObjects));
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReference} to a(n) {@link MultiRefIndirectSelfReferenceDao} and sets the result to the corresponding
	 * reference property at the parent
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param parent                        the parent of converted result
	 * @return an equivalent new created {@link MultiRefIndirectSelfReferenceDao}
	 */
	public static MultiRefIndirectSelfReferenceDao convertToMultiRefIndirectSelfReferenceDao(MultiRefIndirectSelfReference multiRefIndirectSelfReference
			, boolean includeChildren, MultiRefIndirectSelfReferenceDao parent) {
		return convertToMultiRefIndirectSelfReferenceDao(multiRefIndirectSelfReference, includeChildren, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReference} to a(n) {@link MultiRefIndirectSelfReferenceDao} and sets the result to the corresponding
	 * reference property at the parent
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param parent                        the parent of converted result
	 * @param mappedObjects                 map which contains already mapped objects. If an identification of {@code multiRefIndirectSelfReference} is
	 *                                      contained, the found {@link MultiRefIndirectSelfReferenceDao} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectSelfReferenceDao} or the found one from the given map
	 */
	public static MultiRefIndirectSelfReferenceDao convertToMultiRefIndirectSelfReferenceDao(MultiRefIndirectSelfReference multiRefIndirectSelfReference
			, boolean includeChildren, MultiRefIndirectSelfReferenceDao parent, Map<String, IIdentifiableDao> mappedObjects) {
		MultiRefIndirectSelfReferenceDao result = convertToMultiRefIndirectSelfReferenceDao(multiRefIndirectSelfReference, includeChildren, mappedObjects);
		if (result != null) {
			MultiRefIndirectSelfReferenceToMultiRefIndirectSelfReferenceDao connectionTable = DaoObjectFactory.createMultiRefIndirectSelfReferenceToMultiRefIndirectSelfReferenceDao();
			connectionTable.setSubMultiRefIndirectSelfReference(result);
			connectionTable.setMultiRefIndirectSelfReference(parent);
			parent.getSelfRefs().add(connectionTable);
		}
		return result;
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReference} to a(n) {@link MultiRefIndirectSelfReferenceDao} and sets the result to the corresponding
	 * reference property at the parent
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param parent                        the parent of converted result
	 * @return an equivalent new created {@link MultiRefIndirectSelfReferenceDao}
	 */
	public static MultiRefIndirectSelfReferenceDao convertToMultiRefIndirectSelfReferenceDao(MultiRefIndirectSelfReference multiRefIndirectSelfReference
			, boolean includeChildren, RootDao parent) {
		return convertToMultiRefIndirectSelfReferenceDao(multiRefIndirectSelfReference, includeChildren, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefIndirectSelfReference} to a(n) {@link MultiRefIndirectSelfReferenceDao} and sets the result to the corresponding
	 * reference property at the parent
	 *
	 * @param multiRefIndirectSelfReference the source object which should be converted
	 * @param includeChildren               {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                      which are not of type {@link java.util.Collection}
	 * @param parent                        the parent of converted result
	 * @param mappedObjects                 map which contains already mapped objects. If an identification of {@code multiRefIndirectSelfReference} is
	 *                                      contained, the found {@link MultiRefIndirectSelfReferenceDao} will be returned
	 * @return an equivalent new created {@link MultiRefIndirectSelfReferenceDao} or the found one from the given map
	 */
	public static MultiRefIndirectSelfReferenceDao convertToMultiRefIndirectSelfReferenceDao(MultiRefIndirectSelfReference multiRefIndirectSelfReference
			, boolean includeChildren, RootDao parent, Map<String, IIdentifiableDao> mappedObjects) {
		MultiRefIndirectSelfReferenceDao result = convertToMultiRefIndirectSelfReferenceDao(multiRefIndirectSelfReference, includeChildren, mappedObjects);
		if (result != null) {
			result.setParentRoot(parent);
			parent.getMultiRefIndirectSelfReference().add(result);
		}
		return result;
	}

	/**
	 * Converts a(n) {@link MultiRefOtherIndirectParentDao} to a(n) {@link MultiRefOtherIndirectParent}
	 *
	 * @param multiRefOtherIndirectParent the source object which should be converted
	 * @param includeChildren             {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                    which are not of type {@link java.util.Collection}
	 * @return an equivalent new created {@link MultiRefOtherIndirectParent}
	 */
	public static MultiRefOtherIndirectParent convertToMultiRefOtherIndirectParent(MultiRefOtherIndirectParentDao multiRefOtherIndirectParent
			, boolean includeChildren) {
		return convertToMultiRefOtherIndirectParent(multiRefOtherIndirectParent, includeChildren, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefOtherIndirectParentDao} to a(n) {@link MultiRefOtherIndirectParent}
	 *
	 * @param multiRefOtherIndirectParent the source object which should be converted
	 * @param includeChildren             {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                    which are not of type {@link java.util.Collection}
	 * @param mappedObjects               map which contains already mapped objects. If an identification of {@code multiRefOtherIndirectParent} is
	 *                                    contained, the found {@link MultiRefOtherIndirectParent} will be returned
	 * @return an equivalent new created {@link MultiRefOtherIndirectParent} or the found one from the given map
	 */
	public static MultiRefOtherIndirectParent convertToMultiRefOtherIndirectParent(MultiRefOtherIndirectParentDao multiRefOtherIndirectParent
			, boolean includeChildren, Map<String, IIdentifiable> mappedObjects) {
		return convertToDomain(multiRefOtherIndirectParent, mappedObjects, DomainObjectFactory::createMultiRefOtherIndirectParent, (dao, domain) -> getInstance().setMultiRefOtherIndirectParentValues(dao, domain)
				, (dao, domain) -> getInstance().setMultiRefOtherIndirectParentSingleReferences(dao, domain, mappedObjects)
				, (dao, domain) -> getInstance().setMultiRefOtherIndirectParentMultiReferences(dao, domain, includeChildren, mappedObjects));
	}

	/**
	 * Converts a(n) {@link MultiRefOtherIndirectParentDao} to a(n) {@link MultiRefOtherIndirectParent} and sets the result to the corresponding reference
	 * property at the parent
	 *
	 * @param multiRefOtherIndirectParent the source object which should be converted
	 * @param includeChildren             {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                    which are not of type {@link java.util.Collection}
	 * @param parent                      the parent of converted result
	 * @return an equivalent new created {@link MultiRefOtherIndirectParent}
	 */
	public static MultiRefOtherIndirectParent convertToMultiRefOtherIndirectParent(MultiRefOtherIndirectParentDao multiRefOtherIndirectParent
			, boolean includeChildren, Root parent) {
		return convertToMultiRefOtherIndirectParent(multiRefOtherIndirectParent, includeChildren, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefOtherIndirectParentDao} to a(n) {@link MultiRefOtherIndirectParent} and sets the result to the corresponding reference
	 * property at the parent
	 *
	 * @param multiRefOtherIndirectParent the source object which should be converted
	 * @param includeChildren             {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                    which are not of type {@link java.util.Collection}
	 * @param parent                      the parent of converted result
	 * @param mappedObjects               map which contains already mapped objects. If an identification of {@code multiRefOtherIndirectParent} is
	 *                                    contained, the found {@link MultiRefOtherIndirectParent} will be returned
	 * @return an equivalent new created {@link MultiRefOtherIndirectParent} or the found one from the given map
	 */
	public static MultiRefOtherIndirectParent convertToMultiRefOtherIndirectParent(MultiRefOtherIndirectParentDao multiRefOtherIndirectParent
			, boolean includeChildren, Root parent, Map<String, IIdentifiable> mappedObjects) {
		MultiRefOtherIndirectParent result = convertToMultiRefOtherIndirectParent(multiRefOtherIndirectParent, includeChildren, mappedObjects);
		if (result != null) {
			parent.getMultiRefIndirectOtherParent().add(result);
		}
		return result;
	}

	/**
	 * Converts a(n) {@link MultiRefOtherIndirectParent} to a(n) {@link MultiRefOtherIndirectParentDao}
	 *
	 * @param multiRefOtherIndirectParent the source object which should be converted
	 * @param includeChildren             {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                    which are not of type {@link java.util.Collection}
	 * @return an equivalent new created {@link MultiRefOtherIndirectParentDao}
	 */
	public static MultiRefOtherIndirectParentDao convertToMultiRefOtherIndirectParentDao(MultiRefOtherIndirectParent multiRefOtherIndirectParent
			, boolean includeChildren) {
		return convertToMultiRefOtherIndirectParentDao(multiRefOtherIndirectParent, includeChildren, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefOtherIndirectParent} to a(n) {@link MultiRefOtherIndirectParentDao}
	 *
	 * @param multiRefOtherIndirectParent the source object which should be converted
	 * @param includeChildren             {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                    which are not of type {@link java.util.Collection}
	 * @param mappedObjects               map which contains already mapped objects. If an identification of {@code multiRefOtherIndirectParent} is
	 *                                    contained, the found {@link MultiRefOtherIndirectParentDao} will be returned
	 * @return an equivalent new created {@link MultiRefOtherIndirectParentDao} or the found one from the given map
	 */
	public static MultiRefOtherIndirectParentDao convertToMultiRefOtherIndirectParentDao(MultiRefOtherIndirectParent multiRefOtherIndirectParent
			, boolean includeChildren, Map<String, IIdentifiableDao> mappedObjects) {
		return convertToDao(multiRefOtherIndirectParent, mappedObjects, DaoObjectFactory::createMultiRefOtherIndirectParentDao, (domain, dao) -> getInstance().setMultiRefOtherIndirectParentDaoValues(domain, dao)
				, (domain, dao) -> getInstance().setMultiRefOtherIndirectParentDaoSingleReferences(domain, dao, mappedObjects)
				, (domain, dao) -> getInstance().setMultiRefOtherIndirectParentDaoMultiReferences(domain, dao, includeChildren, mappedObjects));
	}

	/**
	 * Converts a(n) {@link MultiRefOtherIndirectParent} to a(n) {@link MultiRefOtherIndirectParentDao} and sets the result to the corresponding reference
	 * property at the parent
	 *
	 * @param multiRefOtherIndirectParent the source object which should be converted
	 * @param includeChildren             {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                    which are not of type {@link java.util.Collection}
	 * @param parent                      the parent of converted result
	 * @return an equivalent new created {@link MultiRefOtherIndirectParentDao}
	 */
	public static MultiRefOtherIndirectParentDao convertToMultiRefOtherIndirectParentDao(MultiRefOtherIndirectParent multiRefOtherIndirectParent
			, boolean includeChildren, RootDao parent) {
		return convertToMultiRefOtherIndirectParentDao(multiRefOtherIndirectParent, includeChildren, parent, new HashMap<>());
	}

	/**
	 * Converts a(n) {@link MultiRefOtherIndirectParent} to a(n) {@link MultiRefOtherIndirectParentDao} and sets the result to the corresponding reference
	 * property at the parent
	 *
	 * @param multiRefOtherIndirectParent the source object which should be converted
	 * @param includeChildren             {@code true} if all references should also be mapped. {@code false} if only those references should be mapped
	 *                                    which are not of type {@link java.util.Collection}
	 * @param parent                      the parent of converted result
	 * @param mappedObjects               map which contains already mapped objects. If an identification of {@code multiRefOtherIndirectParent} is
	 *                                    contained, the found {@link MultiRefOtherIndirectParentDao} will be returned
	 * @return an equivalent new created {@link MultiRefOtherIndirectParentDao} or the found one from the given map
	 */
	public static MultiRefOtherIndirectParentDao convertToMultiRefOtherIndirectParentDao(MultiRefOtherIndirectParent multiRefOtherIndirectParent
			, boolean includeChildren, RootDao parent, Map<String, IIdentifiableDao> mappedObjects) {
		MultiRefOtherIndirectParentDao result = convertToMultiRefOtherIndirectParentDao(multiRefOtherIndirectParent, includeChildren, mappedObjects);
		if (result != null) {
			result.setParentRoot(parent);
			parent.getMultiRefIndirectOtherParent().add(result);
		}
		return result;
	}

	/**
	 * @return the singleton
	 */
	public static MultiIndirectAccessMapper getInstance() {
		if (instance == null) {
			instance = AccessMapperFactory.createMultiIndirectAccessMapper();
		}
		return instance;
	}

	/**
	 * Adds the references at {@code dao} which are of type {@link java.util.Collection}
	 *
	 * @param domain        source of the given references
	 * @param dao           object where to add the references
	 * @param mappedObjects map which contains already mapped objects. It will be used while mapping sub entities of {@code domain} to {@code dao}
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefIndirectParentDaoMultiReferences(MultiRefIndirectParent domain, MultiRefIndirectParentDao dao
			, Map<String, IIdentifiableDao> mappedObjects) {
	}

	/**
	 * Adds the references at {@code dao} which are not of type {@link java.util.Collection}
	 *
	 * @param domain        source of the given references
	 * @param dao           object where to add the references
	 * @param mappedObjects map which contains already mapped objects. It will be used while mapping sub entities of {@code domain} to {@code dao}
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefIndirectParentDaoSingleReferences(MultiRefIndirectParent domain, MultiRefIndirectParentDao dao
			, Map<String, IIdentifiableDao> mappedObjects) {
	}

	/**
	 * Takes over values from {@code domain} to {@code dao} which are not of reference type
	 *
	 * @param domain source of the given values
	 * @param dao    object where to set the values
	 */
	protected void setMultiRefIndirectParentDaoValues(MultiRefIndirectParent domain, MultiRefIndirectParentDao dao) {
		dao.setDescription(domain.getDescription());
	}

	/**
	 * Adds the references at {@code domain} which are of type {@link java.util.Collection}
	 *
	 * @param dao           source of the given references
	 * @param domain        object where to add the references
	 * @param mappedObjects map which contains already mapped objects. It will be used while mapping sub entities of {@code dao} to {@code domain}
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefIndirectParentMultiReferences(MultiRefIndirectParentDao dao, MultiRefIndirectParent domain
			, Map<String, IIdentifiable> mappedObjects) {
	}

	/**
	 * Adds the references at {@code domain} which are not of type {@link java.util.Collection}
	 *
	 * @param dao           source of the given references
	 * @param domain        object where to add the references
	 * @param mappedObjects map which contains already mapped objects. It will be used while mapping sub entities of {@code dao} to {@code domain}
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefIndirectParentSingleReferences(MultiRefIndirectParentDao dao, MultiRefIndirectParent domain
			, Map<String, IIdentifiable> mappedObjects) {
	}

	/**
	 * Takes over values from {@code dao} to {@code domain} which are not of reference type
	 *
	 * @param dao    source of the given values
	 * @param domain object where to set the values
	 */
	protected void setMultiRefIndirectParentValues(MultiRefIndirectParentDao dao, MultiRefIndirectParent domain) {
		domain.setDescription(dao.getDescription());
	}

	/**
	 * Adds the references at {@code dao} which are of type {@link java.util.Collection}
	 *
	 * @param domain          source of the given references
	 * @param dao             object where to add the references
	 * @param includeChildren {@code true} if references should be mapped. Otherwise {@code false}
	 * @param mappedObjects   map which contains already mapped objects. It will be used while mapping sub entities of {@code domain} to {@code dao}
	 */
	protected void setMultiRefIndirectSelfReferenceDaoMultiReferences(MultiRefIndirectSelfReference domain, MultiRefIndirectSelfReferenceDao dao
			, boolean includeChildren, Map<String, IIdentifiableDao> mappedObjects) {
		dao.setSelfRefs(new ArrayList<>());
		if (includeChildren) {
			domain.getSelfRefs().forEach(arg ->
					MultiIndirectAccessMapper.convertToMultiRefIndirectSelfReferenceDao(arg, true, dao, mappedObjects)
			);
		}
	}

	/**
	 * Adds the references at {@code dao} which are not of type {@link java.util.Collection}
	 *
	 * @param domain        source of the given references
	 * @param dao           object where to add the references
	 * @param mappedObjects map which contains already mapped objects. It will be used while mapping sub entities of {@code domain} to {@code dao}
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefIndirectSelfReferenceDaoSingleReferences(MultiRefIndirectSelfReference domain, MultiRefIndirectSelfReferenceDao dao
			, Map<String, IIdentifiableDao> mappedObjects) {
	}

	/**
	 * Takes over values from {@code domain} to {@code dao} which are not of reference type
	 *
	 * @param domain source of the given values
	 * @param dao    object where to set the values
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefIndirectSelfReferenceDaoValues(MultiRefIndirectSelfReference domain, MultiRefIndirectSelfReferenceDao dao) {
	}

	/**
	 * Adds the references at {@code domain} which are of type {@link java.util.Collection}
	 *
	 * @param dao             source of the given references
	 * @param domain          object where to add the references
	 * @param includeChildren {@code true} if references should be mapped. Otherwise {@code false}
	 * @param mappedObjects   map which contains already mapped objects. It will be used while mapping sub entities of {@code dao} to {@code domain}
	 */
	protected void setMultiRefIndirectSelfReferenceMultiReferences(MultiRefIndirectSelfReferenceDao dao, MultiRefIndirectSelfReference domain
			, boolean includeChildren, Map<String, IIdentifiable> mappedObjects) {
		if (includeChildren) {
			dao.getSelfRefs().forEach(arg ->
					MultiIndirectAccessMapper.convertToMultiRefIndirectSelfReference(arg.getSubMultiRefIndirectSelfReference(), true, domain, mappedObjects)
			);
		}
	}

	/**
	 * Adds the references at {@code domain} which are not of type {@link java.util.Collection}
	 *
	 * @param dao           source of the given references
	 * @param domain        object where to add the references
	 * @param mappedObjects map which contains already mapped objects. It will be used while mapping sub entities of {@code dao} to {@code domain}
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefIndirectSelfReferenceSingleReferences(MultiRefIndirectSelfReferenceDao dao, MultiRefIndirectSelfReference domain
			, Map<String, IIdentifiable> mappedObjects) {
	}

	/**
	 * Takes over values from {@code dao} to {@code domain} which are not of reference type
	 *
	 * @param dao    source of the given values
	 * @param domain object where to set the values
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefIndirectSelfReferenceValues(MultiRefIndirectSelfReferenceDao dao, MultiRefIndirectSelfReference domain) {
	}

	/**
	 * Adds the references at {@code dao} which are of type {@link java.util.Collection}
	 *
	 * @param domain          source of the given references
	 * @param dao             object where to add the references
	 * @param includeChildren {@code true} if references should be mapped. Otherwise {@code false}
	 * @param mappedObjects   map which contains already mapped objects. It will be used while mapping sub entities of {@code domain} to {@code dao}
	 */
	protected void setMultiRefOtherIndirectParentDaoMultiReferences(MultiRefOtherIndirectParent domain, MultiRefOtherIndirectParentDao dao
			, boolean includeChildren, Map<String, IIdentifiableDao> mappedObjects) {
		dao.setMultiIndirectRef(new ArrayList<>());
		if (includeChildren) {
			domain.getMultiIndirectRef().forEach(arg ->
					MultiIndirectAccessMapper.convertToMultiRefIndirectParentDao(arg, dao, mappedObjects)
			);
		}
	}

	/**
	 * Adds the references at {@code dao} which are not of type {@link java.util.Collection}
	 *
	 * @param domain        source of the given references
	 * @param dao           object where to add the references
	 * @param mappedObjects map which contains already mapped objects. It will be used while mapping sub entities of {@code domain} to {@code dao}
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefOtherIndirectParentDaoSingleReferences(MultiRefOtherIndirectParent domain, MultiRefOtherIndirectParentDao dao
			, Map<String, IIdentifiableDao> mappedObjects) {
	}

	/**
	 * Takes over values from {@code domain} to {@code dao} which are not of reference type
	 *
	 * @param domain source of the given values
	 * @param dao    object where to set the values
	 */
	protected void setMultiRefOtherIndirectParentDaoValues(MultiRefOtherIndirectParent domain, MultiRefOtherIndirectParentDao dao) {
		dao.setDescription(domain.getDescription());
	}

	/**
	 * Adds the references at {@code domain} which are of type {@link java.util.Collection}
	 *
	 * @param dao             source of the given references
	 * @param domain          object where to add the references
	 * @param includeChildren {@code true} if references should be mapped. Otherwise {@code false}
	 * @param mappedObjects   map which contains already mapped objects. It will be used while mapping sub entities of {@code dao} to {@code domain}
	 */
	protected void setMultiRefOtherIndirectParentMultiReferences(MultiRefOtherIndirectParentDao dao, MultiRefOtherIndirectParent domain
			, boolean includeChildren, Map<String, IIdentifiable> mappedObjects) {
		if (includeChildren) {
			dao.getMultiIndirectRef().forEach(arg ->
					MultiIndirectAccessMapper.convertToMultiRefIndirectParent(arg.getMultiRefIndirectParent(), domain, mappedObjects)
			);
		}
	}

	/**
	 * Adds the references at {@code domain} which are not of type {@link java.util.Collection}
	 *
	 * @param dao           source of the given references
	 * @param domain        object where to add the references
	 * @param mappedObjects map which contains already mapped objects. It will be used while mapping sub entities of {@code dao} to {@code domain}
	 */
	@SuppressWarnings("java:S1186")
	protected void setMultiRefOtherIndirectParentSingleReferences(MultiRefOtherIndirectParentDao dao, MultiRefOtherIndirectParent domain
			, Map<String, IIdentifiable> mappedObjects) {
	}

	/**
	 * Takes over values from {@code dao} to {@code domain} which are not of reference type
	 *
	 * @param dao    source of the given values
	 * @param domain object where to set the values
	 */
	protected void setMultiRefOtherIndirectParentValues(MultiRefOtherIndirectParentDao dao, MultiRefOtherIndirectParent domain) {
		domain.setDescription(dao.getDescription());
	}

}
