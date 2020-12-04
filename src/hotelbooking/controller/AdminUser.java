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
import hotelbooking.dao.UserDao;
import hotelbooking.model.User;
import hotelbooking.util.StringUtil;

@Controller
@RequestMapping("/admin/user")
public class AdminUser {
	@Autowired
	private Defines defines;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private HotelDao hotelDao;

	@Autowired
	private StringUtil stringUtil;

	@ModelAttribute
	public void addCommonsObject(ModelMap modelMap, HttpSession session, Principal principal) {
		modelMap.addAttribute("defines", defines);
		modelMap.addAttribute("listCities", cityDao.getListCities());
	}

	@RequestMapping("/index/{role}")
	public String index(@PathVariable("role") int role, ModelMap model, HttpSession session, RedirectAttributes ra) {
		if (session.getAttribute("userAdmin") != null) {
			session.setAttribute("role", role);
			if (role == 3) {
				User user = (User) session.getAttribute("userAdmin");
				model.addAttribute("listUsers", userDao.getAllStaff(role, user.getHotel_id()));
			} else {
				model.addAttribute("listUsers", userDao.getAllUsers(role));
			}
			return "admin.user.index";
		} else {
			return "redirect:/admin/error403";
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap modelMap) {
		modelMap.addAttribute("listHotel", hotelDao.getAllHotel());
		return "admin.user.add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("user") User user, @RequestParam("picture") CommonsMultipartFile cmf,
			HttpServletRequest request, RedirectAttributes ra, ModelMap model, HttpSession session) {
		List<User> listUser = userDao.getListUsers();
		boolean check = true;
		for (User item : listUser) {
			if (user.getUsername().equals(item.getUsername())) {
				check = false;
			}
		}
		if (check) {
			String fileName = cmf.getOriginalFilename();
			String reFileName = Defines.renameFile(fileName);
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + Defines.DIR_UPLOAD;
			File dirFile = new File(dirPath);
			if (dirFile.exists()) {
				dirFile.mkdir();
			}
			String filePath = dirPath + File.separator + reFileName;
			System.out.println(filePath);
			user.setAvatar(reFileName);
			try {
				cmf.transferTo(new File(filePath));
			} catch (IOException e) {
				System.out.println("Error");
			}

			String password = stringUtil.tess(user.getPassword());
			user.setPassword(password);

			user.setCreatedAt(defines.getDateDay());

			if (userDao.addUser(user) > 0) {
				ra.addFlashAttribute("success", "Thêm thành công!");
			} else {
				ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
			}
		} else {
			model.addAttribute("user", user);
			model.addAttribute("msg", "username đã tồn tại.");
			return "admin.user.add";
		}
		return "redirect:/admin/user/index/" + session.getAttribute("role");
	}

	@RequestMapping(value = "/edit/{id_user}", method = RequestMethod.GET)
	public String edit(@PathVariable("id_user") int id_user, ModelMap model) {
		User user = (User) userDao.getUser(id_user);
		model.addAttribute("user", user);
		if(user.getRole_id()==3) {
			model.addAttribute("checkprofile", 1);
		}
		model.addAttribute("listHotels", hotelDao.getListHotel());
		if(user.getHotel_id()!= -1 && user.getHotel_id() != 0) {
			model.addAttribute("hotel",  hotelDao.getHotel(user.getHotel_id()));
		}
		
		return "admin.user.edit";
	}
	
	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public String editprofile(ModelMap model,HttpSession session) {
		User userAdmin = (User) session.getAttribute("userAdmin");
		model.addAttribute("user", userAdmin);
		model.addAttribute("checkprofile", 1);
		model.addAttribute("listHotels", hotelDao.getListHotel());
		if(userAdmin.getHotel_id()!= -1 && userAdmin.getHotel_id() != 0) {
			model.addAttribute("hotel",  hotelDao.getHotel(userAdmin.getHotel_id()));
		}
		
		return "admin.user.edit";
	}

	@RequestMapping(value = "/edit/{id_user}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("user") User user, @RequestParam("picture") CommonsMultipartFile cmf,
			HttpServletRequest request, @PathVariable("id_user") int id_user, RedirectAttributes ra,
			HttpSession session) {
		String password = "";
		User userAdmin = (User) session.getAttribute("userAdmin");
		User userOld = userDao.getUser(id_user);
		if ("".equals(user.getPassword())) {
			password = userOld.getPassword();
		} else {
			String password1 = user.getPassword();
			password = stringUtil.tess(password1);
		}
		user.setPassword(password);

		String fileNameOld = userOld.getAvatar();
		String fileName = cmf.getOriginalFilename();
		String reFileName = Defines.renameFile(fileName);
		if (!"".equals(fileName)) {
			String appPath = request.getServletContext().getRealPath("");
			String dirPath = appPath + Defines.DIR_UPLOAD;
			String filePath = dirPath + File.separator + reFileName;
			try {
				if ("".equals(fileNameOld)) {
					File fileOld = new File(dirPath + File.separator + fileNameOld);
					fileOld.delete();
				}
				cmf.transferTo(new File(filePath));
			} catch (Exception e) {
				return "redirect:/admin/index";
			}
		} else {
			reFileName = fileNameOld;
		}
		user.setAvatar(reFileName);
		user.setUpdatedAt(defines.getDateDay());
		if(userAdmin.getRole_id() == 1) {
			if (userDao.editUserByAdmin(user) > 0) {
				ra.addFlashAttribute("success", "Cập nhật thành công!");
			} else {
				ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
			}
		}else {
			if (userDao.editUser(user) > 0) {
				ra.addFlashAttribute("success", "Cập nhật thành công!");
			} else {
				ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
			}
		}
		
		return "redirect:/admin/user/index/" + session.getAttribute("role");
	}

	@RequestMapping(value = "/del/{id_user}")
	public String del(@PathVariable("id_user") int id_user, RedirectAttributes ra, HttpSession session) {
		if (userDao.delUser(id_user) > 0) {
			ra.addFlashAttribute("sucess", "Xóa thành công!");
		} else {
			ra.addFlashAttribute("error", "Hệ thống đang bảo trì, vui lòng thực hiện chức năng này sau!");
		}
		return "redirect:/admin/user/index/" + session.getAttribute("role");
	}
}
