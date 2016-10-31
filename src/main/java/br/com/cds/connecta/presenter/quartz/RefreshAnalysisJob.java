package br.com.cds.connecta.presenter.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.cds.connecta.presenter.bean.analysis.AnalysisExecuteRequest;
import br.com.cds.connecta.presenter.business.applicationService.IAnalysisAS;
import br.com.cds.connecta.presenter.business.applicationService.dataExtractor.IDataExtractorAS;
import br.com.cds.connecta.presenter.entity.analysis.Analysis;

public class RefreshAnalysisJob extends QuartzJobBean {

	@Autowired
	private IAnalysisAS analysisService;

	@Autowired
	private IDataExtractorAS extractor;
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

	private final Logger logger = Logger.getLogger(RefreshAnalysisJob.class);

	public IAnalysisAS getAnalysisService() {
		return analysisService;
	}

	public void setAnalysisService(IAnalysisAS analysisService) {
		this.analysisService = analysisService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		List<Analysis> cachedAnalysis = (List<Analysis>) getAnalysisService().listCached();
		List<Long> analysisUpdated = new ArrayList<>();
		
		for(Analysis analysis : cachedAnalysis){
			if(!analysisUpdated.contains(analysis.getId())){
				try{
					AnalysisExecuteRequest analysisExecuteRequest = new AnalysisExecuteRequest();
					analysisExecuteRequest.setAnalysis(analysis);
					analysisExecuteRequest.setUpdatingCache(true);
					
					logger.info("refreshing analysis " + analysis.getId());
					List<Map<String, Object>> analysisResult = extractor.executeAnalysis(analysisExecuteRequest);
					sendResultToConnectedClients(analysisResult, analysis.getId());
					logger.info("analysis " + analysis.getId() + " updated");
					analysisUpdated.add(analysis.getId());
				}catch(Exception e){
					logger.error("Erro ao tentar atualizar a analise " + analysis.getId(), e);
				}	
			}
		}		
	}
	
	private void sendResultToConnectedClients(List<Map<String, Object>> analysisResult, Long analysisId){
	    messagingTemplate.convertAndSend("/topic/analysis/" + analysisId, analysisResult);
	}

}
