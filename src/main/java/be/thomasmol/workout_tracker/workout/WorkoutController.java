package be.thomasmol.workout_tracker.workout;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService){
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAll() {
        return workoutService.getAll();
    }

    @PostMapping
    public ResponseEntity<Workout> create(@RequestBody Workout workout){
        var createdWorkout = workoutService.create(workout);
        return ResponseEntity.ok(createdWorkout);
    }
}
