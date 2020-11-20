package hotelbooking.constant;

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

import org.apache.commons.io.FilenameUtils;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import hotelbooking.model.Check;

public class Defines {
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
