package com.example.springbootjdbc.repositories;

import com.example.springbootjdbc.entities.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTutorialRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int save(Tutorial tutorial) {
        return jdbcTemplate.update("INSERT INTO tutorials (title, description, published) VALUES(?,?,?)",
                tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished());
    }
    public int update(Tutorial tutorial) {
        return jdbcTemplate.update("UPDATE tutorials SET title=?, description=?, published=? WHERE id=?",
                new Object[] { tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished(), tutorial.getId() });
    }
    public Tutorial findById(Long id) {
        try {
            Tutorial tutorial = jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Tutorial.class), id);

            return tutorial;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM tutorials WHERE id=?", id);
    }
    public List<Tutorial> findAll() {
        return jdbcTemplate.query("SELECT * from tutorials", BeanPropertyRowMapper.newInstance(Tutorial.class));
    }
    public List<Tutorial> findByPublished(boolean published) {
        return jdbcTemplate.query("SELECT * from tutorials WHERE published=?",
                BeanPropertyRowMapper.newInstance(Tutorial.class), published);
    }
    public List<Tutorial> findByTitleContaining(String title) {
        String q = "SELECT * from tutorials WHERE title LIKE '%" + title + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Tutorial.class));
    }
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from tutorials");
    }
}
