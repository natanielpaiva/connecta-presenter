package br.com.cds.connecta.presenter.quartz;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import br.com.cds.connecta.framework.core.util.Util;
import br.com.cds.connecta.presenter.business.applicationService.IQuartzAS;
import br.com.cds.connecta.presenter.entity.QuartzJob;

@Configuration
public class QuartzInitializer {
	
	private static final String ANALYSIS_DEFAULT_NAME = "RefreshAnalysisJob";
	private static final String ANALYSIS_DEFAULT_GROUP = "Connecta";
	private static final int ANALYSIS_DEFAULT_INTERVAL = 10;
	
	@Autowired
	private IQuartzAS quartzService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private QuartzConfiguration quartzConfiguration;

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();

		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws ParseException {
		setConfigurationProperties();
		CronTriggerFactoryBean data = quartzConfiguration.cronTriggerFactoryBean();
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(data.getObject());
		
		return scheduler;
	}
	
	private void setConfigurationProperties(){
		QuartzJob analysisJob = quartzService.findByName(ANALYSIS_DEFAULT_NAME);
		if(Util.isNull(analysisJob)){
			analysisJob = new QuartzJob(ANALYSIS_DEFAULT_NAME, 
							ANALYSIS_DEFAULT_GROUP, ANALYSIS_DEFAULT_INTERVAL);
			
			quartzService.save(analysisJob);
		}
		
		quartzConfiguration.setConfigutarion(analysisJob);
	}
}
