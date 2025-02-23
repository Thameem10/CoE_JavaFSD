import React, { useState } from 'react'
import './Background.css'
import image1 from '../../assets/image1.png';
import image2 from '../../assets/image2.png';
import image3 from '../../assets/image3.png';

function Background({ HeroCount }) {
  const images = [image1, image2, image3];

  return (
    <div>
      <img key={HeroCount} src={images[HeroCount]} className="background" alt="Background" />
    </div>
  );
}
export default Background;