package hotelbooking.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotelbooking.constant.Defines;
import hotelbooking.dao.SlideDao;
import hotelbooking.model.Hotel;
import hotelbooking.model.Slide;
import hotelbooking.model.User;

@Controller
@RequestMapping("/admin/slide")
public class AdminSlide {
	@Autowired
	private Defines defines;
	
	@Autowired
	private SlideDao slideDao;
	
	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
	}
	
	@RequestMapping("/index")
	public String index(ModelMap model, HttpSession session, RedirectAttributes ra) {
		User userAdmin = (User) session.getAttribute("userAdmin");
		if(userAdmin.getRole_id() == 1) {
			model.addAttribute("listSlide", slideDao.getListSlide());
			return "admin.slide.index";
		}
		ra.addFlashAttribute("error", "Bạn không thể thực hiện chức năng này!");
		return "redirect:/admin/index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		return "admin.slide.add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam("picture") CommonsMultipartFile cmf,
			HttpServletRequest request, RedirectAttributes ra, ModelMap model) {
		Slide slide = new Slide();
		String fileName = cmf.getOriginalFilename();

		String reFileName = Defines.renameFile(fileName);
		String appPath = request.getServletContext().getRealPath("");
		String dirPath = appPath + Defines.DIR_UPLOAD;
		File dirFile = new File(dirPath);
		if (dirFile.exists()) {
			dirFile.mkdir();
		}
		String filePath = dirPath + File.separator + reFileName;
		try {
			cmf.transferTo(new File(filePath));
		} catch (IOException e) {
			System.out.println("Error");
		}
		slide.setImage(reFileName);
		slide.setStatus(0);
		if (slideDao.addSlide(slide) > 0) {
			ra.addFlashAttribute("success", "Thêm mới slide thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}

		return "redirect:/admin/slide/index";
	}
	
	@RequestMapping(value = "/edit/{id_slide}", method = RequestMethod.GET)
	public String edit(@PathVariable("id_slide") int id_slide, ModelMap model) {
		model.addAttribute("slide", slideDao.getSlide(id_slide));
		return "admin.slide.edit";
	}
	
	@RequestMapping(value = "/edit/{id_slide}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("slide") Slide slide, @PathVariable("id_slide") int id_slide,
			@RequestParam("picture") CommonsMultipartFile cmf, HttpServletRequest request, RedirectAttributes ra) {
		Slide slideOld = slideDao.getSlide(id_slide);
		String fileNameOld = slideOld.getImage();
		String fileName = cmf.getOriginalFilename();
		String reFileName = Defines.renameFile(fileName);
		if (!"".equals(fileName)) {
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + Defines.DIR_UPLOAD;
			String filePath = dirPath + File.separator + reFileName;
			try {
				if ("".equals(fileNameOld)) {
					File fileOld = new File(dirPath + File.separator + fileNameOld);
					fileOld.delete();
				}
				cmf.transferTo(new File(filePath));
			} catch (Exception e) {
				return "redirect:/admin/slide/index";
			}
		} else {
			reFileName = fileNameOld;
		}
		slide.setImage(reFileName);
		if (slideDao.editSlide(slide) > 0) {
			ra.addFlashAttribute("msg", "Cập nhật slide thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}

		return "redirect:/admin/slide/index";
	}
	
	@RequestMapping(value = "/del/{id_slide}", method = RequestMethod.GET)
	public String del(@PathVariable("id_slide") int id_slide, HttpServletRequest request,
			RedirectAttributes ra) {
		Slide hotel = slideDao.getSlide(id_slide);
		String fileNameOld = hotel.getImage();
		String appPath = request.getServletContext().getRealPath("");
		String dirPath = appPath + Defines.DIR_UPLOAD;
		String filePath = dirPath + File.separator + fileNameOld;
		if ("".equals(filePath)) {
			File file = new File(filePath);
			file.delete();
		}
		if (slideDao.delSlide(id_slide) > 0) {
			ra.addFlashAttribute("msg", "Xóa slide thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}
		return "redirect:/admin/slide/index";
	}
}
