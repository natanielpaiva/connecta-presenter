package br.com.cds.connecta.presenter.controller;

import br.com.cds.connecta.framework.core.controller.AbstractBaseController;
import br.com.cds.connecta.presenter.business.applicationService.IGroupAS;
import br.com.cds.connecta.presenter.entity.Group;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author Nataniel Paiva
 */
@Controller
@RequestMapping("group")
public class GroupController extends AbstractBaseController<Group> {

    @Autowired
    private IGroupAS groupService;

    @Override
    protected ResponseEntity<Group> get(Long id, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        Group group = groupService.get(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<List<Group>> list(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        List<Group> list = groupService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping("single-source/{id}")
    protected ResponseEntity<Group> getSingleSourceByGroupId(
                    @PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response){
    
        Group group = groupService.getSingleSourceByGroupId(id);
        
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Group> save(Group group, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        Group newGroup;
        newGroup = groupService.saveOrUpdate(group);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }

    @Override
    protected ResponseEntity<Group> createWithUpload(MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ResponseEntity<Group> update(Long id, Group group,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Group updateGroup = groupService.saveOrUpdate(group);
        return new ResponseEntity<>(updateGroup, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Group> updateWithUpload(Long id, MultipartHttpServletRequest multipartRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void delete(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        groupService.delete(id);
    }

}
