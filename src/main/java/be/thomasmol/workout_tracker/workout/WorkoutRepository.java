package be.thomasmol.workout_tracker.workout;

import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
