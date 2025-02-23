import React, { useState } from "react";
import Hero from "../Components/Hero/Hero";
import Background from "../Components/Background/Background";

function Home() {
  const HeroData = [
    { text1: "Dive into", text2: "what you love" },
    { text1: "Indulge", text2: "your passion" },
    { text1: "Give in to", text2: "your dreams" },
  ];

  const [HeroCount, setHeroCount] = useState(0);

  return (
    <div>
        <Background HeroCount={HeroCount} />
      <Hero HeroCount={HeroCount} setHeroCount={setHeroCount} HeroData={HeroData} />
    </div>
  );
}

export default Home;
