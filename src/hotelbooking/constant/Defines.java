package hotelbooking.constant;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;

import hotelbooking.model.Check;
import hotelbooking.model.Room;
import hotelbooking.model.Hotel;
import hotelbooking.model.Booking;
import hotelbooking.model.User;
import hotelbooking.dao.BookingDao;
import hotelbooking.dao.RoomDao;
import hotelbooking.dao.UserDao;
import hotelbooking.dao.HotelDao;
import java.io.File;

public class Defines {
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private HotelDao hotelDao;
	// @TODO
	public String urlPublic;
	public String urlAdmin;
	public String superAdmin;

	public static final int ROW_COUNT_ADMIN = 10;
	public static final int ROW_COUNT_PUBLIC = 10;
	public static final int SHOW_PUBLIC = 3;
	public static final String DIR_UPLOAD = "uploads";

	public String getUrlPublic() {
		return urlPublic;
	}

	public void setUrlPublic(String urlPublic) {
		this.urlPublic = urlPublic;
	}

	public String getUrlAdmin() {
		return urlAdmin;
	}

	public void setUrlAdmin(String urlAdmin) {
		this.urlAdmin = urlAdmin;
	}

	public String getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(String superAdmin) {
		this.superAdmin = superAdmin;
	}

	public static String renameFile(String fileName) {
		return FilenameUtils.getBaseName(fileName) + "-" + System.nanoTime() + "."
				+ FilenameUtils.getExtension(fileName);
	}
	
	public int recomusercontentcsv(String path) {
		String phay = ",";
		String enter = "\n";
		List<User> listUsers = userDao.getListUsers();
		String fileName = path+"data/usercontent.csv";
		File file = new File(fileName);
		file.delete();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			for (User user : listUsers) {
				String id_user = ""+user.getId_user();
				fileWriter.append(id_user);
				fileWriter.append(phay);
			}
			fileWriter.append(enter);
			for (User user : listUsers) {
				int id1 = (int) user.getId_user() ;
				for (User user2 : listUsers) {
					int id2 = (int) user2.getId_user();
					int p = 0;
					User city1 = (User) userDao.getUser(id1);
					User city2 = (User) userDao.getUser(id2);
					if(city1.getCity() == city2.getCity()) {
						p +=1; 
					}
					int year1 = Integer.parseInt(city1.getBirthday().split("-")[0]);
					int year2 = Integer.parseInt(city2.getBirthday().split("-")[0]);
					if((year1-year2) <= 2 && (year1-year2) >= -2) {
						p +=1; 
					}
					if(city1.getGender() == city2.getGender()) {
						p +=1; 
					}
					fileWriter.append(""+p);
					fileWriter.append(phay);
				}
				fileWriter.append(enter);
			}
			System.out.println("hehe !!!");
			return 1;

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int recomhotelcontentcsv(String path) {
		String phay = ",";
		String enter = "\n";
		List<Hotel> listHotels = hotelDao.getListHotel();
		String fileName = path+"data/hotelcontent.csv";
		File file = new File(fileName);
		file.delete();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			for (Hotel hotel : listHotels) {
				String id_hotel = ""+hotel.getId_hotel();
				fileWriter.append(id_hotel);
				fileWriter.append(phay);
			}
			fileWriter.append(enter);
			for (Hotel hotel : listHotels) {
				int id1 = (int) hotel.getId_hotel() ;
				for (Hotel hotel2 : listHotels) {
					int id2 = (int) hotel2.getId_hotel();
					int p = 0;
					Hotel hotel11 = (Hotel) hotelDao.getHotel(id1);
					Hotel hotel12 = (Hotel) hotelDao.getHotel(id2);
					if(hotel11.getCity_id() == hotel12.getCity_id()) {
						p +=1; 
					}
					if((hotel11.getRating()-hotel11.getRating()) >= -0.5 && (hotel11.getRating()-hotel11.getRating()) <= 0.5) {
						p +=1; 
					}
					fileWriter.append(""+p);
					fileWriter.append(phay);
				}
				fileWriter.append(enter);
			}
			System.out.println("hehe !!!");
			return 1;

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int recomroomcontentcsv(String path) {
		String phay = ",";
		String enter = "\n";
		List<Room> listRooms = roomDao.getListRooms();
		String fileName = path+"data/roomcontent.csv";
		File file = new File(fileName);
		file.delete();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			for (Room room : listRooms) {
				String id_user = ""+room.getId_room();
				fileWriter.append(id_user);
				fileWriter.append(phay);
			}
			fileWriter.append(enter);
			for (Room room : listRooms) {
				int id1 = (int) room.getId_room() ;
				for (Room room2 : listRooms) {
					int id2 = (int) room2.getId_room();
					int p = 0;
					Room room11 = (Room) roomDao.getRoom(id1);
					Room room12 = (Room) roomDao.getRoom(id2);
					if(room11.getHotel_id() == room12.getHotel_id()) {
						p +=1; 
					}
					if((room11.getRating()-room12.getRating()) <= 0.5 && (room11.getRating()-room12.getRating()) >= -0.5) {
						p +=1; 
					}
					if((room11.getPrice() - room12.getPrice())>-200000 && (room11.getPrice() - room12.getPrice() <200000)) {
						p +=1; 
					}
					if(room11.getAdult_number() == room12.getAdult_number()) {
						p +=1; 
					}
					if(room11.getBed_number() == room12.getBed_number()) {
						p +=1; 
					}
					if(room11.getChildren_number() == room12.getChildren_number()) {
						p +=1; 
					}
					if(room11.getService() == room12.getService()) {
						p +=1; 
					}
					fileWriter.append(""+p);
					fileWriter.append(phay);
				}
				fileWriter.append(enter);
			}
			System.out.println("hehe !!!");
			return 1;

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int recomusercsv(String path) {
		String phay = ",";
		String enter = "\n";
		List<Room> listRooms = roomDao.getListRoomAlls();
		List<User> listUsers = userDao.getListUsers();
		String fileName = path+"data/user.csv";
		File file = new File(fileName);
		file.delete();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			for (User user : listUsers) {
				String id_user = ""+user.getId_user();
				fileWriter.append(id_user);
				fileWriter.append(phay);
			}
			fileWriter.append(enter);
			for (User user : listUsers) {
				int id1 = (int) user.getId_user() ;
				for (User user2 : listUsers) {
					int id2 = (int) user2.getId_user();
					int count_all = roomDao.getCountListRoomAlls();
					float count = bookingDao.CountRoomUser(id1)/count_all;
					float count1 =bookingDao.CountRoomUser(id2)/count_all;
					int p = 0;
					float pow1 = 0;
					float pow2 = 0;
					for(Room room : listRooms) {
						float check1 = bookingDao.CheckUserRoom( room.getId_room(), id1)-count;
						float check2 = bookingDao.CheckUserRoom( room.getId_room(), id2)-count1;
						p += check1 * check2;
						pow1 += Math.pow(check1,2);
						pow2 += Math.pow(check2,2);
					}
					String re = "0";
					if( p != 0) {
						re = ""+p/(Math.sqrt(pow1) * Math.sqrt(pow2));//do tuong tu
					}
					fileWriter.append(re);
					fileWriter.append(phay);
				}
				fileWriter.append(enter);
			}
			System.out.println("hehe !!!");
			return 1;

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int recomroomcsv(String path) {
		String phay = ",";
		String enter = "\n";
		List<Room> listRooms = roomDao.getListRooms();
		List<User> listUsers = userDao.getListUserAlls();
		String fileName = path+"data/room.csv";
		File file = new File(fileName);
		file.delete();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			for (Room room : listRooms) {
				String id_room = ""+room.getId_room();
				fileWriter.append(id_room);
				fileWriter.append(phay);
			}
			fileWriter.append(enter);
			for (Room room : listRooms) {
				int id1 = (int) room.getId_room() ;
				for (Room room2 : listRooms) {
					int id2 = (int) room2.getId_room();
					int count_all=userDao.getCountListUserAlls();
					float count = bookingDao.CountUserRoom(id1)/count_all;
					float count1 =bookingDao.CountUserRoom(id2)/count_all;
					int p = 0;
					float pow1 = 0;
					float pow2 = 0;
					for(User user : listUsers) {
						float check1 = bookingDao.CheckUserRoom(id1, user.getId_user()) - count;
						float check2 = bookingDao.CheckUserRoom(id2, user.getId_user()) - count1;
						p += check1 * check2;
						pow1 += Math.pow(check1,2);
						pow2 += Math.pow(check2,2);
					}
					String re = "0";
					if( p != 0) {
						re = ""+p/(Math.sqrt(pow1) * Math.sqrt(pow2));//do tuong tu
					}
					fileWriter.append(re);
					fileWriter.append(phay);
				}
				fileWriter.append(enter);
			}
			System.out.println("hehe !!!");
			return 1;

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int recomhotelcsv(String path) {
		String phay = ",";
		String enter = "\n";
		String fileName = path+"data/hotel.csv";
		File file = new File(fileName);
		file.delete();
		List<Hotel> listHotels = hotelDao.getListHotel();
		List<User> listUsers = userDao.getListUserAlls();
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			for (Hotel hotel : listHotels) {
				String id_hotel = ""+hotel.getId_hotel();
				fileWriter.append(id_hotel);
				fileWriter.append(phay);
			}
			fileWriter.append(enter);
			for (Hotel hotel : listHotels) {
				int id1 = (int) hotel.getId_hotel() ;
				for(Hotel hotel2 : listHotels) {
					int id2 = (int) hotel2.getId_hotel();
					int count_all=userDao.getCountListUserAlls();
					float count = bookingDao.CountUserHotel(id1)/count_all;
					float count1 =bookingDao.CountUserHotel(id2)/count_all;
					int p = 0;
					float pow1 = 0;
					float pow2 = 0;
					for(User user : listUsers) {
						float check1 = bookingDao.CheckUserHotel(id1, user.getId_user()) - count;
						float check2 = bookingDao.CheckUserHotel(id2, user.getId_user()) - count1;
						p += check1 * check2;
						pow1 += Math.pow(check1,2);
						pow2 += Math.pow(check2,2);
					}
					String re = "0";
					if( p != 0) {
						re = ""+p/(Math.sqrt(pow1) * Math.sqrt(pow2));//do tuong tu
					}
					fileWriter.append(re);
					fileWriter.append(phay);
				}
				fileWriter.append(enter);
			}
			System.out.println("hehe !!!");
			return 1;

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}	

		return 0;
	}
	
	public List<Hotel> recomhotel(int id, String path,int max) {
		String kq = "";
		String fileName = path+"data/hotel.csv";
		if(bookingDao.CheckHotel(id)==0) {
			fileName = path+"data/hotelcontent.csv";
		}
		BufferedReader br = null;
		
		int index = 0;
		int countHotel = hotelDao.getCountListHotel();
		int [] listIdUser = new int[countHotel];
		float[][] result = new float[countHotel][countHotel];
		try {
			String line;
			br = new BufferedReader(new FileReader(fileName));
			
			// How to read file in java line by line?
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (line != null) {
					String[] splitData = line.split(",");
					for (int j = 0; j < splitData.length; j++) {
						if(i==0) {
							if(splitData[j]!="") {
								listIdUser[j] = (int) Float.parseFloat(splitData[j]);
							}
							if(((int) Float.parseFloat(splitData[j]))==id) {
								index = j;
							}
						}else {
							result[i-1][j] = Float.parseFloat(splitData[j]);
						}
						
					}
				}
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException crunchifyException) {
				crunchifyException.printStackTrace();
			}
		}
		if(countHotel<max) {
			max = countHotel;
		}
		for(int k = 0 ; k <= max-1 ; k++) {
			for(int l = k+1 ; l < countHotel ; l++) {
				if(result[index][k] < result[index][l]) {
					float t = result[index][k];
					result[index][k]=result[index][l];
					result[index][l]=t;
					int tid = listIdUser[k];
					listIdUser[k]=listIdUser[l];
					listIdUser[l]=tid;
				}
			}
			if(listIdUser[k] != id) {
				if(k==max-1) {
					kq += listIdUser[k];
				}else {
					if(listIdUser[max-1] == id && k==max-2) {
						kq += listIdUser[k];
					}else {
						kq += listIdUser[k]+",";
					}
				}
			}
		}
		String t = kq;
		return hotelDao.getListHotelRecom10s(kq);
	}
	
	public List<Room> recomroom(int id,String path,int max) {
		String kq = "";
		String fileName = path+"data/room.csv";
		if(bookingDao.CheckRoom(id)==0) {
			 fileName = path+"data/roomcontent.csv";
		}
		BufferedReader br = null;
		
		int index = 0;
		int countRoom = roomDao.getCountListRooms();
		int [] listIdRoom = new int[countRoom];
		float[][] result = new float[countRoom][countRoom];
		try {
			String line;
			br = new BufferedReader(new FileReader(fileName));
			
			// How to read file in java line by line?
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (line != null) {
					String[] splitData = line.split(",");
					for (int j = 0; j < splitData.length; j++) {
						if(i==0) {
							if(splitData[j]!="") {
								listIdRoom[j] = (int) Float.parseFloat(splitData[j]);
							}
							if(((int) Float.parseFloat(splitData[j]))==id) {
								index = j;
							}
						}else {
							result[i-1][j] = Float.parseFloat(splitData[j]);
						}
						
					}
				}
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException crunchifyException) {
				crunchifyException.printStackTrace();
			}
		}
		if(countRoom<max) {
			max = countRoom;
		}
		for(int k = 0 ; k <= max-1 ; k++) {
			for(int l = k+1 ; l < countRoom ; l++) {
				if(result[index][k] < result[index][l]) {
					float t = result[index][k];
					result[index][k]=result[index][l];
					result[index][l]=t;
					int tid = listIdRoom[k];
					listIdRoom[k]=listIdRoom[l];
					listIdRoom[l]=tid;
				}
			}
			if(listIdRoom[k] != id) {
				if(k==max-1) {
					kq += listIdRoom[k];
				}else {
					if(listIdRoom[max-1] == id && k==max-2) {
						kq += listIdRoom[k];
					}else {
						kq += listIdRoom[k]+",";
					}
				}
			}
		}
		String t = kq;
		return roomDao.getListRoomRecom10s(kq);
	}
	
	public List<Room> recom(int id,String path,int max) {
		String kq = "";
		String fileName = path+"data/user.csv";
		if(bookingDao.CheckUser(id)==0) {
			fileName = path+"data/usercontent.csv";
		}
		
		BufferedReader br = null;
		
		int index = 0;
		int countUser = userDao.getCountListUsers();
		int [] listIdUser = new int[countUser];
		float[][] result = new float[countUser][countUser];
		try {
			String line;
			br = new BufferedReader(new FileReader(fileName));
			
			// How to read file in java line by line?
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (line != null) {
					String[] splitData = line.split(",");
					for (int j = 0; j < splitData.length; j++) {
						if(i==0) {
							if(splitData[j]!="") {
								listIdUser[j] = (int) Float.parseFloat(splitData[j]);
							}
							if(((int) Float.parseFloat(splitData[j]))==id) {
								index = j;
							}
						}else {
							result[i-1][j] = Float.parseFloat(splitData[j]);
						}
						
					}
				}
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException crunchifyException) {
				crunchifyException.printStackTrace();
			}
		}
//		if(countUser<max) {
//			max = countUser;
//		}
		for(int k = 0 ; k <= countUser-1 ; k++) {
			for(int l = k+1 ; l < countUser ; l++) {
				if(result[index][k] < result[index][l]) {
					float t = result[index][k];
					result[index][k]=result[index][l];
					result[index][l]=t;
					int tid = listIdUser[k];
					listIdUser[k]=listIdUser[l];
					listIdUser[l]=tid;
				}
			}
			if(listIdUser[k] != id) {
				if(k==0) {
					kq = ""+listIdUser[k] + k;
				}else {
					kq = listIdUser[k]+"," + k;
				}
			}
		}
		List<Booking> listRecomRoom = bookingDao.getUserBooking(kq,id);
		if(max == 0) {
			listRecomRoom = bookingDao.getUserBookingAll(kq,id);
		}
		String listRoomBooking = "";
		int p = 0;
		for(Booking b : listRecomRoom) {
			if(p==0) {
				listRoomBooking = ""+b.getRoom_id() + listRoomBooking;
			}else {
				listRoomBooking = b.getRoom_id()+"," + listRoomBooking;
			}
			p++;
		}
		if(max == 0) {
			return roomDao.getListRoomForUserRecomAlls(listRoomBooking,id);
		}
		return roomDao.getListRoomForUserRecom10s(listRoomBooking,id);
	}

	public static String formatNumber(Double money) {
		Locale locale = new Locale("vi", "VN");
		DecimalFormat format2 = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
		DecimalFormatSymbols decimal = new DecimalFormatSymbols();
		decimal.setGroupingSeparator('.');
		decimal.setCurrencySymbol(" ");
		format2.setDecimalFormatSymbols(decimal);
		return format2.format(money);
	}

	public static float VNDToUSD(int money) {
		float result = (float) (0.0000429890 * money);
		result = (float) (Math.ceil(result * 100) / 100);
		return result;
	}

	public Session ConnectNeo4j() {
		Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123456"));
		Session session = driver.session();

		return session;
	}

	public boolean checkDate(Check check) {
		if ((check.getCheckin().indexOf(",") == -1) || (check.getCheckout().indexOf(",") == -1)) {

			String arrCheckIn[] = check.getCheckin().split("/");
			String checkInDate = String.format("20%s-%s-%s", arrCheckIn[2], arrCheckIn[1], arrCheckIn[0]);
			String arrCheckOut[] = check.getCheckout().split("/");
			String checkOutDate = String.format("20%s-%s-%s", arrCheckOut[2], arrCheckOut[1], arrCheckOut[0]);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			if ((sdf.parse(checkInDate, new ParsePosition(0)) != null)
					&& (sdf.parse(checkOutDate, new ParsePosition(0)) != null)) {
				check.setCheckin(checkInDate);
				check.setCheckout(checkOutDate);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int checkQuantityDate(Check check) {
		LocalDate checkInDate = LocalDate.parse(check.getCheckin());
		LocalDate checkOutDate = LocalDate.parse(check.getCheckout());
		int day = Period.between(checkInDate, checkOutDate).getDays();
		return day;
	}

	public boolean checkDateCheckIn(Check check) {
		LocalDate today = LocalDate.now();
		LocalDate checkInDate = LocalDate.parse(check.getCheckin());
		long day = Period.between(today, checkInDate).getDays();
		if (day >= 0) {
			return true;
		}
		return false;
	}

	public String getDateDay() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String presentDate = dateFormat.format(date);
		return presentDate;
	}

	public List<Integer> GetRomsCollaborative(int id_user) {
		System.out.println("GetRomsCollaborative-START");
		Session session = ConnectNeo4j();
		List<Integer> listIdRooms = new ArrayList<Integer>();
		String querry = "";
		Integer iInteger = null;
		/*
		 * querry =
		 * "MATCH (p1:user{id_user:%d})-[r:INTERACTIVE]->(ro:room) WITH p1, AVG(r.score) AS p1_mean "
		 * + "MATCH (p1)-[r1:INTERACTIVE]->(ro:room)<-[r2:INTERACTIVE]-(p2:user) " +
		 * "WITH p1, p1_mean, p2, COLLECT({r1:r1, r2:r2}) AS ratings WHERE size(ratings) > 0 "
		 * +
		 * "MATCH (P2)-[r:INTERACTIVE]->(ro:room) WITH p1, p1_mean, p2, AVG(r.score) AS p2_mean, ratings "
		 * +
		 * "UNWIND ratings AS r WITH SUM((r.r1.score - p1_mean) * (r.r2.score - p2_mean)) AS nom, "
		 * +
		 * "SQRT(SUM((r.r1.score - p1_mean)^2) * SUM((r.r2.score - p2_mean)^2)) AS denom, p1, p2 "
		 * + "WHERE denom <> 0 WITH p1, p2, nom/denom AS pearson_similarty " +
		 * "WITH p1, p2, pearson_similarty ORDER BY pearson_similarty DESC LIMIT 15 " +
		 * "OPTIONAL MATCH (p2)-[r:INTERACTIVE]->(ro:room) WHERE NOT EXISTS( (p1)-[:INTERACTIVE]->(ro) ) "
		 * +
		 * "RETURN SUM( pearson_similarty * r.score) AS recommendation, ro.id_room, ro.name "
		 * + "ORDER BY recommendation DESC LIMIT 9";
		 */

		querry = "MATCH (p1:user{id_user:%d})-[r:INTERACTIVE]->(ro:room) WITH p1, AVG(r.score) AS p1_mean "
				+ "MATCH (p1)-[r1:INTERACTIVE]->(ro:room)<-[r2:INTERACTIVE]-(p2:user) "
				+ "WITH p1, p1_mean, p2, COLLECT({r1:r1, r2:r2}) AS ratings WHERE size(ratings) > 0 "
				+ "MATCH (P2)-[r:INTERACTIVE]->(ro:room) WITH p1, p1_mean, p2, AVG(r.score) AS p2_mean, ratings "
				+ "UNWIND ratings AS r WITH SUM((r.r1.score - p1_mean) * (r.r2.score - p2_mean)) AS nom, "
				+ "SQRT(SUM((r.r1.score - p1_mean)^2) * SUM((r.r2.score - p2_mean)^2)) AS denom, p1, p2 "
				+ "WHERE denom <> 0 WITH p1, p2, nom/denom AS pearson_similarty "
				+ "WITH p1, p2, pearson_similarty ORDER BY pearson_similarty DESC LIMIT 15 "
				+ " MATCH (p2)-[r:INTERACTIVE]->(ro:room) WHERE NOT EXISTS( (p1)-[:INTERACTIVE]->(ro) ) "
				+ "WITH ro, SUM( pearson_similarty * r.score) AS recommendation RETURN  ro.id_room AS id_room ORDER BY recommendation DESC LIMIT 9";
		querry = String.format(querry, id_user);
		System.out.println(querry);
		StatementResult result = session.run(querry);
		while (result.hasNext()) {
			Record record = result.next();
			{
				int idRoom = record.get("id_room").asInt();
				iInteger = new Integer(idRoom);
				listIdRooms.add(iInteger);
			}
		}
		System.out.println("GetRomsCollaborative-END");
		return listIdRooms;
	}

	public List<Integer> GetRomsContentBased(int id_user, String temp, int limit) {
		System.out.println("GetRomsCollaborative-START");
		Session session = ConnectNeo4j();
		List<Integer> listIdRooms = new ArrayList<Integer>();
		String querry = "";
		Integer iInteger = null;
		querry = "MATCH (u: user { id_user: %d })-[r:SIMILAR]-(other:user) WHERE 1=1 %s "
				+ "WITH other, SUM(r.score) as similar_score, u ORDER BY similar_score DESC LIMIT 10 "
				+ "MATCH (other)-[i1:INTERACTIVE]-(ro:room) WITH ro, u, other, i1.score +"
				+ "similar_score as recommendation ORDER BY recommendation DESC RETURN ro.id_room AS id_room LIMIT %d";
		querry = String.format(querry, id_user, temp, limit);
		StatementResult result = session.run(querry);
		while (result.hasNext()) {
			Record record = result.next();
			if (record != null) {
				int id_room = record.get("id_room").asInt();
				iInteger = new Integer(id_room);
				listIdRooms.add(iInteger);
			}
		}
		System.out.println("GetRomsCollaborative-END");
		return listIdRooms;
	}

	public List<Integer> GetRomsContentBased2(int id_room) {
		System.out.println("GetRomsCollaborative-START");
		Session session = ConnectNeo4j();
		List<Integer> listIdRooms = new ArrayList<Integer>();
		String querry = "";
		Integer iInteger = null;
		querry = "MATCH (ro:room{id_room:%d})-[r:SIMILAR_HOTEL]-(other:room) return r.score AS recommendation, other.id_room AS id_room ORDER BY recommendation DESC LIMIT 8";
		querry = String.format(querry, id_room);
		StatementResult result = session.run(querry);
		while (result.hasNext()) {
			Record record = result.next();
			{
				int idRoom = record.get("id_room").asInt();
				iInteger = new Integer(idRoom);
				listIdRooms.add(iInteger);
			}
		}
		System.out.println("GetRomsCollaborative-END");
		return listIdRooms;
	}
}
