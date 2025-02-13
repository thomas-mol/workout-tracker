package be.thomasmol.workout_tracker.workout;

public record Exercise(
        Integer id,
        String name,
        MuscleGroup muscleGroupTrained,
        Integer sets,
        Integer repetitions
) {}
