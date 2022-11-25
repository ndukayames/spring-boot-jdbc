package com.example.springbootjdbc.services;

import com.example.springbootjdbc.entities.Tutorial;
import com.example.springbootjdbc.models.CreateTutorialModel;
import com.example.springbootjdbc.models.UpdateTutorialModel;
import com.example.springbootjdbc.repositories.JdbcTutorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService{

    private final Logger logger = LoggerFactory.getLogger(TutorialService.class);
    @Autowired
    JdbcTutorialRepository jdbcTutorialRepository;
    @Override
    public int saveTutorial(CreateTutorialModel tutorial) {
        Tutorial newTutorial = new Tutorial();
        newTutorial.setDescription(tutorial.getDescription());
        newTutorial.setTitle(tutorial.getTitle());
        return jdbcTutorialRepository.save(newTutorial);
    }
    @Override
    public int updateTutorial(UpdateTutorialModel tutorial, long id) {
        // find tutorial
        Tutorial _tutorial = jdbcTutorialRepository.findById(id);
        if(_tutorial != null) {
            if(tutorial.isPublished() || !tutorial.isPublished()) {
                _tutorial.setPublished(tutorial.isPublished());
            }
            if(tutorial.getDescription() != null) {
                _tutorial.setDescription(tutorial.getDescription());
            }
            if(tutorial.getTitle() != null) {
                _tutorial.setTitle(tutorial.getTitle());
            }
        } else {
            throw new RuntimeException("Could not find tutorial");
        }
        return jdbcTutorialRepository.update(_tutorial);
    }

    @Override
    public Tutorial findTutorial(Long id) {
        return jdbcTutorialRepository.findById(id);
    }

    @Override
    public int deleteTutorial(Long id) {
        return jdbcTutorialRepository.deleteById(id);
    }

    @Override
    public List<Tutorial> FindAllTutorials() {
        List<Tutorial> tutorials = jdbcTutorialRepository.findAll();
        logger.info("Tutorials: {}", tutorials);
        return tutorials;
    }

    @Override
    public List<Tutorial> findTurialsByPublishedStatus(boolean published) {
        return jdbcTutorialRepository.findByPublished(published);
    }

    @Override
    public List<Tutorial> findTutorialsByTitleContaining(String title) {
        return jdbcTutorialRepository.findByTitleContaining(title);
    }

    @Override
    public int deleteAll() {
        return jdbcTutorialRepository.deleteAll();
    }
}
