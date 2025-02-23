import { useState } from 'react'
import './App.css'
import 'bootstrap/dist/css/bootstrap.css'
import Background from './Components/Background/Background'
import Navbar from './Components/Navbar/Navbar'
import Router from './Components/Router/Router'
import { BrowserRouter } from 'react-router-dom'
function App() {
  const [HeroCount, setHeroCount] = useState(0);
  return (
    <BrowserRouter>
    <div>
      <Background HeroCount={HeroCount} />
      <Navbar />
      <Router />
    </div>
    </BrowserRouter>
  );
}

export default App;

