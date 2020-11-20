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

import hotelbooking.constant.Defines;
import hotelbooking.dao.HotelDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.dao.UserDao;
import hotelbooking.model.Booking;
import hotelbooking.model.Check;
import hotelbooking.model.Room;
import hotelbooking.model.User;
import hotelbooking.model.Hotel;

@Controller
@RequestMapping("/public")
public class PublicRoom {
	private ArrayList<Room> listRoomsBooking = new ArrayList<Room>();
	@Autowired
	private Defines defines;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private HotelDao hotelDao;
	
	private UserDao userDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
//		modelMap.addAttribute("listHotels", hotelDao.getListHotel());
		List<Booking> listBooking = (ArrayList<Booking>) session.getAttribute("listBooking");
		if (listBooking == null) {
			listBooking = new ArrayList<Booking>();
			session.setAttribute("soluong", 0);
			session.setAttribute("listBooking", listBooking);
		}
	}
	
	@RequestMapping(value = { "/hotels", "/hotels/{page}" })
	public String hotel(@PathVariable(value = "page", required = false) Integer page, ModelMap model,
			HttpSession session) {
		
		if (page == null) {
			page = 1;
		}
		int totalHotels = hotelDao.getCountHotels();
		int sumPage = (int) Math.ceil((float) totalHotels / Defines.ROW_COUNT_PUBLIC);
		model.addAttribute("sumPage", sumPage);
		int offset = (page - 1) * Defines.ROW_COUNT_PUBLIC;
		model.addAttribute("page", page);

		if (page == 1) {
			List<Hotel> listhotelsRecommend = new ArrayList<Hotel>();
			if (session.getAttribute("userPublic") != null) {
//				User userPublic = (User) session.getAttribute("userPublic");
//				List<Integer> listIdRooms = defines.GetRomsCollaborative(userPublic.getId_user());
//				String temp = "";
//
//				for (Integer idRoom : listIdRooms) {
//					Room roomCF = roomDao.getRoom(idRoom);
//					if (roomCF != null) {
//						listRoomsRecommend.add(roomCF);
//						temp += " AND other.id_user <> " + roomCF.getId_room();
//					}
//				}
//				if (listRoomsRecommend.size() < 10) {
//					List<Integer> listIdRoomsAdd = defines.GetRomsContentBased(userPublic.getId_user(), temp,
//							10 - listRoomsRecommend.size());
//					for (Integer idRoom : listIdRoomsAdd) {
//						Room roomCF = roomDao.getRoom(idRoom);
//						if (roomCF != null) {
//							listRoomsRecommend.add(roomCF);
//						}
//					}
//				}
//				if (listRoomsRecommend.size() < 10) {
//					List<Room> listIdRoomsAdd = roomDao.getRoomsAdd(10 - listRoomsRecommend.size());
//					listRoomsRecommend.addAll(listIdRoomsAdd);
//				}
				listhotelsRecommend = hotelDao.getListHotel(offset);
			} else {
				listhotelsRecommend = hotelDao.getListHotel(offset);
			}
			model.addAttribute("listHotels", listhotelsRecommend);
		} else {
			model.addAttribute("listHotels", hotelDao.getListHotel(offset));
		}

		return "public.hotels";
		
	}

	@RequestMapping(value = { "/rooms", "/rooms/{page}" })
	public String index(@PathVariable(value = "page", required = false) Integer page, ModelMap model,
			HttpSession session) {
		if (page == null) {
			page = 1;
		}
		int totalRooms = roomDao.getCountRooms();
		int sumPage = (int) Math.ceil((float) totalRooms / Defines.ROW_COUNT_PUBLIC);
		model.addAttribute("sumPage", sumPage);
		int offset = (page - 1) * Defines.ROW_COUNT_PUBLIC;
		model.addAttribute("page", page);

		if (page == 1) {
			List<Room> listRoomsRecommend = new ArrayList<Room>();
			if (session.getAttribute("userPublic") != null) {
//				User userPublic = (User) session.getAttribute("userPublic");
//				List<Integer> listIdRooms = defines.GetRomsCollaborative(userPublic.getId_user());
//				String temp = "";
//
//				for (Integer idRoom : listIdRooms) {
//					Room roomCF = roomDao.getRoom(idRoom);
//					if (roomCF != null) {
//						listRoomsRecommend.add(roomCF);
//						temp += " AND other.id_user <> " + roomCF.getId_room();
//					}
//				}
//				if (listRoomsRecommend.size() < 10) {
//					List<Integer> listIdRoomsAdd = defines.GetRomsContentBased(userPublic.getId_user(), temp,
//							10 - listRoomsRecommend.size());
//					for (Integer idRoom : listIdRoomsAdd) {
//						Room roomCF = roomDao.getRoom(idRoom);
//						if (roomCF != null) {
//							listRoomsRecommend.add(roomCF);
//						}
//					}
//				}
//				if (listRoomsRecommend.size() < 10) {
//					List<Room> listIdRoomsAdd = roomDao.getRoomsAdd(10 - listRoomsRecommend.size());
//					listRoomsRecommend.addAll(listIdRoomsAdd);
//				}
				listRoomsRecommend = roomDao.getListRoomsLimit(offset);
			} else {
				listRoomsRecommend = roomDao.getListRoomsLimit(offset);
			}
			model.addAttribute("listRooms", listRoomsRecommend);
		} else {
			model.addAttribute("listRooms", roomDao.getListRoomsLimit(offset));
		}

		return "public.rooms";
	}

	@RequestMapping("/check_room")
	public String check_room(@ModelAttribute("check") Check check, ModelMap model) {
		String sql = "SELECT r.*,h.hotel_name FROM room AS r INNER JOIN hotel AS h ON r.hotel_id = h.id_hotel WHERE r.price > %s AND r.price < %s";
		sql = String.format(sql, check.getMin_price(), check.getMax_price());
		if (!"00".equals(check.getAdults())) {
			sql += " AND r.adult_number = %s";
			sql = String.format(sql, check.getAdults());
		}
		if (!"00".equals(check.getChildren())) {
			sql += " AND r.children_number = %s";
			sql = String.format(sql, check.getChildren());
		}
		model.addAttribute("check", check);
		model.addAttribute("listRooms", roomDao.check_room(sql));
		return "public.room_check";
	}
}
