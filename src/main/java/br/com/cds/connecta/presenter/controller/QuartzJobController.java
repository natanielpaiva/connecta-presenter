package br.com.cds.connecta.presenter.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cds.connecta.presenter.business.applicationService.IQuartzAS;
import br.com.cds.connecta.presenter.entity.QuartzJob;

@Controller
@RequestMapping("quartzJob")
public class QuartzJobController {
	
	@Autowired
	private IQuartzAS quartzService;
	
	@RequestMapping(value = "reschedule", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> rescheduleJob(@RequestBody QuartzJob job) throws SchedulerException{
		quartzService.rescheduleJob(job);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public ResponseEntity<QuartzJob> getByName(@PathVariable("name") String name) {
		QuartzJob job = quartzService.findByName(name);
		return new ResponseEntity<>(job, HttpStatus.OK);
	}

}
