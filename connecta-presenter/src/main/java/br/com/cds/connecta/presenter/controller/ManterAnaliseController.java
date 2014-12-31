package br.com.cds.connecta.presenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.cds.connecta.framework.core.controller.AbstractBaseController;
import br.com.cds.connecta.presenter.business.applicationService.IManterAnaliseAS;
import br.com.cds.connecta.presenter.entity.Analysis;

@Controller("ManterAnaliseController")
@RequestMapping("/presenter/rest/manterAnalise")
public class ManterAnaliseController extends AbstractBaseController<Analysis>{
	
	@Autowired
	private IManterAnaliseAS manterAnaliseAS;
	
	@Override
	protected Analysis save(Analysis analysi, HttpServletRequest arg1,
			HttpServletResponse arg2) throws Exception {
		return getManterAnaliseAS().saveOrUpdate(analysi);
	}

	@Override
	protected Analysis update(Long arg0, Analysis analysi, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception {
		return getManterAnaliseAS().saveOrUpdate(analysi);
	}

	@Override
	protected void delete(Long id, HttpServletRequest arg1,
			HttpServletResponse arg2) throws Exception {
		getManterAnaliseAS().delete(id);
		
	}

	@Override
	protected Analysis get(Long id, HttpServletRequest arg1,
			HttpServletResponse arg2) throws Exception {
		return getManterAnaliseAS().get(id);
	}

	@Override
	protected List<Analysis> list(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Analysis updateWithUpload(Long arg0,
			MultipartHttpServletRequest arg1, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Analysis createWithUpload(MultipartHttpServletRequest arg0,
			HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public IManterAnaliseAS getManterAnaliseAS() {
		return manterAnaliseAS;
	}

	public void setManterAnaliseAS(IManterAnaliseAS manterAnaliseAS) {
		this.manterAnaliseAS = manterAnaliseAS;
	}

}
