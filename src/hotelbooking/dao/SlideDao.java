package hotelbooking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hotelbooking.model.Contact;
import hotelbooking.model.Slide;

@Repository
public class SlideDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Slide> getListSlide() {
		String sql = "SELECT * FROM slide";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Slide>(Slide.class));
	}

	public List<Slide> getListSlideON() {
		String sql = "SELECT * FROM slide WHERE status = 1";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Slide>(Slide.class));
	}

	public int addSlide(Slide slide) {
		String sql = "INSERT INTO slide(status, image) VALUES(?,?)";
		return jdbcTemplate.update(sql, new Object[] { slide.getStatus(), slide.getImage() });
	}

	public Slide getSlide(int id_slide) {
		String sql = "SELECT * FROM slide WHERE id_slide = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id_slide },
				new BeanPropertyRowMapper<Slide>(Slide.class));
	}

	public int editSlide(Slide slide) {
		String sql = "UPDATE slide SET status = ?, image = ? WHERE id_slide = ? ";
		return jdbcTemplate.update(sql, new Object[] { slide.getStatus(), slide.getImage(), slide.getId_slide() });
	}

	public int delSlide(int id_slide) {
		String sql = "DELETE FROM slide WHERE id_slide = ?";
		return jdbcTemplate.update(sql, new Object[] { id_slide });
	}

	public int InsertContact(Contact contact) {
		String sql = "INSERT INTO contact(name , email, message) VALUES(?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { contact.getName(), contact.getEmail(), contact.getMessage() });
	}
}
