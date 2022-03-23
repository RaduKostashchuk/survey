package my.survey.controller;

import my.survey.model.Question;
import my.survey.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questions;

    public QuestionController(QuestionService questions) {
        this.questions = questions;
    }

    @PostMapping("/add/{surveyId}")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question,
                                                @PathVariable
                                                @Positive(message = "Id опроса должен быть > 0")int surveyId) {
        return new ResponseEntity<>(questions.save(surveyId, question), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive(message = "Id вопроса должен быть > 0") int id) {
        questions.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{surveyId}")
    public ResponseEntity<Void> update(@RequestBody Question question,
                                       @PathVariable
                                       @Positive(message = "Id опроса должен быть > 0") int surveyId) {
        questions.update(surveyId, question);
        return ResponseEntity.ok().build();
    }
}
