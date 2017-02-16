package br.com.cds.connecta.presenter.business.applicationService;

import br.com.cds.connecta.presenter.entity.FileSingleSource;
import java.util.List;

import br.com.cds.connecta.presenter.entity.SingleSource;
import br.com.cds.connecta.presenter.entity.UrlSingleSource;
import br.com.cds.connecta.presenter.filter.SingleSourceFilter;
import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ISingleSourceAS {

    SingleSource get(Long id, String domain);

    Iterable<SingleSource> list(SingleSourceFilter filter);

    SingleSource save(SingleSource entity);

    void delete(Long id, String domain);

    void delete(SingleSource singleSource, String domain);

    void validate(FileSingleSource fileSingleSource);

    void validate(UrlSingleSource url);
    
    void preValidate(FileSingleSource fileSingleSource, MultipartFile file) throws IOException;

    FileSingleSource getFileWithBinary(Long id);
    
    List<SingleSource> getByAttributeId(Long id);
    
    Page<SingleSource> listAutoComplete(SingleSourceFilter filter);

    void deleteAll(List<Long> ids, String domain);

}
