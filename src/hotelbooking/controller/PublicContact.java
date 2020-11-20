package hotelbooking.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotelbooking.constant.Defines;
import hotelbooking.dao.HotelDao;
import hotelbooking.dao.SlideDao;
import hotelbooking.model.Contact;

@Controller
@RequestMapping("/public")
public class PublicContact {
	@Autowired
	private Defines defines;

	@Autowired
	private SlideDao slideDao;
	
	@Autowired
	private HotelDao hotelDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("listHotels", hotelDao.getListHotel());
	}

	@RequestMapping("/contact")
	public String index() {

		return "public.contact";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String index(@ModelAttribute("contact") Contact contact, RedirectAttributes ra) {
		if (slideDao.InsertContact(contact) > 0) {
			ra.addFlashAttribute("success", "Tạo liên hệ thành công");
		} else {
			ra.addFlashAttribute("error", "Tạo liên hệ thất bại, vui lòng quay lại sau!");
		}
		return "redirect:/public/contact";
	}
}
