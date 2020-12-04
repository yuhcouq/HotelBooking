package hotelbooking.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import hotelbooking.dao.CityDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.dao.RoomReviewDao;
import hotelbooking.dao.HotelReviewDao;
import hotelbooking.dao.SlideDao;
import hotelbooking.dao.UserDao;
import hotelbooking.model.Booking;
import hotelbooking.model.Check;
import hotelbooking.model.Login;
import hotelbooking.model.Room;
import hotelbooking.model.Hotel;
import hotelbooking.model.City;
import hotelbooking.model.RoomReview;
import hotelbooking.model.HotelReview;
import hotelbooking.model.User;
import hotelbooking.util.StringUtil;
import sun.misc.ObjectInputFilter.Status;
import java.util.concurrent.TimeUnit;
@Controller
@RequestMapping("")
public class Publicindex {
	@Autowired
	private Defines defines;

	@Autowired
	private SlideDao slideDao;

	@Autowired
	private HotelDao hotelDao;
	
	@Autowired
	private CityDao cityDao;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private RoomReviewDao roomReviewDao;
	
	@Autowired
	private HotelReviewDao hotelReviewDao;

	@Autowired
	private StringUtil stringUtil;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("listHotels", hotelDao.getListHotel());
		modelMap.addAttribute("listCities", cityDao.getListCities());
		List<Booking> listBooking = (ArrayList<Booking>) session.getAttribute("listBooking");
		if (listBooking == null) {
			listBooking = new ArrayList<Booking>();
			session.setAttribute("soluong", 0);
			session.setAttribute("listBooking", listBooking);
		}
	}

	@RequestMapping(value = { "/public/index", "" })
	public String index(ModelMap model, HttpSession session, HttpServletRequest request) {
		model.addAttribute("listSlide", slideDao.getListSlideON());
		List<Room> listRoomTop3 = roomDao.getRoomsTop3();
		model.addAttribute("listRoomTop3", listRoomTop3);

		List<Room> listRoomsRecommend = new ArrayList<Room>();
		
		long millis = System.currentTimeMillis();
        Date date2 = new Date(millis-(7*86400000));
		if (session.getAttribute("userPublic") != null) {
//			User userPublic = (User) session.getAttribute("userPublic");
//			List<Integer> listIdRooms = defines.GetRomsCollaborative(userPublic.getId_user());
//			String temp = "";
//
//			for (Integer idRoom : listIdRooms) {
//				Room roomCF = roomDao.getRoom(idRoom);
//				if (roomCF != null) {
//					listRoomsRecommend.add(roomCF);
//					temp += " AND other.id_user <> " + roomCF.getId_room();
//				}
//			}
//			if (listRoomsRecommend.size() < 10) {
//				List<Integer> listIdRoomsAdd = defines.GetRomsContentBased(userPublic.getId_user(), temp,
//						10 - listRoomsRecommend.size());
//				for (Integer idRoom : listIdRoomsAdd) {
//					Room roomCF = roomDao.getRoom(idRoom);
//					if (roomCF != null) {
//						listRoomsRecommend.add(roomCF);
//					}
//				}
//			}
//			if (listRoomsRecommend.size() < 10) {
//				List<Room> listIdRoomsAdd = roomDao.getRoomsAdd(10 - listRoomsRecommend.size());
//				listRoomsRecommend.addAll(listIdRoomsAdd);
//			}
//			model.addAttribute("listRoomTop10", listRoomsRecommend);
			User userPublic = (User) session.getAttribute("userPublic");
			String appPath = request.getServletContext().getRealPath("");
			model.addAttribute("listRoomTop10", defines.recom(userPublic.getId_user(),appPath,10));
//			model.addAttribute("listRoomTop10", roomDao.getListRoom10s());
			model.addAttribute("listRoomTopreate10", roomDao.getListRoomcreate10s());
			model.addAttribute("listRoomTopHot10", roomDao.getListRoomHot10s(date2));
		} else {
			
			model.addAttribute("listRoomTop10", roomDao.getListRoom10s());
			model.addAttribute("listRoomTopreate10", roomDao.getListRoomcreate10s());
			model.addAttribute("listRoomTopHot10", roomDao.getListRoomHot10s(date2));
		}
		List<Hotel> listhotels = hotelDao.getListHotel();
		model.addAttribute("listhotels", listhotels);
		return "public.index.index";
	}

	@RequestMapping(value = { "/public/listhotel"}, method = RequestMethod.GET)
	public ResponseEntity<List<Hotel>> listHotel() {
		List<Hotel> listHotel= hotelDao.getListHotel();
		return new ResponseEntity<>(listHotel,HttpStatus.OK);
	}
	@RequestMapping(value = { "/public/listcity"}, method = RequestMethod.GET)
	public ResponseEntity<List<City>> listCity() {
		List<City> city = cityDao.getListCities();
		return new ResponseEntity<>(city,HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/public/hotel/{id_hotel}", "/public/hotel/{id_hotel}/{page}" })
	public String hotel(@PathVariable(value = "page", required = false) Integer page, ModelMap model,
			@PathVariable("id_hotel") int id_hotel) {
		if (page == null) {
			page = 1;
		}
		int totalRooms = roomDao.getCountRoomsID(id_hotel);
		int sumPage = (int) Math.ceil((float) totalRooms / Defines.ROW_COUNT_PUBLIC);
		model.addAttribute("sumPage", sumPage);
		int offset = (page - 1) * Defines.ROW_COUNT_PUBLIC;
		model.addAttribute("page", page);

		model.addAttribute("listRooms", roomDao.getListRooms(id_hotel, offset));

		return "public.rooms";
	}

	@RequestMapping("/public/check_index")
	public String check_index(@ModelAttribute("check") Check check, ModelMap model) {
		String sql = "SELECT r.*,h.hotel_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel WHERE 1=1";
		if (!"00".equals(check.getAdults())) {
			sql += " AND r.adult_number = %s";
			sql = String.format(sql, check.getAdults());
		}
		if (!"00".equals(check.getChildren())) {
			sql += " AND r.children_number = %s";
			sql = String.format(sql, check.getChildren());
		}
		if ((!"0".equals(check.getMin_price())) && (check.getMin_price() != null)) {
			sql += " AND r.price >= %s";
			sql = String.format(sql, check.getMin_price());
		}
		if ((!"0".equals(check.getMin_price())) && (check.getMin_price() != null)) {
			sql += " AND r.price <= %s";
			sql = String.format(sql, check.getMax_price());
		}
		if ((check.getCheckin().indexOf("-") != -1) && (check.getCheckout().indexOf("-") != -1)) {
			sql += " AND r.id_room NOT IN (SELECT b.room_id FROM booking b WHERE (b.checkin < '%s' AND b.checkout > '%s') OR (b.checkin < '%s' AND b.checkout > '%s') OR (b.checkin >= '%s' AND b.checkout <= '%s'))";
			sql = String.format(sql, check.getCheckin(), check.getCheckin(), check.getCheckout(), check.getCheckout(),
					check.getCheckin(), check.getCheckout());
		}
		if ((check.getCheckin().indexOf("-") != -1) && (check.getCheckout().indexOf("-") == -1)) {
			sql += " AND r.id_room NOT IN (SELECT b.room_id FROM booking b WHERE (b.checkin < '%s' AND b.checkout > '%s'))";
			sql = String.format(sql, check.getCheckin(), check.getCheckin());
		}
		if ((check.getCheckin().indexOf("-") == -1) && (check.getCheckout().indexOf("-") != -1)) {
			sql += " AND r.id_room NOT IN (SELECT b.room_id FROM booking b WHERE (b.checkin < '%s' AND b.checkout > '%s'))";
			sql = String.format(sql, check.getCheckout(), check.getCheckout());
		}
		sql += " LIMIT 10";
		System.out.println(sql);
		model.addAttribute("check", check);
		model.addAttribute("listRooms", roomDao.check_index(sql));
		return "public.room_check";
	}

	@RequestMapping(value = { "/public/search", "/public/search/{page}/{hotel_name}" })
	public String search(@ModelAttribute("check") Check check,
			@PathVariable(value = "page", required = false) Integer page,
			@ModelAttribute("hotel_name") String hotel_name, ModelMap model) {
		if (!"".equals(check.getSearch()) && check.getSearch() != null) {
			model.addAttribute("hotel_name", check.getSearch());
			hotel_name = "%" + check.getSearch() + "%";
		} else {
			model.addAttribute("hotel_name", hotel_name);
			hotel_name = "%" + hotel_name + "%";
		}
		if (page == null) {
			page = 1;
		}
		int totalSearch = roomDao.getCountSearch(hotel_name);
		int sumPage = (int) Math.ceil((float) totalSearch / Defines.ROW_COUNT_PUBLIC);
		model.addAttribute("sumPage", sumPage);
		int offset = (page - 1) * Defines.ROW_COUNT_PUBLIC;
		model.addAttribute("page", page);

		model.addAttribute("listRooms", roomDao.search(hotel_name, offset));
		return "public.room_search";
	}

	@RequestMapping(value = "/public/login", method = RequestMethod.GET)
	public String login() {
		return "public.login";
	}

	@RequestMapping(value = "/public/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("login") Login login, HttpSession session, ModelMap model,
			RedirectAttributes ra) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (login.getCheck() == 0) {
			List<User> listUsers = userDao.getListUsers(login.getEmailSignIn());
			for (User user : listUsers) {
				if (passwordEncoder.matches(login.getPasswordSignIn(), user.getPassword())) {
					if (user.getRole_id() == 4) {
						session.setAttribute("userPublic", user);
						return "redirect:/public/index";
					} else {
						if (user.getRole_id() == 1) {
							user.setHotel_name("ADMIN MANAGER");
						} else {
							if(user.getHotel_id() == -1 || user.getHotel_id() == 0) {
								user.setHotel_name("Chưa có khách sạn nào");
							}else {
								user.setHotel_name(hotelDao.getHotel(user.getHotel_id()).getHotel_name());
							}
							
						}
						session.setAttribute("userAdmin", user);
						return "redirect:/admin/index";
					}
				}
			}
			model.addAttribute("msg", "Sai Email hoặc Password.");
			model.addAttribute("signin", login);
			return "public.login";
		} else {
			if (userDao.getUser(login.getEmailSignUp()) > 0) {
				model.addAttribute("msg", "Email đã được đăng ký.");
				model.addAttribute("signup", login);
				return "public.login";
			} else {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String presentDate = dateFormat.format(date);
				login.setPasswordSignUp(stringUtil.tess(login.getPasswordSignUp()));
				if (userDao.addUser(login, presentDate) > 0) {
					ra.addFlashAttribute("msg", "Đăng ký thành công.");
					return "redirect:/public/login";
				} else {
					model.addAttribute("signup", "Đăng ký thất bại, vui lòng thử lại sau!");
					model.addAttribute("signup", login);
					return "public.login";
				}
			}
		}
	}

	@RequestMapping(value = "/public/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeValue("userPublic");
		session.removeValue("infoCustomer");
//		session.removeAttribute("userPublic");
//		session.removeAttribute("userAdmin");
//		session.removeAttribute("infoCustomer");
		return "redirect:/public/index";
	}

	@RequestMapping(value = "/public/my-info", method = RequestMethod.GET)
	public String MyInfo() {
		
		return "public.my-info";
	}

	@RequestMapping(value = "/public/my-info/update", method = RequestMethod.POST)
	public String MyInfo(@ModelAttribute("user") User customer, HttpSession session, RedirectAttributes ra) {
		User userOld = (User) session.getAttribute("userPublic");
		customer.setId_user(userOld.getId_user());
		customer.setCreatedAt(defines.getDateDay());
		customer.setAvatar(userOld.getAvatar());
		customer.setUsername(userOld.getUsername());
		if ("".equals(customer.getPassword())) {
			customer.setPassword(userOld.getPassword());
		} else {
			customer.setPassword(stringUtil.tess(customer.getPassword()));
		}
		if (userDao.editUser(customer) > 0) {
			session.setAttribute("userPublic", customer);
			ra.addFlashAttribute("success", "Cập nhật thành công!");
		} else {
			ra.addFlashAttribute("success", "Cập nhật thất bại, vui lòng quay lại sau!");
		}
		return "redirect:/public/my-info";
	}

	@RequestMapping(value = "/public/my-booking/{id_user}", method = RequestMethod.GET)
	public String MyBooking(@PathVariable("id_user") int id_user, ModelMap model) {
		List<Booking> booking = bookingDao.getAllMyBoooking(id_user);
		model.addAttribute("listBooking", booking);
		return "public.my-booking";
	}
	
	@RequestMapping(value = "/public/my-review/{id_user}", method = RequestMethod.GET)
	public String MyReview(@PathVariable("id_user") int id_user, ModelMap model) {
		List<RoomReview> roomReview = roomReviewDao.getListReviewUsers(id_user);
		List<HotelReview> hotelReview = hotelReviewDao.getListReviewOfUsers(id_user);
		model.addAttribute("listHotelReview", hotelReview);
		model.addAttribute("listRoomReview", roomReview);
		return "public.my-review";
	}

	@RequestMapping(value = "/public/reviews/{id_room}/{id_booking}", method = RequestMethod.GET)
	public String Reviews(@PathVariable("id_room") int id_room, @PathVariable("id_booking") int id_booking,
			ModelMap model) {
		model.addAttribute("id_room", id_room);
		model.addAttribute("id_booking", id_booking);
		return "public.reviews";
	}

	@RequestMapping(value = "/public/reviews/{id_room}/{id_user}/{id_booking}", method = RequestMethod.POST)
	public String Reviews(@PathVariable("id_room") int id_room, @PathVariable("id_user") int id_user,
			@PathVariable("id_booking") int id_booking, @ModelAttribute("roomReviews") RoomReview roomReviews,
			ModelMap model, RedirectAttributes ra) {
		roomReviews.setCreate_time(defines.getDateDay());
		if (roomReviewDao.InsertReview(roomReviews, id_room, id_user) > 0) {
			if (bookingDao.updateStatus(id_booking) > 0) {
				Room room = roomDao.getRoom(id_room);
				if (room.getRating() != 0) {
					room.setRating((room.getRating() + roomReviews.getRating()) / 2);
				} else {
					room.setRating(roomReviews.getRating());
				}

				roomDao.updateRating(room);
				ra.addFlashAttribute("success", "Đánh giá chất lượng thành công!");
			}
		} else {
			ra.addFlashAttribute("error", "Đánh giá chất lượng thất bại, vui lòng quay lại sau!");
		}
		return "redirect:/public/my-booking/" + id_user;
	}
	
	@RequestMapping(value = "/public/reviews/{id_hotel}/{id_user}", method = RequestMethod.POST)
	public String ReviewHotels(@PathVariable("id_hotel") int id_hotel, @PathVariable("id_user") int id_user, @ModelAttribute("hotelReviews") HotelReview hotelReview,
			ModelMap model, RedirectAttributes ra) {
		hotelReview.setCreate_time(defines.getDateDay());
		if(hotelReviewDao.getCheckReviewOfUsers(id_user)==0) {
			if (hotelReviewDao.InsertReview(hotelReview, id_hotel, id_user) > 0) {
				Hotel hotel = hotelDao.getHotel(id_hotel);
				if (hotel.getRating() != 0) {
					hotel.setRating((hotel.getRating() + hotelReview.getRating()) / 2);
				} else {
					hotel.setRating(hotelReview.getRating());
				}
				hotelDao.updateRating(hotel);
				ra.addFlashAttribute("success", "Đánh giá chất lượng thành công!");
			} else {
				ra.addFlashAttribute("error", "Đánh giá chất lượng thất bại, vui lòng quay lại sau!");
			}
		}else {
			ra.addFlashAttribute("error", "Bạn đã đánh giá khách sạn này rồi!");
		}
		return "redirect:/public/single_hotel/" + id_hotel;
	}
	
	@RequestMapping(value = "/public/recomhotel", method = RequestMethod.GET)
	public String RecomHotel(HttpServletRequest request) {
		String appPath = request.getServletContext().getRealPath("");
		defines.recomhotelcsv(appPath);
		return "redirect:/public/index";
	}
	
	@RequestMapping(value = "/public/recomroom", method = RequestMethod.GET)
	public String RecomRoom(HttpServletRequest request) {
		String appPath = request.getServletContext().getRealPath("");
		defines.recomroomcsv(appPath);
		return "redirect:/public/index";
	}
	
	@RequestMapping(value = "/public/recomuser", method = RequestMethod.GET)
	public String RecomUser(HttpServletRequest request) {
		String appPath = request.getServletContext().getRealPath("");
		defines.recomusercsv(appPath);
		return "redirect:/public/index";
	}
	
	@RequestMapping(value = "/public/recomcontenthotel", method = RequestMethod.GET)
	public String RecomContentHotel(HttpServletRequest request) {
		String appPath = request.getServletContext().getRealPath("");
		defines.recomhotelcontentcsv(appPath);
		return "redirect:/public/index";
	}
	
	@RequestMapping(value = "/public/recomcontentroom", method = RequestMethod.GET)
	public String RecomContentRoom(HttpServletRequest request) {
		String appPath = request.getServletContext().getRealPath("");
		defines.recomroomcontentcsv(appPath);
		return "redirect:/public/index";
	}
	
	@RequestMapping(value = "/public/recomcontentuser", method = RequestMethod.GET)
	public String RecomContentUser(HttpServletRequest request) {
		String appPath = request.getServletContext().getRealPath("");
		defines.recomusercontentcsv(appPath);
		return "redirect:/public/index";
	}
}
