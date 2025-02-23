import React from 'react'
import arrow_btn from '../../assets/arrow_btn.png';
import './Hero.css'
function Hero({ HeroData, HeroCount, setHeroCount }) {
    return (
      <div className="hero">
        <div className="hero-text">
          <p>{HeroData[HeroCount].text1}</p>
          <p>{HeroData[HeroCount].text2}</p>
        </div>
        <div className="hero-explorer">
          <p>Explore the Feature</p>
          <img src={arrow_btn} alt="" />
        </div>
        <div className="hero-dot-play">
          <ul className="hero-dot">
            <li onClick={() => setHeroCount(0)} className={HeroCount === 0 ? "hero-dot orange" : "hero-dot"}></li>
            <li onClick={() => setHeroCount(1)} className={HeroCount === 1 ? "hero-dot orange" : "hero-dot"}></li>
            <li onClick={() => setHeroCount(2)} className={HeroCount === 2 ? "hero-dot orange" : "hero-dot"}></li>
          </ul>
        </div>
      </div>
    );
  }
export default Hero;  