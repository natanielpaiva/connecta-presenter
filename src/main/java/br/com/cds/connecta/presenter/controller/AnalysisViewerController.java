package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.presenter.bean.analysisviewer.AnalysisViewerResult;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisViewerAS;
import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataExtractorAS;
import br.com.cds.connecta.presenter.entity.viewer.AnalysisViewer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nataniel Paiva
 */
@Controller
@RequestMapping("analysis-viewer")
public class AnalysisViewerController {

    @Autowired
    private IAnalysisViewerAS analysisViewerService;

    @Autowired
    private IDataExtractorAS dataExtratorService;

    @RequestMapping(value = "result/{id}", method = RequestMethod.GET)
    public ResponseEntity<AnalysisViewerResult> getResult(@PathVariable("id") Long id) {
        AnalysisViewer analysisViewer = analysisViewerService.get(id);
        AnalysisViewerResult analysisViewerResult = dataExtratorService.getAnalysisViewerResult(analysisViewer);
        return new ResponseEntity<>(analysisViewerResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AnalysisViewer> get(@PathVariable("id") Long id) {
        AnalysisViewer analysisViewer = analysisViewerService.get(id);
        return new ResponseEntity<>(analysisViewer, HttpStatus.OK);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    protected ResponseEntity<AnalysisViewerResult> save(@RequestBody AnalysisViewer analysisViewer)
            throws Exception {
        AnalysisViewer analysisViewerResultNew = analysisViewerService.saveOrUpdate(analysisViewer);
        AnalysisViewerResult analysisViewerResult = dataExtratorService.getAnalysisViewerResult(analysisViewerResultNew);

        return new ResponseEntity<>(analysisViewerResult, HttpStatus.CREATED);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = "preview")
    protected ResponseEntity<AnalysisViewerResult> getPreview(@RequestBody AnalysisViewer analysisViewer)
            throws Exception {
        AnalysisViewerResult analysisViewerResult = dataExtratorService.getAnalysisViewerResult(analysisViewer);

        return new ResponseEntity<>(analysisViewerResult, HttpStatus.OK);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT)
    protected ResponseEntity<AnalysisViewer> update(@RequestBody AnalysisViewer analysisViewer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AnalysisViewer analysisViewerResult = analysisViewerService.saveOrUpdate(analysisViewer);
        return new ResponseEntity<>(analysisViewerResult, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        analysisViewerService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AnalysisViewer>> list() throws Exception {
        List<AnalysisViewer> analysisViewer = analysisViewerService.list();
        return new ResponseEntity(analysisViewer, HttpStatus.OK);
    }

}
