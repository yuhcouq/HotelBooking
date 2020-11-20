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
import hotelbooking.dao.BookingDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.model.Booking;
import hotelbooking.model.Check;
import hotelbooking.model.Room;
import hotelbooking.model.User;

@Controller
@RequestMapping("/admin/booking")
public class AdminBooking {
	@Autowired
	private Defines defines;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private RoomDao roomDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
	}

	@RequestMapping("/index")
	public String index(ModelMap model, HttpSession session) {
		if (session.getAttribute("userAdmin") != null) {
			User userAdmin = (User) session.getAttribute("userAdmin");
			if (userAdmin.getRole_id() == 1) {
				model.addAttribute("listBooking", bookingDao.getAllBooking(defines.getDateDay()));
			} else {
				model.addAttribute("listBooking",
						bookingDao.getAllBookingOfHotel(defines.getDateDay(), userAdmin.getHotel_id()));
			}
			return "admin.booking.index";
		} else {
			return "redirect:/admin/error403";
		}
	}

	@RequestMapping("/views/{code}")
	public String views(@PathVariable("code") int code, ModelMap model, RedirectAttributes ra) {
		List<Booking> listBookingDetail = bookingDao.getAllBooking(code);
		if (listBookingDetail.size() != 0) {
			for (int i = 0; i < listBookingDetail.size(); i++) {
				listBookingDetail.get(i).setRoom(roomDao.getRoom(listBookingDetail.get(i).getRoom_id()));
			}
			model.addAttribute("listBookingDetail", listBookingDetail);
			String customer_name = listBookingDetail.get(0).getLastname() + " "
					+ listBookingDetail.get(0).getFirstname();
			model.addAttribute("customer_name", customer_name);
			model.addAttribute("customer_phone", listBookingDetail.get(0).getPhone());
		} else {
			ra.addFlashAttribute("success", "Hủy thành công!");
			return "redirect:/admin/booking/index";
		}
		return "admin.booking.views";
	}

	@RequestMapping("/payment/{code}")
	public String payment(@PathVariable("code") int code, RedirectAttributes ra) {
		if (bookingDao.Payment(code) > 0) {
			ra.addFlashAttribute("success",
					"Thanh toán thành công đơn <span style='color: #1CC3B2;'>" + code + "</span>");
		} else {
			ra.addFlashAttribute("error",
					"Thanh toán không thành công đơn <span style='color: red;'>" + code + "</span>");
		}
		return "redirect:/admin/booking/index";
	}
	
	@RequestMapping("/paid/{id}")
	public String paid(@PathVariable("id") int id, RedirectAttributes ra, HttpSession session) {
		User userAdmin = (User) session.getAttribute("userAdmin");
		if(userAdmin.getRole_id()!=4) {
			bookingDao.UpdatePaid(id);
		}
		session.setAttribute("listBooking", bookingDao.getAllBookingOfHotel(defines.getDateDay(), userAdmin.getHotel_id()));
		return "redirect:/admin/booking/index";
	}

	@RequestMapping("/del/{code}")
	public String delete(@PathVariable("code") int code, RedirectAttributes ra) {
		if (bookingDao.Delete(code) > 0) {
			ra.addFlashAttribute("success", "Xóa thành công đơn <span style='color: #1CC3B2;'>" + code + "</span>");
		} else {
			ra.addFlashAttribute("error", "Xóa không thành công đơn <span style='color: red;'>" + code + "</span>");
		}
		return "redirect:/admin/booking/index";
	}

	@RequestMapping("/cancel/{code}/{id_booking}")
	public String cancel(@PathVariable("code") int code, @PathVariable("id_booking") int id_booking,
			RedirectAttributes ra) {
		if (bookingDao.Cancel(id_booking) > 0) {
			ra.addFlashAttribute("success", "Hủy thành công!");
		} else {
			ra.addFlashAttribute("error", "Hủy không thành!");
		}
		return "redirect:/admin/booking/views/" + code;
	}

	@RequestMapping("/booking-add")
	public String CreatedBooking(ModelMap model, HttpSession session) {
		User userAdmin = (User) session.getAttribute("userAdmin");
		model.addAttribute("listRooms", roomDao.getAllRoomsOfHotel(userAdmin.getHotel_id()));
		return "admin.booking.booking_add";
	}

	@RequestMapping(value = "/booking-add", method = RequestMethod.POST)
	public String CreatedBooking(@ModelAttribute("booking") Booking booking, HttpSession session) {
		Room room = roomDao.getRoom(booking.getRoom_id());

		Check check = new Check();
		check.setCheckin(booking.getCheckin());
		check.setCheckout(booking.getCheckout());
		int day = defines.checkQuantityDate(check);

		booking.setRoom_number(room.getRoom_number());
		booking.setImage(room.getImage());
		booking.setHotel_id(room.getHotel_id());
		booking.setHotel_name(room.getHotel_name());
		booking.setPrice(room.getPrice());
		booking.setTotal_price(room.getPrice() * day);
		booking.setDay(day);
		booking.setPrepayment(room.getPrepayment());
		session.setAttribute("customerBooking", booking);

		return "admin.booking.customer_add";
	}

	@RequestMapping(value = "/customer-add", method = RequestMethod.POST)
	public String CreatedCustomer(@ModelAttribute("user") User user, HttpSession session, RedirectAttributes ra) {
		Booking booking = (Booking) session.getAttribute("customerBooking");
		booking.setCheck_move(2);
		booking.setCreated_time(defines.getDateDay());
		user.setNote(booking.getNote());
		do {
			Booking.setCode_auto(Booking.getCode_auto() + 1);
		} while (bookingDao.CheckCode(Booking.getCode_auto()) != 0);

		if (bookingDao.insertBooking(booking, user) > 0) {
			ra.addFlashAttribute("success", "Đặt phòng thành công!");

		} else {
			ra.addFlashAttribute("error", "Đặt phòng thất bại, vui lòng quay lại sau!");
		}
		return "redirect:/admin/booking/index";
	}

	@RequestMapping("/customer-edit/{code}")
	public String EditdCustomer(@PathVariable("code") int code, ModelMap model) {
		model.addAttribute("booking", bookingDao.getCusromerBooking(code));
		return "admin.booking.customer_edit";
	}

	@RequestMapping(value = "/customer-edit/{code}", method = RequestMethod.POST)
	public String EditdCustomer(@ModelAttribute("booking") Booking booking, ModelMap model, RedirectAttributes ra) {
		if (bookingDao.UpdateCustomer(booking) > 0) {
			ra.addFlashAttribute("success", "Cập nhật thành công!");
		} else {
			ra.addFlashAttribute("error", "Cập nhật thất bại, vui lòng quay lại sau!");
		}
		return "redirect:/admin/booking/index";
	}

	@RequestMapping("/booking-edit/{id_booking}")
	public String EditdBooking(@PathVariable("id_booking") int id_booking, ModelMap model) {
		Booking booking = bookingDao.getBooking(id_booking);
		model.addAttribute("booking", booking);
		model.addAttribute("listRooms", roomDao.getAllRoomsOfHotel(booking.getHotel_id()));
		return "admin.booking.booking_edit";
	}

	@RequestMapping(value = "/booking-edit/{id_booking}/{code}", method = RequestMethod.POST)
	public String EditdBooking(@PathVariable("code") int code, @ModelAttribute("booking") Booking booking, ModelMap model, RedirectAttributes ra) {
		Room room = roomDao.getRoom(booking.getRoom_id());

		Check check = new Check();
		check.setCheckin(booking.getCheckin());
		check.setCheckout(booking.getCheckout());
		int day = defines.checkQuantityDate(check);

		booking.setDay(day);
		booking.setTotal_price(room.getPrice() * day);
		booking.setRoom(room);
		booking.setPaid(room.getPrepayment());

		if (bookingDao.UpdateBoooking(booking) > 0) {
			ra.addFlashAttribute("success", "Cập nhật thành công!");
		} else {
			ra.addFlashAttribute("error", "Cập nhật thất bại, vui lòng quay lại sau!");
		}
		return "redirect:/admin/booking/views/" + code;
	}
}
