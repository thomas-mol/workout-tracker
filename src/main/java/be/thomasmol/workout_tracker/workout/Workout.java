package be.thomasmol.workout_tracker.workout;

import java.time.LocalDateTime;
import java.util.List;

public record Workout(
        Integer id,
        String title,
        LocalDateTime dateRecorded,
        List<Exercise> exercises
) {}
