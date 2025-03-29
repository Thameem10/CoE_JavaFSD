import React from "react";
import { useNavigate } from "react-router-dom"; 
import "./Navbar.css";

function Navbar() {
  const navigate = useNavigate(); 

  return (
    <div className="nav">
      <div className="nav-logo" onClick={() => navigate("/")}>Fitness</div> 
      <ul className="nav-menu">
        <li onClick={() => navigate("/")}>Home</li>
        <li onClick={() => navigate("/ExerciseList")}>Exercise List</li>
        <li onClick={() => navigate("/CreateRoutine")}>Create Routine</li>
        <li onClick={() => navigate("/MyRoutine")}>My Routine</li>
        <li className="nav-contact" onClick={() => navigate("/Contact")}>Contact</li>
      </ul>
    </div>
  );
}

export default Navbar;
