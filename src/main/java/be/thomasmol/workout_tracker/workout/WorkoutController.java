package be.thomasmol.workout_tracker.workout;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAll() {
        return workoutService.getAll();
    }

    @PostMapping
    public ResponseEntity<Workout> create(@RequestBody Workout workout) {
        var createdWorkout = workoutService.create(workout);
        return ResponseEntity.ok(createdWorkout);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Workout> update(@PathVariable long id, @RequestBody Workout workout) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

    }
}
