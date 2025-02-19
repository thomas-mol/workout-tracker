package be.thomasmol.workout_tracker.exercise;

import be.thomasmol.workout_tracker.workout.Workout;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getById(long id){
        return exerciseRepository.findById(id);
    }
}
