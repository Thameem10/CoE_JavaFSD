import React, { useState, useEffect } from "react";
import "./CreateRoutine.css";

const exerciseOptions = [
  "Push-ups", "Bench Press", "Squats", "Deadlifts", 
  "Pull-ups", "Bicep Curls", "Tricep Dips", "Plank", "Leg Raises"
];

function CreateRoutine() {
  const [routineName, setRoutineName] = useState("");
  const [selectedExercise, setSelectedExercise] = useState("");
  const [routine, setRoutine] = useState([]);

  // Load existing routines from localStorage
  useEffect(() => {
    const savedRoutine = JSON.parse(localStorage.getItem("routine")) || [];
    setRoutine(savedRoutine);
  }, []);

  const addExercise = () => {
    if (routineName && selectedExercise) {
      const newRoutine = [...routine, { name: routineName, exercise: selectedExercise }];
      setRoutine(newRoutine);
      localStorage.setItem("routine", JSON.stringify(newRoutine));
      setRoutineName("");
      setSelectedExercise("");
    }
  };

  return (
    <div className="create-routine">
      <h2>Create a Routine</h2>
      <input 
        type="text" 
        placeholder="Routine Name" 
        value={routineName} 
        onChange={(e) => setRoutineName(e.target.value)}
      />
      <select value={selectedExercise} onChange={(e) => setSelectedExercise(e.target.value)}>
        <option value="">Select an Exercise</option>
        {exerciseOptions.map((exercise, index) => (
          <option key={index} value={exercise}>{exercise}</option>
        ))}
      </select>
      <button onClick={addExercise}>Add Exercise</button>

      <div className="routine-list">
        <h3>Current Routine</h3>
        {routine.map((item, index) => (
          <div key={index} className="routine-item">
            <p><strong>{item.name}:</strong> {item.exercise}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default CreateRoutine;
