package br.com.cds.connecta.presenter.business.applicationService.impl;

import java.util.List;

import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import br.com.cds.connecta.presenter.business.applicationService.IQuartzAS;
import br.com.cds.connecta.presenter.entity.QuartzJob;
import br.com.cds.connecta.presenter.persistence.QuartzJobRepository;

@Service
public class QuartzAS implements IQuartzAS {

    @Autowired
    private QuartzJobRepository quartzRepository;
    
    @Autowired
    private ApplicationContext appContext;
    
	private SchedulerFactoryBean schedulerFactory;
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void rescheduleJob(QuartzJob job) throws SchedulerException {
		
		schedulerFactory = appContext.getBean(SchedulerFactoryBean.class);
		
		List<? extends Trigger> triggerList = schedulerFactory.getScheduler()
				.getTriggersOfJob(new JobKey(job.getName(), job.getGroup()));

		schedulerFactory.getScheduler().getCurrentlyExecutingJobs();

		TriggerBuilder tb = triggerList.get(0).getTriggerBuilder();

		Trigger newTrigger = tb.withSchedule(
				SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInMinutes(job.getInterval()))
				.build();

		schedulerFactory.getScheduler().rescheduleJob(triggerList.get(0).getKey(), newTrigger);
		
		quartzRepository.save(job);
	}
	
	@Override
	public QuartzJob get(Long id) {
		return quartzRepository.findOne(id);
	}

	@Override
	public Iterable<QuartzJob> list() {
		return quartzRepository.findAll();
	}
	
	@Override
	public QuartzJob findByName(String name){
		return quartzRepository.findByName(name);
	}

	@Override
	public QuartzJob save(QuartzJob job) {
		return quartzRepository.save(job);
	}

}
