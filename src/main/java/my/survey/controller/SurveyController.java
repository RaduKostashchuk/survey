package my.survey.controller;

import my.survey.model.Survey;
import my.survey.model.SurveyResult;
import my.survey.service.SurveyResultService;
import my.survey.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/survey")
public class SurveyController {
    private final SurveyService surveys;
    private final SurveyResultService results;

    public SurveyController(SurveyService surveys, SurveyResultService results) {
        this.surveys = surveys;
        this.results = results;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Survey>> getAll() {
        return new ResponseEntity<>(surveys.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getById(@PathVariable @Positive(message = "Id опроса должен быть > 0") int id) {
        return new ResponseEntity<>(surveys.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Survey> save(@RequestBody Survey survey) {
        return new ResponseEntity<>(surveys.save(survey), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive(message = "Id опроса должен быть > 0") int id) {
        surveys.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Survey survey) {
        surveys.update(survey);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/start")
    public ResponseEntity<Void> startSurvey(@RequestBody SurveyResult result) {
        results.begin(result);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/end")
    public ResponseEntity<Void> endSurvey(@RequestBody SurveyResult result) {
        results.end(result);
        return ResponseEntity.ok().build();
    }
}
