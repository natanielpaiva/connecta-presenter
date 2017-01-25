package br.com.cds.connecta.presenter.persistence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cds.connecta.presenter.entity.FileSingleSource;

public interface FileSingleSourceRepository extends JpaRepository<FileSingleSource, Serializable> {
	
	@Query("SELECT t FROM FileSingleSource t join fetch t.binaryFile WHERE t.id = :id")
	FileSingleSource getWithBinary(@Param ("id") long id);
	

}
