package com.example.springbootjdbc.services;

import com.example.springbootjdbc.entities.Tutorial;
import com.example.springbootjdbc.models.CreateTutorialModel;
import com.example.springbootjdbc.models.UpdateTutorialModel;

import java.util.List;

public interface TutorialService {
    int saveTutorial(CreateTutorialModel book);

    int updateTutorial(UpdateTutorialModel tutorial, long id );

    Tutorial findTutorial(Long id);

    int deleteTutorial(Long id);

    List<Tutorial> FindAllTutorials();

    // custom finder methods

    List<Tutorial> findTurialsByPublishedStatus(boolean published);

    List<Tutorial> findTutorialsByTitleContaining(String title);

    int deleteAll();
}
