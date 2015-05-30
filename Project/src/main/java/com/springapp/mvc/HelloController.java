package com.springapp.mvc;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    MaterialDao materialDao;

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        List<Material> materials = materialDao.findAll();
		model.addAttribute("message", "Hello world!");
        model.addAttribute("materials",materials);
		return "hello";
	}
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
     public String upload(ModelMap model,
                          @RequestParam CommonsMultipartFile[] fileUpload) {
        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                Material material = new Material();
                material.setName(aFile.getOriginalFilename());
                material.setMaterial(aFile.getBytes());
                materialDao.persist(material);
            }
        }
        return "hello";
    }
    @RequestMapping(value = "/download/{id}",method = RequestMethod.GET)
    public String upload(ModelMap model,HttpServletResponse response,
                         @PathVariable Integer id) {
        Material material = materialDao.find(id);
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" +material.getName()+ "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(material.getName());
            IOUtils.copy(new ByteArrayInputStream(material.getMaterial()), out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}