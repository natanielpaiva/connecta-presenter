package br.com.cds.connecta.presenter.business.applicationService.impl;

import static br.com.cds.connecta.framework.core.util.Util.isNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.framework.core.exception.ResourceNotFoundException;
import br.com.cds.connecta.presenter.business.applicationService.IDatasourceAS;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;
import br.com.cds.connecta.presenter.entity.datasource.Datasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasource;
import br.com.cds.connecta.presenter.entity.datasource.WebserviceDatasourceParameter;
import br.com.cds.connecta.presenter.filter.DatasourceFilter;
import br.com.cds.connecta.presenter.persistence.AnalysisRepository;
import br.com.cds.connecta.presenter.persistence.DatasourceRepository;
import br.com.cds.connecta.presenter.persistence.WebserviceDatasourceParameterRepository;
import br.com.cds.connecta.presenter.persistence.specification.DataSourceSpecification;

/**
 *
 * @author diego
 */
@Service
public class DatasourceAS implements IDatasourceAS {

	@Autowired
	private DatasourceRepository dsRepository;

	@Autowired
	private AnalysisRepository asRepository;

	@Autowired
	private WebserviceDatasourceParameterRepository datasourceParameterRepository;

	private List<Analysis> findByDatasource;

	@Override
	public Datasource save(Datasource datasource) {
		return dsRepository.save(datasource);
	}

	@Override
	public Iterable<Datasource> list(DatasourceFilter filter) {
		Pageable pageable = filter.makePageable();
		return dsRepository.findAll(DataSourceSpecification.byDomain(filter.getDomain()), pageable);
	}

	@Override
	public Datasource get(Long id, String domain) {
		Datasource ds = (Datasource) dsRepository.findOne(DataSourceSpecification.byIdAndDomain(id, domain));

		if (isNull(ds)) {
			throw new ResourceNotFoundException(Datasource.class.getSimpleName());
		}

		if (ds instanceof WebserviceDatasource) {
			WebserviceDatasource wds = (WebserviceDatasource) ds;
			List<WebserviceDatasourceParameter> parameters = datasourceParameterRepository
					.findAllByWebserviceDatasource(wds);
			wds.setParameters(parameters);
			return wds;
		} else {
			return ds;
		}
	}

	@Override
	public void delete(Long id, String domain) {
		Datasource ds = get(id, domain);

		if (ds instanceof WebserviceDatasource) {
			WebserviceDatasource wds = (WebserviceDatasource) ds;
			_delete(wds);
		} else {
			_delete(ds);
		}
	}

	@Override
	public void deleteAll(List<Long> ids, String domain) {
		List<Datasource> listDs = dsRepository.findAll(DataSourceSpecification.byIdsAndDomain(ids, domain));
		for (Datasource datasource : listDs) {
			findByDatasource = asRepository.findByDatasource(datasource);

			if (!findByDatasource.isEmpty()) {
				throw new DataIntegrityViolationException("");
			}
		}

		dsRepository.delete(listDs);

	}

	private void _delete(WebserviceDatasource ds) {
		List<WebserviceDatasourceParameter> parameters = datasourceParameterRepository
				.findAllByWebserviceDatasource(ds);
		datasourceParameterRepository.delete(parameters);
		dsRepository.delete(ds);
	}

	private void _delete(Datasource ds) {
		List<Analysis> findByDatasource = asRepository.findByDatasource(ds);

		if (findByDatasource.isEmpty()) {
			dsRepository.delete(ds);
		} else {
			throw new DataIntegrityViolationException("");
		}

	}

}
