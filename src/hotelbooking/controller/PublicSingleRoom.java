package hotelbooking.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hotelbooking.constant.Defines;
import hotelbooking.dao.BookingDao;
import hotelbooking.dao.HotelDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.dao.RoomReviewDao;
import hotelbooking.dao.HotelReviewDao;
import hotelbooking.model.Booking;
import hotelbooking.model.Room;
import hotelbooking.model.Hotel;
import hotelbooking.model.RoomImage;
import hotelbooking.model.User;

@Controller
@RequestMapping("/public")
public class PublicSingleRoom {
	@Autowired
	private Defines defines;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private HotelDao hotelDao;

	@Autowired
	private RoomReviewDao roomReviewDao;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private HotelReviewDao hotelReviewDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("listHotels", hotelDao.getListHotel());
		List<Booking> listBooking = (ArrayList<Booking>) session.getAttribute("listBooking");
		if (listBooking == null) {
			listBooking = new ArrayList<Booking>();
			session.setAttribute("soluong", 0);
			session.setAttribute("listBooking", listBooking);
		}
	}

	@RequestMapping("/single_room/{id_room}")
	public String index(@PathVariable("id_room") int id_room, ModelMap model, HttpSession session,HttpServletRequest request) {
		List<RoomImage> listImages = roomDao.GetListImages(id_room);
		Room room = roomDao.getRoom(id_room);
		model.addAttribute("room", room);
		model.addAttribute("listImages", listImages);

		String[] listService = room.getService().split(",");

		if (listService[0].compareTo("1") == 0) {
			model.addAttribute("wifi", 1);
		}
		if (listService[1].compareTo("1") == 0) {
			model.addAttribute("television", 1);
		}
		if (listService[2].compareTo("1") == 0) {
			model.addAttribute("conditioning", 1);
		}
		if (listService[3].compareTo("1") == 0) {
			model.addAttribute("drinks", 1);
		}
		if (listService[4].compareTo("1") == 0) {
			model.addAttribute("restaurant", 1);
		}

		List<Room> listRoomsRecommend = new ArrayList<Room>();
		/*
		 * List<Integer> listIdRooms = defines.GetRomsContentBased2(id_room); String
		 * temp = "";
		 * 
		 * for (Integer idRoom : listIdRooms) { Room roomCF = roomDao.getRoom(idRoom);
		 * if (roomCF != null) { listRoomsRecommend.add(roomCF); temp +=
		 * " AND other.id_user <> " + roomCF.getId_room(); } }
		 */
		if (listRoomsRecommend.size() < 9) {
			List<Room> listIdRoomsAdd = roomDao.getRoomsAdd(9 - listRoomsRecommend.size());
			listRoomsRecommend.addAll(listIdRoomsAdd);
		}
		String appPath = request.getServletContext().getRealPath("");
		model.addAttribute("listRoomTop9", listRoomsRecommend);
		model.addAttribute("listRoomTop9", defines.recomroom(id_room,appPath,10));
		model.addAttribute("listReviews", roomReviewDao.getListReviews(id_room));
		return "public.single_room";
	}
	
	@RequestMapping("/single_hotel/{id_hotel}")
	public String indexhotel(@PathVariable("id_hotel") int id_hotel, ModelMap model, HttpSession session,HttpServletRequest request) {
//		List<RoomImage> listImages = roomDao.GetListImages(id_hotel);
		Hotel hotel = hotelDao.getHotel(id_hotel);
		User userPublic = (User) session.getAttribute("userPublic");
		model.addAttribute("hotel", hotel);
		model.addAttribute("listImages", hotel.getHotel_image());
		List<Room> listRoom = roomDao.getAllRoomsOfHotel(id_hotel);
		for(Room room : listRoom) {
			String[] listService = room.getService().split(",");

			if (listService[0].compareTo("1") == 0) {
				model.addAttribute("wifi", 1);
			}
			if (listService[1].compareTo("1") == 0) {
				model.addAttribute("television", 1);
			}
			if (listService[2].compareTo("1") == 0) {
				model.addAttribute("conditioning", 1);
			}
			if (listService[3].compareTo("1") == 0) {
				model.addAttribute("drinks", 1);
			}
			if (listService[4].compareTo("1") == 0) {
				model.addAttribute("restaurant", 1);
			}
		}
		
		

		List<Hotel> listHotelsRecommend = new ArrayList<Hotel>();
		/*
		 * List<Integer> listIdRooms = defines.GetRomsContentBased2(id_room); String
		 * temp = "";
		 * 
		 * for (Integer idRoom : listIdRooms) { Room roomCF = roomDao.getRoom(idRoom);
		 * if (roomCF != null) { listRoomsRecommend.add(roomCF); temp +=
		 * " AND other.id_user <> " + roomCF.getId_room(); } }
		 */
		if (listHotelsRecommend.size() < 9) {
			List<Hotel> listIdHotelsAdd = hotelDao.getHotelsAdd(9 - listHotelsRecommend.size());
			listHotelsRecommend.addAll(listIdHotelsAdd);
		}
		String appPath = request.getServletContext().getRealPath("");
		if(userPublic != null) {
			model.addAttribute("checkUserBooking", bookingDao.CheckUserHotel(id_hotel,userPublic.getId_user()));
		}
		
//		model.addAttribute("listHotelTop9", listHotelsRecommend);
		model.addAttribute("listHotelTop9", defines.recomhotel(id_hotel,appPath,10));
		model.addAttribute("userPublic", userPublic);
		model.addAttribute("listReviews", hotelReviewDao.getListReviewOfHotels(id_hotel));
		return "public.single_hotel";
	}
}
