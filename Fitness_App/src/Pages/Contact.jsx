import React from "react";
import "./Contact.css";

function Contact() {
  return (
    <div className="contact-container">
      <h2>Contact Us</h2>
      <p>If you have any questions or need support, feel free to reach out to us!</p>
      
      <div className="contact-details">
        <p><strong>Email:</strong> support@fitnessapp.com</p>
        <p><strong>Phone:</strong> +1 (555) 123-4567</p>
        <p><strong>Address:</strong> 123 Fitness St, Workout City, India</p>
      </div>

      <div className="contact-form">
        <h3>Send Us a Message</h3>
        <input type="text" placeholder="Your Name" />
        <input type="email" placeholder="Your Email" />
        <textarea placeholder="Your Message"></textarea>
        <button>Send Message</button>
      </div>
    </div>
  );
}

export default Contact;
