package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProject(Long projectId) {
		Project project = null;
		String sql = "SELECT project_id, name, from_date, to_date FROM project WHERE project_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);

		if (results.next()) {
			project = mapRowToProject(results);
		}
		return project;
	}

	private Project mapRowToProject(SqlRowSet results) {
		Project project = new Project();
		project.setId(results.getLong("project_id"));
		project.setName(results.getString("name"));
		if (results.getDate("to_date") != null) {
			project.setToDate(results.getDate("to_date").toLocalDate());
		}
		if (results.getDate("from_date") != null) {
			project.setFromDate(results.getDate("from_date").toLocalDate());
		}
		return project;
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> listOfProjects = new ArrayList<>();
		String sql = "SELECT project_id, name, from_date, to_date FROM project;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			listOfProjects.add(mapRowToProject(results));
		}
		return listOfProjects;
	}

	@Override
	public Project createProject(Project newProject) {
	String sql = "INSERT INTO project (name, from_date, to_date) " + "VALUES(?, ?, ?) RETURNING project_id;";
	Long ProjectId = jdbcTemplate.queryForObject(sql, Long.class, newProject.getName(), newProject.getFromDate(), newProject.getToDate());
	return newProject;
	}

	@Override
	public void deleteProject(Long projectId) {
		String sql = "DELETE FROM project_employee WHERE project_id = ?;";
		jdbcTemplate.update(sql, projectId);
		String deleteProjectSql = "DELETE FROM Project WHERE project_id = ?;";
		jdbcTemplate.update(deleteProjectSql, projectId);

	}
	

}
