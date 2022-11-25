package com.example.springbootjdbc.repositories;

import com.example.springbootjdbc.entities.Tutorial;

import java.util.List;

public interface TutorialRepository {
    // interface that provides abstract methods

    // crud ops
    int save(Tutorial book);

    int update(Tutorial book);

    Tutorial findById(Long id);

    int deleteById(Long id);

    List<Tutorial> findAll();

    // custom finder methods

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

    int deleteAll();
}
