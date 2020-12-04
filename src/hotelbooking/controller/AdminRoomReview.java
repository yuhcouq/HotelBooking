package hotelbooking.controller;

import java.security.Principal;
import java.util.List;

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
import hotelbooking.dao.HotelDao;
import hotelbooking.dao.RoomReviewDao;
import hotelbooking.model.RoomReview;
import hotelbooking.model.User;

@Controller
@RequestMapping("/admin/roomreview")
public class AdminRoomReview {
	@Autowired
	private Defines defines;

	@Autowired
	private HotelDao hotelDao;

	@Autowired
	private RoomReviewDao roomReviewDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
	}

	@RequestMapping("/index")
	public String index(ModelMap model, HttpSession session, RedirectAttributes ra) {
		if (session.getAttribute("userAdmin") != null) {
			User userAdmin = (User) session.getAttribute("userAdmin");
			if (userAdmin.getRole_id() == 1) {
				model.addAttribute("listRoomReviews", roomReviewDao.getListRoomReviews());
				return "admin.roomreview.index";
			}else if(userAdmin.getRole_id() == 2 || userAdmin.getRole_id() == 3){
				if(userAdmin.getHotel_id() != -1) {
					model.addAttribute("listRoomReviews", roomReviewDao.getListRoomReviewHotels(userAdmin.getHotel_id()));
					model.addAttribute("checkrole",2);
					return "admin.roomreview.index";
				}else {
					ra.addFlashAttribute("error", "Bạn không thể thực hiện chức năng này!");
					return "redirect:/admin/index";
				}
			} 
			else {
				ra.addFlashAttribute("error", "Bạn không thể thực hiện chức năng này!");
				return "redirect:/admin/index";
			}
		} else {
			return "redirect:/admin/error403";
		}
	}

	@RequestMapping(value = "/edit/{id_review}", method = RequestMethod.GET)
	public String edit(@PathVariable("id_review") int id_review, ModelMap model) {
		model.addAttribute("listHotels", hotelDao.getListHotel());
		model.addAttribute("roomreview", roomReviewDao.getRoomReview(id_review));
		return "admin.roomreview.edit";
	}

	@RequestMapping(value = "/edit/{id_review}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("roomReview") RoomReview roomReview, @PathVariable("id_review") int id_review,
			RedirectAttributes ra) {

		roomReview.setId_review(id_review);
		if (roomReviewDao.editRoomReview(roomReview) > 0) {
			ra.addFlashAttribute("sucess", "Cập nhật đánh giá thành công!");
		} else {
			ra.addFlashAttribute("error", "\"Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!\"");
		}

		return "redirect:/admin/roomreview/index";
	}
	
	@RequestMapping(value = "/request/{id_review}", method = RequestMethod.GET)
	public String editrequest(@PathVariable("id_review") int id_review, ModelMap model,RedirectAttributes ra) {
		
		if (roomReviewDao.editRoomReviewRequest(id_review) > 0) {
			ra.addFlashAttribute("success", "Yêu cầu xóa thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}
		return "redirect:/admin/hotelreview/index";
	}

	@RequestMapping(value = "/del/{id_review}", method = RequestMethod.GET)
	public String del(@PathVariable("id_review") int id_review, RedirectAttributes ra) {
		if (roomReviewDao.delRoomReview(id_review) > 0) {
			ra.addFlashAttribute("msg", "Xóa đánh giá thành công!");
		} else {
			ra.addFlashAttribute("error", "\"Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!\"");
		}
		return "redirect:/admin/roomreview/index";
	}
}
