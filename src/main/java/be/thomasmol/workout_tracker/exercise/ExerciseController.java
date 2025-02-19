package be.thomasmol.workout_tracker.exercise;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<Exercise> getAll(){
        return exerciseService.getAll();
    }

    @GetMapping("/{id}")
    public Exercise getById(@PathVariable long id){
        Optional<Exercise> exercise = exerciseService.getById(id);
        if(exercise.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        else return exercise.get();
    }
}
