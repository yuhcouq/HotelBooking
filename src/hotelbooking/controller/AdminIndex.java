package hotelbooking.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hotelbooking.constant.Defines;
import hotelbooking.dao.RoomDao;
import hotelbooking.model.User;
import hotelbooking.model.City;
import hotelbooking.model.Room;

@Controller
@RequestMapping("/admin")
public class AdminIndex {
	@Autowired
	private Defines defines;

	@Autowired
	private RoomDao roomDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		// User user = userDao.getUser(principal.getName());
		// session.setAttribute("user", user);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpSession session, ModelMap model) {
		if (session.getAttribute("userAdmin") == null) {
			return "redirect:/admin/error403";
		} else {
			if (session.getAttribute("userAdmin") != null) {
				User userAdmin = (User) session.getAttribute("userAdmin");
				if (userAdmin.getRole_id() == 1) {
					model.addAttribute("listRooms", roomDao.getListRooms());
					return "admin.room.index";
				} else {
					if(userAdmin.getHotel_id() == -1) {
						
					}
					else{
						model.addAttribute("listRooms", roomDao.getAllRoomsOfHotel(userAdmin.getHotel_id()));
					}
					
					return "admin.room.index";
				}
			} else {
				return "redirect:/admin/error403";
			}
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeValue("userAdmin");
		session.removeValue("role");
		return "redirect:/public/index";
	}

	@RequestMapping("/error403")
	public String error403() {
		return "err.403";
	}
	
	@RequestMapping(value = "/discount/index")
	public String discount(HttpSession session, ModelMap model) {
		User userAdmin = (User) session.getAttribute("userAdmin");
		if (userAdmin.getRole_id() == 1) {
			List<Room> listRoom = roomDao.getListRooms();
			for(Room room : listRoom) {
				int discount = 0;
				if(roomDao.checkDiscountRoom(room.getId_room())>0) {
					discount = roomDao.getDiscountRoom(room.getId_room());
				}
				room.setDiscount(discount);
			}
			model.addAttribute("listRooms", listRoom);
			return "admin.discount";
		}else {
			List<Room> listRoom = roomDao.getAllRoomsOfHotel(userAdmin.getHotel_id());
			for(Room room : listRoom) {
				int discount = 0;
				if(roomDao.checkDiscountRoom(room.getId_room())>0) {
					discount = roomDao.getDiscountRoom(room.getId_room());
				}
				room.setDiscount(discount);
			}
			model.addAttribute("listRooms", listRoom);
			return "admin.discount";
		}
	}
	@RequestMapping(value = { "/adddiscount"}, method = RequestMethod.GET)
	public ResponseEntity<Integer> adddiscount(@ModelAttribute("discount") int discount, @ModelAttribute("id") int id) {
		int result = 0;
		if(roomDao.addDiscount(id,discount) > 0) {
			result = 1;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/deletediscount"}, method = RequestMethod.GET)
	public ResponseEntity<Integer> deletediscount(@ModelAttribute("id") int id) {
		int result = 0;
		if(roomDao.deleteDiscount(id) > 0) {
			result = 1;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
