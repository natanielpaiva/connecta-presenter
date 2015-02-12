package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.FileSingleSource;
import java.util.List;

public interface IFileDAO extends IBaseJpaDAO<FileSingleSource>{

	public abstract List<FileSingleSource> list();

}