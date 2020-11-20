package hotelbooking.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotelbooking.constant.Defines;
import hotelbooking.dao.CityDao;
import hotelbooking.model.City;

@Controller
@RequestMapping("/admin/city")
public class AdminCity {
	@Autowired
	private Defines defines;

	@Autowired
	private CityDao cityDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		// modelMap.addAttribute("listCities", hotelDao.getListCities());
		// User user = userDao.getUser(principal.getName());
		// session.setAttribute("user", user);
	}
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		model.addAttribute("listCities", cityDao.getListCities());
		return "admin.city.index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		return "admin.city.add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("city") City city, RedirectAttributes ra) {
		if (cityDao.addCity(city) > 0) {
			ra.addFlashAttribute("success", "Thêm mới thành phố thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}

		return "redirect:/admin/city/index";
	}
	
	@RequestMapping(value = "/edit/{id_city}", method = RequestMethod.GET)
	public String edit(@PathVariable("id_city") int id_city, ModelMap model) {
		model.addAttribute("city", cityDao.getCity(id_city));
		return "admin.city.edit";
	}
	
	@RequestMapping(value = "/edit/{id_city}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("city") City city, @PathVariable("id_city") int id_city, RedirectAttributes ra) {

		city.setId_city(id_city);
		if (cityDao.editCity(city) > 0) {
			ra.addFlashAttribute("success", "Cập nhật thông tin thành phố thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}

		return "redirect:/admin/city/index";
	}
	
	@RequestMapping(value = "/del/{id_city}", method = RequestMethod.GET)
	public String del(@PathVariable("id_city") int id_city, RedirectAttributes ra) {
		if (cityDao.delCity(id_city) > 0) {
			ra.addFlashAttribute("success", "Xóa thành phố thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}
		return "redirect:/admin/city/index";
	}
}
