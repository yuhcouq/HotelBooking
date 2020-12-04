package hotelbooking.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hotelbooking.constant.Defines;
import hotelbooking.dao.BookingDao;
import hotelbooking.dao.HotelDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.model.Booking;
import hotelbooking.model.Check;
import hotelbooking.model.User;

@Controller
@RequestMapping("/admin/statistical")
public class AdminStatistical {
	@Autowired
	private Defines defines;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private HotelDao hotelDao;

	@Autowired
	private BookingDao bookingDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("listHotels", hotelDao.getListHotel());
	}

	@RequestMapping("/index")
	public String index(ModelMap model, HttpSession session) {
		if (session.getAttribute("userAdmin") != null) {
			User userAdmin = (User) session.getAttribute("userAdmin");
			List<Booking> listBooking = null;
			if (userAdmin.getRole_id() == 1) {
				listBooking = bookingDao.getAllBooking();
			} else {
				listBooking = bookingDao.getAllBookingOfHotel(userAdmin.getHotel_id());
			}
			if (listBooking.size() > 0) {
				for (int i = 0; i < listBooking.size(); i++) {
					listBooking.get(i).setRoom(roomDao.getRoom(listBooking.get(i).getRoom_id()));
					listBooking.get(i)
							.setHotel_name(hotelDao.getHotel(listBooking.get(i).getHotel_id()).getHotel_name());
				}
			}

			Check check = new Check();
			check.setCheck(0);
			model.addAttribute("check", check);
			model.addAttribute("listBooking", listBooking);
			return "admin.booking.statistical";
		} else {
			return "redirect:/admin/error403";
		}
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String check(@ModelAttribute("check") Check check, ModelMap model, HttpSession session) {
		if (session.getAttribute("userAdmin") != null) {
			List<Booking> listBooking = null;
			User userAdmin = (User) session.getAttribute("userAdmin");
			if (userAdmin.getRole_id() == 1) {
				if (check.getHotel_id() == 0) {
					if (check.getCheck() == 0) {
						listBooking = bookingDao.getAllBooking(check);
					} else if (check.getCheck() == 1) {
						listBooking = bookingDao.getAllBookingByRoomMax(check);
					} else if (check.getCheck() == 2) {
						listBooking = bookingDao.getAllBookingByRoomMin(check);
					}
				} else {
					if (check.getCheck() == 0) {
						listBooking = bookingDao.getAllBooking(check.getHotel_id(), check);
					} else if (check.getCheck() == 1) {
						listBooking = bookingDao.getAllBookingByRoomMax(check.getHotel_id(), check);
					} else if (check.getCheck() == 2) {
						listBooking = bookingDao.getAllBookingByRoomMin(check.getHotel_id(), check);
					}
				}
			} else {
				if (check.getCheck() == 0) {
					listBooking = bookingDao.getAllBookingOfHotel(check, userAdmin.getHotel_id());
				} else if (check.getCheck() == 1) {
					listBooking = bookingDao.getAllBookingByRoomOfHotelMax(check, userAdmin.getHotel_id());
				} else if (check.getCheck() == 2) {
					listBooking = bookingDao.getAllBookingByRoomOfHotelMin(check, userAdmin.getHotel_id());
				}
			}
			if (listBooking.size() > 0) {
				for (int i = 0; i < listBooking.size(); i++) {
					listBooking.get(i).setRoom(roomDao.getRoom(listBooking.get(i).getRoom_id()));
					listBooking.get(i)
							.setHotel_name(hotelDao.getHotel(listBooking.get(i).getHotel_id()).getHotel_name());
					if ((check.getCheck() == 1) || (check.getCheck() == 2)) {
						listBooking.get(i).setTotal_price(listBooking.get(i).getPrice());
					}
				}
			}
			model.addAttribute("check", check);
			model.addAttribute("listBooking", listBooking);
			model.addAttribute("find", 1);
		} else {
			return "redirect:/admin/error403";
		}
		return "admin.booking.statistical";
	}
}
