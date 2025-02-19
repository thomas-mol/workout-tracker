package be.thomasmol.workout_tracker.workout;

import be.thomasmol.workout_tracker.exercise.Exercise;
import be.thomasmol.workout_tracker.exercise.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<Workout> getAll() {
        return workoutRepository.findAll();
    }

    public Optional<Workout> getById(long id){
        return workoutRepository.findById(id);
    }

    @Transactional
    public Workout create(Workout workout) {
        Set<Exercise> existingExercises = new HashSet<>(exerciseRepository.findAll());
        Set<Exercise> workoutExercises = new HashSet<>();

        for (Exercise exercise : workout.getExercises()) {
            Exercise matchingExercise = existingExercises.stream()
                    .filter(e -> e.equals(exercise))
                    .findFirst()
                    .orElse(null);

            if(matchingExercise != null){
                matchingExercise.getWorkouts().add(workout);
                exerciseRepository.save(matchingExercise);
                workoutExercises.add(matchingExercise);
            }else {
                exercise.getWorkouts().add(workout);
                Exercise savedExercise = exerciseRepository.save(exercise);
                workoutExercises.add(savedExercise);
            }
        }

        workout.setExercises(workoutExercises);
        return workoutRepository.save(workout);
    }

    @Transactional
    public Optional<Workout> update(Long id, Workout workoutDetails) {
        return workoutRepository.findById(id).map(workout -> {
            workout.setName(workoutDetails.getName());
            workout.setDateRecorded(workoutDetails.getDateRecorded());
            workout.setDurationInMinutes(workoutDetails.getDurationInMinutes());
            workout.setExercises(workoutDetails.getExercises());
            return workoutRepository.save(workout);
        });
    }

    @Transactional
    public void delete(long id) {
        workoutRepository.deleteById(id);
    }
}
