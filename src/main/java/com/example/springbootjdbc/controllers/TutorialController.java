package com.example.springbootjdbc.controllers;

import com.example.springbootjdbc.entities.Tutorial;
import com.example.springbootjdbc.models.CreateTutorialModel;
import com.example.springbootjdbc.models.ResponseModel;
import com.example.springbootjdbc.models.ResponseWithDataModel;
import com.example.springbootjdbc.models.UpdateTutorialModel;
import com.example.springbootjdbc.services.TutorialService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class TutorialController {
    private final Logger logger = LoggerFactory.getLogger(TutorialController.class);
    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<ResponseModel> getAllTutorials(@RequestParam(required = false) String title) {

        try {
            List<Tutorial> tutorials;
            if (title == null) {
                tutorials = tutorialService.FindAllTutorials();
            } else {
                tutorials = tutorialService.findTutorialsByTitleContaining(title);
            }
            ResponseModel response = new ResponseWithDataModel<>(true, tutorials);
            logger.info("Response: {}", response);
            return new ResponseEntity<ResponseModel>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<ResponseModel> getTutorialById(@PathVariable("id") @Min(1) long id) {
        Tutorial tutorial = tutorialService.findTutorial(id);

        if (tutorial != null) {
            ResponseModel response = new ResponseWithDataModel<>(true, tutorial);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseModel response = new ResponseModel(false, "A problem occurred while fetching tutorial");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<ResponseModel> createTutorial(@Valid @RequestBody CreateTutorialModel tutorial) {
        try {
            tutorialService.saveTutorial(tutorial);
            ResponseModel response = new ResponseModel(true, "Tutorial created successfully");
            return new ResponseEntity<ResponseModel>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel(false, "Tutorial not created");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<ResponseModel> updateTutorial(@PathVariable("id") long id, @RequestBody UpdateTutorialModel tutorial) {
        try {
            tutorialService.updateTutorial(tutorial, id);
            ResponseModel response = new ResponseModel(true, "Tutorial was updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException ex){
            ResponseModel response = new ResponseModel(false, "Cannot find Tutorial");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<ResponseModel> deleteTutorial(@PathVariable("id") long id) {
        try {
            int result = tutorialService.deleteTutorial(id);
            logger.info("Result from delete endpoint: {}", result);
            if (result == 0) {
                ResponseModel response = new ResponseModel(false, "Can't find tutorial");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            ResponseModel response = new ResponseModel(true, "Tutorial was deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel(false, "Can't delete tutorial");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<ResponseModel> deleteAllTutorials() {
        try {
            int numRows = tutorialService.deleteAll();
            ResponseModel response = new ResponseModel(true, "Deleted \" + numRows + \" Tutorial(s) successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseModel response = new ResponseModel(false, "Cannot delete tutorials.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<ResponseModel> findByPublished() {
        try {
            List<Tutorial> tutorials = tutorialService.findTurialsByPublishedStatus(true);
            ResponseModel response = new ResponseWithDataModel<>(true, tutorials);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
