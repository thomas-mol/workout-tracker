package be.thomasmol.workout_tracker.workout;

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

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAll() {
        return workoutService.getAll();
    }

    @GetMapping("/{id}")
    public  Workout getById(@PathVariable long id){
        Optional<Workout> workout = workoutService.getById(id);
        if(workout.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        else return workout.get();
    }

    @PostMapping
    public ResponseEntity<Workout> create(@RequestBody Workout workout) {
        var createdWorkout = workoutService.create(workout);
        return ResponseEntity.ok(createdWorkout);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Workout> update(@PathVariable long id, @RequestBody Workout workout) {
        Optional<Workout> updatedWorkout = workoutService.update(id, workout);
        return updatedWorkout.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        workoutService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
