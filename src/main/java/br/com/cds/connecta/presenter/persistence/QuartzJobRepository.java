package br.com.cds.connecta.presenter.persistence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cds.connecta.presenter.entity.QuartzJob;

@Repository
public interface QuartzJobRepository extends JpaRepository<QuartzJob, Serializable>{
	
	QuartzJob findByName(String name);
    
}
