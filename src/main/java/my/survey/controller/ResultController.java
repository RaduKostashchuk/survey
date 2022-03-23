package my.survey.controller;

import my.survey.model.SurveyResult;
import my.survey.service.SurveyResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
public class ResultController {
    private final SurveyResultService results;

    public ResultController(SurveyResultService results) {
        this.results = results;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<SurveyResult>> findAll() {
        return new ResponseEntity<>(results.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<SurveyResult>> findByUserId(@PathVariable String id) {
        return new ResponseEntity<>(results.findByUserId(id), HttpStatus.OK);
    }
}
