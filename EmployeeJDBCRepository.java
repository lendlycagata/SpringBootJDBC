package com.javaAbs.springboot.jdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.javaAbs.springboot.jdbc.model.Employee;

@Repository
@Component
public class EmployeeJDBCRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class EmployeeRowMapper implements RowMapper < Employee > {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee curse = new Employee();
            curse.setId(rs.getInt("id"));
            curse.setFirstName(rs.getString("first_name"));
            curse.setLastName(rs.getString("last_name"));
            curse.setEmailId(rs.getString("email_address"));
            return curse;
        }
    }

    public List < Employee > findAll() {
        return jdbcTemplate.query("select * from employee", new EmployeeRowMapper());
    }

    public Optional < Employee > findById(int id) {
        return Optional.of(jdbcTemplate.queryForObject("select * from employee where id=?", new Object[] {
                id
            },
            new BeanPropertyRowMapper < Employee > (Employee.class)));
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from employee where id=?", new Object[] {
            id
        });
    }

    public int insert(Employee curse) {
        return jdbcTemplate.update("insert into employee (id, first_name, last_name, email_address) " + "values(?, ?, ?, ?)",
            new Object[] {
        		curse.getId(), curse.getFirstName(), curse.getLastName(), curse.getEmailId()
            });
    }

    public int update(Employee curse) {
        return jdbcTemplate.update("update employee " + " set first_name = ?, last_name = ?, email_address = ? " + " where id = ?",
            new Object[] {
        		curse.getFirstName(), curse.getLastName(), curse.getEmailId(), curse.getId()
            });
    }
}
