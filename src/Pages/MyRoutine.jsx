import React, { useState, useEffect } from "react";
import "./MyRoutine.css";

function MyRoutine() {
  const [routine, setRoutine] = useState([]);

  useEffect(() => {
    const savedRoutine = JSON.parse(localStorage.getItem("routine")) || [];
    setRoutine(savedRoutine);
  }, []);

  const clearRoutine = () => {
    localStorage.removeItem("routine");
    setRoutine([]);
  };

  return (
    <div className="my-routine">
      <h2>My Routine</h2>
      {routine.length > 0 ? (
        <div>
          {routine.map((item, index) => (
            <div key={index} className="routine-item">
              <p><strong>{item.name}:</strong> {item.exercise}</p>
            </div>
          ))}
          <button className="clear-btn" onClick={clearRoutine}>Clear Routine</button>
        </div>
      ) : (
        <p>No routines found. Create one first!</p>
      )}
    </div>
  );
}

export default MyRoutine;
