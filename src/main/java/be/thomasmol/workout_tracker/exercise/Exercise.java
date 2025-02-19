package be.thomasmol.workout_tracker.exercise;

import be.thomasmol.workout_tracker.workout.Workout;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "EXERCISE")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "muscleGroup")
    private MuscleGroup muscleGroup;

    @Column(name = "sets")
    private int sets;

    @Column(name = "repetitions")
    private int repetitions;


    @ManyToMany(mappedBy = "exercises")
    @JsonIgnore
    private Set<Workout> workouts = new HashSet<>();

    public Exercise() {
    }

    public Exercise(String name, MuscleGroup muscleGroup) {
        this.name = name;
        this.muscleGroup = muscleGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Exercise exercise = (Exercise) obj;
        if(exercise.sets != sets || exercise.repetitions != repetitions) return false;
        return name.equalsIgnoreCase(exercise.name) && muscleGroup == exercise.muscleGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), muscleGroup);
    }
}
