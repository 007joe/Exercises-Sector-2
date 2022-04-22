package com.techelevator.projects.dao;

import com.techelevator.projects.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcDepartmentDao implements DepartmentDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcDepartmentDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Department getDepartment(Long id) {
        Department department = null;
        String sql =
                "SELECT department_id, name FROM department WHERE department_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            department = mapRowToDepartment(results);
        }
        return department;
    }

    private Department mapRowToDepartment(SqlRowSet results) {
        Department department = new Department();
        department.setName(results.getString("name"));
        department.setId(results.getLong("department_id"));
        return department;
    }


    @Override
    public List<Department> getAllDepartments() {
        List<Department> listOfDepartments = new ArrayList<>();
        String sql =
                "SELECT department_id, name FROM department;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            listOfDepartments.add(mapRowToDepartment(results));
        }
        return listOfDepartments;
    }

    @Override
    public void updateDepartment(Department updatedDepartment) {
        String sql = "UPDATE department " +
                "Set name = ? " +
                "WHERE department_id = ?;";
        jdbcTemplate.update(sql, updatedDepartment.getName(), updatedDepartment.getId());
    }

}
