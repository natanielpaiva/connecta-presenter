package br.com.cds.connecta.presenter.business.applicationService.impl;

import static br.com.cds.connecta.framework.core.util.Util.isNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.business.aplicationService.common.AbstractBaseAS;
import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import br.com.cds.connecta.presenter.business.applicationService.IHierarchyAS;
import br.com.cds.connecta.presenter.entity.hierarchy.Hierarchy;
import br.com.cds.connecta.presenter.persistence.HierarchyRepository;
import br.com.cds.connecta.presenter.persistence.impl.BulkActionsRepository;

@Service
public class HierarchyAS extends AbstractBaseAS<Hierarchy> implements IHierarchyAS {

	@Autowired
	private BulkActionsRepository bulk;

	@Autowired
	private HierarchyRepository hierarchyRepository;

	/**
	 *
	 * @param hierarchy
	 * @return
	 */
	@Override
	public Hierarchy saveOrUpdate(Hierarchy hierarchy) {
		return hierarchyRepository.save(hierarchy);
	}

	/**
	 *
	 * @param id
	 *            Long
	 * @return
	 */
	@Override
	public Hierarchy get(Long id) {
		Hierarchy h;

		h = hierarchyRepository.getOne(id);

		if (isNull(h)) {
			throw new ResourceNotFoundException(Hierarchy.class.getCanonicalName());
		}

		return h;
	}

	/**
	 *
	 * @return List Hierarchy
	 */
	@Override
	public List<Hierarchy> list() {
		return hierarchyRepository.findAll();
	}

	/**
	 * deleta uma Hierarchy
	 *
	 * @param id
	 *            Long
	 */
	@Override
	public void delete(Long id) {
		hierarchyRepository.delete(id);
	}

	@Override
	public void delete(Hierarchy entity) {
		hierarchyRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<Long> ids) {
		bulk.delete(Hierarchy.class, ids);
	}

}
