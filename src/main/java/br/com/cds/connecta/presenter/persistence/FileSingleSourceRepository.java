package br.com.cds.connecta.presenter.persistence;

import br.com.cds.connecta.presenter.entity.FileSingleSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 * @author Heloisa
 */
public interface FileSingleSourceRepository extends JpaRepository<FileSingleSource, Long> {

    @Query("SELECT t FROM FileSingleSource t join fetch t.binaryFile WHERE t.id = :id")
    public FileSingleSource getWithBinary(@Param("id") Long id);

}
