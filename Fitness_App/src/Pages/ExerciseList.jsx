import React from "react";
import "./ExerciseList.css"; 

const exercises = [
  { name: "Push-ups", category: "Chest" },
  { name: "Bench Press", category: "Chest" },
  { name: "Squats", category: "Legs" },
  { name: "Deadlifts", category: "Back" },
  { name: "Pull-ups", category: "Back" },
  { name: "Bicep Curls", category: "Arms" },
  { name: "Tricep Dips", category: "Arms" },
  { name: "Plank", category: "Core" },
  { name: "Leg Raises", category: "Core" },
];

function ExerciseList() {
  return (
    <div className="exercise-list">
      <div className="exercise-grid">
        {exercises.map((exercise, index) => (
          <div key={index} className="exercise-card">
            <h3>{exercise.name}</h3>
            <p>{exercise.category}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ExerciseList;
