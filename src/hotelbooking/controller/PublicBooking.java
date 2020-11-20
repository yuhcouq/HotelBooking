package hotelbooking.controller;

import java.security.Principal;
import java.util.ArrayList;
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
import hotelbooking.dao.HotelDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.dao.UserDao;
import hotelbooking.model.Booking;
import hotelbooking.model.Check;
import hotelbooking.model.Room;
import hotelbooking.model.User;

@Controller
@RequestMapping("/public/booking")
public class PublicBooking {
	@Autowired
	private Defines defines;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private HotelDao hotelDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BookingDao bookingDao;

	private ArrayList<Booking> listBooking;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("listHotels", hotelDao.getListHotel());
	}

	@RequestMapping(value = "/index")
	public String cart(HttpSession session, ModelMap model) {
		if (session.getAttribute("listBooking") == null) {
			session.setAttribute("soluong", 0);
		}
		return "public.cartbooking";
	}

	@RequestMapping(value = "add/{id_room}", method = RequestMethod.POST)
	public String addcart(@PathVariable("id_room") int id_room, @ModelAttribute("check") Check check,
			HttpSession session, RedirectAttributes ra) {
		if (session.getAttribute("listBooking") != null) {
			listBooking = (ArrayList<Booking>) session.getAttribute("listBooking");
		} else {
			listBooking = new ArrayList<Booking>();
		}
		if ((!"".equals(check.getCheckin())) || (!"".equals(check.getCheckout()))) {
			if (defines.checkDate(check) && defines.checkDateCheckIn(check)) {
				int day = defines.checkQuantityDate(check);
				if ((day > 0) && (day < 11)) {
					int soLuong;
					if (session.getAttribute("soluong") != null) {
						soLuong = (int) session.getAttribute("soluong");
					} else {
						soLuong = 0;
					}

					boolean checkTemp = false;
					if (session.getAttribute("listBooking") != null) {
						listBooking = (ArrayList<Booking>) session.getAttribute("listBooking");
						for (Booking booking : listBooking) {
							if (id_room == booking.getRoom_id()) {
								ra.addFlashAttribute("msg",
										"<div class='alert alert-danger' role='alert'>Phòng đã có trong danh sách booking!</div>");
								checkTemp = true;
							}
						}
					}
					if (checkTemp == false) {
						soLuong++;
						Room room = roomDao.getRoom(id_room);
						Booking booking = new Booking();
						booking.setRoom_id(id_room);
						booking.setRoom_number(room.getRoom_number());
						booking.setImage(room.getImage());
						booking.setHotel_id(room.getHotel_id());
						booking.setHotel_name(room.getHotel_name());
						booking.setPrice(room.getPrice());
						booking.setTotal_price(room.getPrice() * day);
						booking.setCheckin(check.getCheckin());
						booking.setCheckout(check.getCheckout());
						booking.setDay(day);
						booking.setPrepayment(room.getPrepayment());

						listBooking.add(booking);

						session.setAttribute("soluong", soLuong);
						session.setAttribute("listBooking", listBooking);

						ra.addFlashAttribute("msg",
								"<div class='alert alert-success' role='alert'> Thêm phòng thành công! </div>");
					}
				} else {
					ra.addFlashAttribute("msg",
							"<div class='alert alert-danger' role='alert'>Chỉ được phép đặt từ 1 -> 10 ngày.</div>");
				}
			} else {
				ra.addFlashAttribute("msg",
						"<div class='alert alert-danger' role='alert'>Sai định dạng thời gian.</div>");
			}
		} else {
			ra.addFlashAttribute("msg", "<div class='alert alert-danger' role='alert'>Vui lòng chọn thời gian.</div>");
		}
		return "redirect:/public/single_room/" + id_room;
	}

	@RequestMapping(value = "/delete/{id_room}")
	public String del(@PathVariable("id_room") int id_room, HttpSession session, ModelMap model) {
		int soLuong = (int) session.getAttribute("soluong");
		listBooking = (ArrayList<Booking>) session.getAttribute("listBooking");
		for (int i = 0; i < listBooking.size(); i++) {
			if (listBooking.get(i).getRoom_id() == id_room) {
				listBooking.remove(i);
			}
		}
		session.setAttribute("soluong", soLuong);
		session.setAttribute("listBooking", listBooking);
		return "public.cartbooking_delete";
	}

	@RequestMapping(value = "/updateQuantity")
	public String updateQuantity(HttpSession session) {
		int soLuong = (int) session.getAttribute("soluong");
		listBooking = (ArrayList<Booking>) session.getAttribute("listBooking");
		if (listBooking.size() > 0) {
			soLuong--;
		} else {
			soLuong = 0;
		}
		session.setAttribute("soluong", soLuong);
		return "public.quantity";
	}

	@RequestMapping(value = "/booknow/{id_room}")
	public String booknow(@PathVariable("id_room") int id_room, @ModelAttribute("check") Check check,
			HttpSession session, RedirectAttributes ra) {
		if (session.getAttribute("listBooking") != null) {
			listBooking = (ArrayList<Booking>) session.getAttribute("listBooking");
		} else {
			listBooking = new ArrayList<Booking>();
		}
		if ((!"".equals(check.getCheckin())) || (!"".equals(check.getCheckout()))) {
			if (defines.checkDate(check) && defines.checkDateCheckIn(check)) {
				int day = defines.checkQuantityDate(check);
				if ((day > 0) && (day < 11)) {
					int soLuong;
					if (session.getAttribute("soluong") != null) {
						soLuong = (int) session.getAttribute("soluong");
					} else {
						soLuong = 0;
					}

					Room room = roomDao.getRoom(id_room);
					boolean checkTemp = false;
					if (session.getAttribute("listBooking") != null) {
						listBooking = (ArrayList<Booking>) session.getAttribute("listBooking");
						for (Booking booking : listBooking) {
							if (id_room == booking.getRoom_id()) {
								ra.addFlashAttribute("msg",
										"<div class='alert alert-danger' role='alert'>Phòng đã có trong danh sách booking!</div>");
								checkTemp = true;
								return "redirect:/public/single_room/" + id_room;
							}

							if (room.getHotel_id() != booking.getHotel_id()) {
								System.out.println("ok");
								ra.addFlashAttribute("msg",
										"<div class='alert alert-danger' role='alert'>Vui lòng đặt phòng trong My Booking trước!</div>");
								checkTemp = true;
								return "redirect:/public/single_room/" + id_room;
							}
						}
					}
					if (checkTemp == false) {
						soLuong++;

						Booking booking = new Booking();
						booking.setRoom_id(id_room);
						booking.setRoom_number(room.getRoom_number());
						booking.setImage(room.getImage());
						booking.setHotel_id(room.getHotel_id());
						booking.setHotel_name(room.getHotel_name());
						booking.setPrice(room.getPrice());
						booking.setTotal_price(room.getPrice() * day);
						booking.setCheckin(check.getCheckin());
						booking.setCheckout(check.getCheckout());
						booking.setDay(day);
						booking.setPrepayment(room.getPrepayment());
						listBooking.add(booking);

						session.setAttribute("soluong", soLuong);
						session.setAttribute("listBooking", listBooking);
					}
				} else {
					ra.addFlashAttribute("msg",
							"<div class='alert alert-danger' role='alert'>Chỉ được phép đặt từ 1 -> 10 ngày.</div>");
					return "redirect:/public/single_room/" + id_room;
				}
			} else {
				ra.addFlashAttribute("msg",
						"<div class='alert alert-danger' role='alert'>Sai định dạng thời gian.</div>");
				return "redirect:/public/single_room/" + id_room;
			}
		} else {
			ra.addFlashAttribute("msg", "<div class='alert alert-danger' role='alert'>Vui lòng chọn thời gian.</div>");
			return "redirect:/public/single_room/" + id_room;
		}
		return "public.cartbooking";
	}

	@RequestMapping(value = "/info")
	public String info() {
		return "public.infobooking";
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public String info(@ModelAttribute("user") User infoCustomer, HttpSession session) {
		infoCustomer.setUpdatedAt(defines.getDateDay());
		session.setAttribute("infoCustomer", infoCustomer);
		return "public.pay-booking";
	}

//	@RequestMapping(value = "/info", method = RequestMethod.POST)
//	public String info(HttpSession session) {
//		return "public.pay-booking";
//	}

	
	@RequestMapping(value = "/paycomplete")
	public String paycomplete(ModelMap model, HttpSession session, RedirectAttributes ra) {
		List<Booking> listBooking = (List<Booking>) session.getAttribute("listBooking");
		User user = (User) session.getAttribute("infoCustomer");
		if (user.getId_user() != 0) {
			if (userDao.updateUser(user) <= 0) {
				ra.addFlashAttribute("complete", "Đặt phòng thất bại, vui lòng quay lại sau!");
				return "redirect:/public/booking/pay-complete";
			}
		}

		do {
			Booking.setCode_auto(Booking.getCode_auto() + 1);
		} while (bookingDao.CheckCode(Booking.getCode_auto()) != 0);

		for (Booking booking : listBooking) {
			if (user.getId_user() != 0) {
				booking.setCheck_move(0);
			} else {
				booking.setCheck_move(2);
			}
			booking.setCreated_time(defines.getDateDay());

			if (bookingDao.insertBooking(booking, user) <= 0) {
				ra.addFlashAttribute("complete", "Đặt phòng thất bại, vui lòng quay lại sau!");
				return "redirect:/public/booking/pay-complete";
			}
		}
		model.addAttribute("code", Booking.getCode_auto());
		session.setAttribute("soluong", 0);
		session.removeValue("listBooking");
		return "public.pay-complete";
	}
}
