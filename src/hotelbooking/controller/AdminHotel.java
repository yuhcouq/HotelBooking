package hotelbooking.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotelbooking.constant.Defines;
import hotelbooking.dao.CityDao;
import hotelbooking.dao.HotelDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.dao.UserDao;
import hotelbooking.model.Hotel;
import hotelbooking.model.Room;
import hotelbooking.model.RoomImage;
import hotelbooking.model.User;

@Controller
@RequestMapping("/admin/hotel")
public class AdminHotel {
	@Autowired
	private Defines defines;

	@Autowired
	HotelDao hotelDao;

	@Autowired
	RoomDao roomDao;

	@Autowired
	CityDao cityDao;

	@Autowired
	UserDao userDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("listCities", cityDao.getListCities());
	}

	@RequestMapping(value = { "/index", "/index/{page}" })
	public String index(ModelMap model, @PathVariable(value = "page", required = false) Integer page,
			HttpSession session, RedirectAttributes ra) {
		if (session.getAttribute("userAdmin") != null) {
			User userAdmin = (User) session.getAttribute("userAdmin");
			if (userAdmin.getRole_id() == 1) {
				model.addAttribute("listHotels", hotelDao.getListHotel());
				return "admin.hotel.index";
			} if(userAdmin.getRole_id() == 2) {
				if(userAdmin.getHotel_id() == -1) {
					return "admin.hotel.add";
				}else {
					model.addAttribute("hotel", hotelDao.getHotel(userAdmin.getHotel_id()));
					return "admin.hotel.edit";
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

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpSession session, RedirectAttributes ra, ModelMap modelMap) {
		if (session.getAttribute("userAdmin") != null) {
			User userAdmin = (User) session.getAttribute("userAdmin");
			if (userAdmin.getRole_id() == 1 || userAdmin.getRole_id() == 3) {
				
				return "admin.hotel.add";
			} else {
				ra.addFlashAttribute("error", "Bạn không thể thực hiện chức năng này!");
				return "redirect:/admin/index";
			}
		} else {
			return "redirect:/admin/error403";
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("hotel") Hotel hotel, @RequestParam("picture") CommonsMultipartFile cmf,
			HttpServletRequest request, RedirectAttributes ra, ModelMap model, HttpSession session) {
		String fileName = cmf.getOriginalFilename();

		String reFileName = Defines.renameFile(fileName);
		String appPath = request.getServletContext().getRealPath("");
		String dirPath = appPath + Defines.DIR_UPLOAD;
		File dirFile = new File(dirPath);
		if (dirFile.exists()) {
			dirFile.mkdir();
		}
		String filePath = dirPath + File.separator + reFileName;
		hotel.setHotel_image(reFileName);
		try {
			cmf.transferTo(new File(filePath));
		} catch (IOException e) {
			System.out.println("Error");
		}
		User userAdmin = (User) session.getAttribute("userAdmin");
		if (hotelDao.addHotel(hotel, userAdmin, userDao) > 0) {
			List<User> listUsers = userDao.getListUsers(userAdmin.getEmail());
			for (User user : listUsers) {
				user.setHotel_name(hotelDao.getHotel(user.getHotel_id()).getHotel_name());
				session.setAttribute("userAdmin", user);
			}
			ra.addFlashAttribute("success", "Thêm mới khách sạn thành công!");
			return "redirect:/admin/index";
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}

		return "redirect:/admin/hotel/index";
	}

	@RequestMapping(value = "/edit/{id_hotel}", method = RequestMethod.GET)
	public String edit(@PathVariable("id_hotel") int id_hotel, ModelMap model, HttpSession session,
			RedirectAttributes ra) {
		if (session.getAttribute("userAdmin") != null) {
			User userAdmin = (User) session.getAttribute("userAdmin");
			if (userAdmin.getRole_id() == 1) {
				model.addAttribute("hotel", hotelDao.getHotel(id_hotel));
				return "admin.hotel.edit";
			} else {
				ra.addFlashAttribute("error", "Bạn không thể thực hiện chức năng này!");
				return "redirect:/admin/index";
			}
		} else {
			return "redirect:/admin/error403";
		}
	}

	@RequestMapping(value = "/edit/{id_hotel}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("hotel") Hotel hotel, @PathVariable("id_hotel") int id_hotel,
			@RequestParam("picture") CommonsMultipartFile cmf, HttpServletRequest request, RedirectAttributes ra) {

		Hotel hotelOld = hotelDao.getHotel(id_hotel);
		String fileNameOld = hotelOld.getHotel_image();
		String fileName = cmf.getOriginalFilename();
		String reFileName = Defines.renameFile(fileName);
		if (!"".equals(fileName)) {
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + Defines.DIR_UPLOAD;
			String filePath = dirPath + File.separator + reFileName;
			try {
				if (!"".equals(fileNameOld)) {
					File fileOld = new File(dirPath + File.separator + fileNameOld);
					fileOld.delete();
				}
				cmf.transferTo(new File(filePath));
			} catch (Exception e) {
				return "redirect:/admin/news/index";
			}
		} else {
			reFileName = fileNameOld;
		}
		hotel.setHotel_image(reFileName);
		if (hotelDao.editHotel(hotel) > 0) {
			ra.addFlashAttribute("success", "Cập nhật thông tin khách sạn thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}

		return "redirect:/admin/hotel/index";
	}

	@RequestMapping(value = "/del/{id_hotel}", method = RequestMethod.GET)
	public String del(@PathVariable("id_hotel") int id_hotel, HttpServletRequest request, HttpSession session,
			RedirectAttributes ra) {
		if (session.getAttribute("userAdmin") != null) {
			User userAdmin = (User) session.getAttribute("userAdmin");
			if (userAdmin.getRole_id() == 1) {
				Hotel hotel = hotelDao.getHotel(id_hotel);
				String fileNameOld = hotel.getHotel_image();
				String appPath = request.getServletContext().getRealPath("");
				String dirPath = appPath + Defines.DIR_UPLOAD;
				String filePath = dirPath + File.separator + fileNameOld;
				File file;
				if (!"".equals(filePath)) {
					file = new File(filePath);
					file.delete();
				}
				if (hotelDao.delHotel(id_hotel) > 0) {
					List<Room> listRooms = roomDao.getListRooms(id_hotel);
					for (Room room : listRooms) {
						filePath = dirPath + File.separator + room.getImage();
						file = new File(filePath);
						file.delete();
						if (roomDao.delRoom(room.getId_room()) > 0) {
							List<RoomImage> listImages = roomDao.GetListImages(room.getId_room());
							for (RoomImage roomImage : listImages) {
								filePath = dirPath + File.separator + roomImage.getImage();
								file = new File(filePath);
								file.delete();
								roomDao.deleteImages(room.getId_room());
							}
						}
					}
					ra.addFlashAttribute("success", "Xóa khách sạn thành công!");
				} else {
					ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
				}
				return "redirect:/admin/hotel/index";
			} else {
				ra.addFlashAttribute("error", "Bạn không thể thực hiện chức năng này!");
				return "redirect:/admin/index";
			}
		} else {
			return "redirect:/admin/error403";
		}
	}
}
