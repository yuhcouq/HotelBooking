package hotelbooking.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hotelbooking.constant.Defines;
import hotelbooking.dao.HotelDao;
import hotelbooking.dao.PrepaymentDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.dao.RoomReviewDao;
import hotelbooking.model.Room;
import hotelbooking.model.RoomImage;
import hotelbooking.model.User;

@Controller
@RequestMapping("/admin/room")
public class AdminRoom {
	@Autowired
	private Defines defines;

	@Autowired
	private HotelDao hotelDao;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private RoomReviewDao roomReviewDao;

	@Autowired
	private PrepaymentDao prepaymentDao;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
	}

	@RequestMapping(value = {"/index","/index/{id_hotel}"})
	public String index(ModelMap model, HttpSession session, RedirectAttributes ra,@PathVariable(value = "id_hotel", required = false) Integer id_hotel) {
		if (session.getAttribute("userAdmin") != null) {
			User userAdmin = (User) session.getAttribute("userAdmin");
			if (userAdmin.getRole_id() == 1) {
				if(id_hotel != null) {
					model.addAttribute("listRooms", roomDao.getAllRoomsOfHotel(id_hotel));
				}else {
					model.addAttribute("listRooms", roomDao.getListRooms());
				}
				
				return "admin.room.index";
			}else {
				model.addAttribute("listRooms", roomDao.getAllRoomsOfHotel(userAdmin.getHotel_id()));
				return "admin.room.index";
			}
		} else {
			return "redirect:/admin/error403";
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("listPrepayments", prepaymentDao.getListPrepayments());
		return "admin.room.add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("room") Room room, @RequestParam("picture") CommonsMultipartFile cmf,
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
		room.setImage(reFileName);
		try {
			cmf.transferTo(new File(filePath));
		} catch (IOException e) {
			System.out.println("Error");
		}

		String service = "";
		if (room.getWifi()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		if (room.getTelevision()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		if (room.getConditioning()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		if (room.getDrinks()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		if (room.getRestaurant()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		User userAdmin = (User) session.getAttribute("userAdmin");
		room.setHotel_id(userAdmin.getHotel_id());
		int id_hotel = userAdmin.getHotel_id();
		room.setService(service);
		room.setCreatedAt(defines.getDateDay());

		if (roomDao.addRoom(room,id_hotel) > 0) {
			ra.addFlashAttribute("success", "Thêm phòng thành công!");
		} else {
			ra.addFlashAttribute("error", "Thêm phòng thất bại!");
		}
		return "redirect:/admin/room/index";
	}

	@RequestMapping(value = "/edit/{id_room}", method = RequestMethod.GET)
	public String edit(@PathVariable("id_room") int id_room, ModelMap model) {
		model.addAttribute("listHotels", hotelDao.getListHotel());
		model.addAttribute("room", roomDao.getRoom(id_room));
		model.addAttribute("listPrepayments", prepaymentDao.getListPrepayments());
		return "admin.room.edit";
	}

	@RequestMapping(value = "/edit/{id_room}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("room") Room room, @PathVariable("id_room") int id_room,
			@RequestParam("picture") CommonsMultipartFile cmf, HttpServletRequest request, RedirectAttributes ra,
			ModelMap model) {
		String service = "";
		if (room.getWifi()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		if (room.getTelevision()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		if (room.getConditioning()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		if (room.getDrinks()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		if (room.getRestaurant()!=null) {
			service += "1,";
		} else {
			service += "0,";
		}
		room.setService(service);

		Room roomOld = roomDao.getRoom(id_room);
		String fileNameOld = roomOld.getImage();
		String fileName = cmf.getOriginalFilename();
		String reFileName = Defines.renameFile(fileName);
		String appPath = "";
		String dirPath = "";
		String filePath = "";
		if (!"".equals(fileName)) {
			appPath = request.getServletContext().getRealPath("");
			dirPath = appPath + Defines.DIR_UPLOAD;
			filePath = dirPath + File.separator + reFileName;
			try {
				if (!"".equals(fileNameOld)) {
					File fileOld = new File(dirPath + File.separator + fileNameOld);
					fileOld.delete();
				}
				cmf.transferTo(new File(filePath));
			} catch (Exception e) {
				return "redirect:/admin/room/index";
			}
		} else {
			reFileName = fileNameOld;
		}
		room.setImage(reFileName);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String presentDate = dateFormat.format(date);
		room.setUpdatedAt(presentDate);

		if (roomDao.editRoom(room) > 0) {
			ra.addFlashAttribute("success", "Cập nhật thông tin phòng thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}
		return "redirect:/admin/room/index";
	}

	@RequestMapping(value = "/del/{id_room}", method = RequestMethod.GET)
	public String del(@PathVariable("id_room") int id_room, HttpServletRequest request, RedirectAttributes ra) {
		if (roomDao.delRoom(id_room) > 0) {
			ra.addFlashAttribute("success", "Xóa phòng thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}
		return "redirect:/admin/room/index";
	}

	@RequestMapping("/views_image/{id_room}")
	public String viewsImage(@PathVariable("id_room") int id_room, ModelMap model) {
		model.addAttribute("listImages", roomDao.GetListImages(id_room));
		model.addAttribute("id_room", id_room);
		return "admin.room.views_image";
	}

	@RequestMapping("/add_image/{id_room}")
	public String addImage(@PathVariable("id_room") int id_room, ModelMap model, RedirectAttributes ra) {
		if (roomDao.getCountImage(id_room) > 3) {
			ra.addFlashAttribute("error", "Chỉ có thể tối đa là 4 ảnh!");
			return "redirect:/admin/room/views_image/" + id_room;
		}
		model.addAttribute("id_room", id_room);
		return "admin.room.add_image";
	}

	@RequestMapping(value = "/add_image/{id_room}", method = RequestMethod.POST)
	public String add_image(@PathVariable("id_room") int id_room, @RequestParam("picture") CommonsMultipartFile cmf,
			HttpServletRequest request, RedirectAttributes ra, ModelMap model) {
		String fileName = cmf.getOriginalFilename();

		String reFileName = Defines.renameFile(fileName);
		String appPath = request.getServletContext().getRealPath("");
		String dirPath = appPath + Defines.DIR_UPLOAD;
		File dirFile = new File(dirPath);
		if (dirFile.exists()) {
			dirFile.mkdir();
		}
		String filePath = dirPath + File.separator + reFileName;
		try {
			cmf.transferTo(new File(filePath));
		} catch (IOException e) {
			System.out.println("Error");
		}
		if (roomDao.addImage(id_room, reFileName) > 0) {
			ra.addFlashAttribute("success", "Thêm hình ảnh thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}
		return "redirect:/admin/room/views_image/" + id_room;
	}

	@RequestMapping("/edit_image/{id_image}/{id_room}")
	public String editImage(@PathVariable("id_image") int id_image, @PathVariable("id_room") int id_room,
			ModelMap model) {
		model.addAttribute("image", roomDao.getImage(id_image));
		model.addAttribute("id_room", id_room);
		return "admin.room.edit_image";
	}

	@RequestMapping(value = "/edit_image/{id_image}/{id_room}", method = RequestMethod.POST)
	public String editImage(@PathVariable("id_image") int id_image, @PathVariable("id_room") int id_room,
			@RequestParam("picture") CommonsMultipartFile cmf, HttpServletRequest request, ModelMap model,
			RedirectAttributes ra) {
		String fileName = cmf.getOriginalFilename();
		String reFileName = Defines.renameFile(fileName);
		if (!fileName.equals("")) {
			RoomImage roomImageOld = roomDao.getImage(id_image);
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + Defines.DIR_UPLOAD;
			try {
				if (!"".equals(roomImageOld.getImage())) {
					File fileOld = new File(dirPath + File.separator + roomImageOld.getImage());
					fileOld.delete();
				}
				String filePath = dirPath + File.separator + reFileName;
				cmf.transferTo(new File(filePath));
			} catch (Exception e) {
				return "redirect:/admin/room/views_image/" + id_room;
			}

			if (roomDao.editImage(id_image, reFileName) > 0) {
				ra.addFlashAttribute("success", "Chỉnh sửa hình ảnh thành công!");
			} else {
				ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
			}
		}
		return "redirect:/admin/room/views_image/" + id_room;
	}

	@RequestMapping("/del_image/{id_image}/{id_room}")
	public String delImage(@PathVariable("id_image") int id_image, @PathVariable("id_room") int id_room, ModelMap model,
			RedirectAttributes ra, HttpServletRequest request) {
		RoomImage roomImageOld = roomDao.getImage(id_image);
		if (roomDao.deleteImage(id_image) > 0) {
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + Defines.DIR_UPLOAD;
			try {
				if (!"".equals(roomImageOld.getImage())) {
					File fileOld = new File(dirPath + File.separator + roomImageOld.getImage());
					fileOld.delete();
				}
			} catch (Exception e) {
				return "redirect:/admin/room/views_image/" + id_room;
			}
			ra.addFlashAttribute("success", "Xóa hình ảnh thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}
		return "redirect:/admin/room/views_image/" + id_room;
	}
}
