package hotelbooking.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hotelbooking.model.Prepayment;

@Repository
public class PrepaymentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Prepayment> getListPrepayments() {
		String sql = "SELECT * FROM prepayment";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Prepayment>(Prepayment.class));
	}
	
	
}
