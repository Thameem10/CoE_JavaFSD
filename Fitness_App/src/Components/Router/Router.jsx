import { Routes, Route } from "react-router-dom";
import Home from "../../Pages/Home";
import CreateRoutine from "../../Pages/CreateRoutine";
import ExerciseList from "../../Pages/ExerciseList";
import Contact from "../../Pages/Contact";
import MyRoutine from "../../Pages/MyRoutine";

function Router() {
  return (
    <Routes>
      <Route index element={<Home />} />
      <Route path="/Home" element={<Home />} />
      <Route path="/ExerciseList" element={<ExerciseList />} />
      <Route path="/CreateRoutine" element={<CreateRoutine />} />
      <Route path="/MyRoutine" element={<MyRoutine />} />
      <Route path="/Contact" element={<Contact />} />
    </Routes>
  );
}

export default Router;
