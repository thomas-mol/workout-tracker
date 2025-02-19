package be.thomasmol.workout_tracker.workout;

import be.thomasmol.workout_tracker.exercise.Exercise;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_recorded")
    private LocalDateTime dateRecorded;

    @Column(name = "duration_in_minutes")
    private int durationInMinutes;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "workout_exercises",
            joinColumns = @JoinColumn(name="workout_id"),
            inverseJoinColumns = @JoinColumn(name="exercise_id")
    )
    private Set<Exercise> exercises = new HashSet<>();

    public Workout() {
    }

    public Workout(long id, String name, LocalDateTime dateRecorded, int durationInMinutes, Set<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.dateRecorded = dateRecorded;
        this.durationInMinutes = durationInMinutes;
        this.exercises = exercises;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(LocalDateTime dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}
