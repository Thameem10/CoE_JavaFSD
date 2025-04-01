import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'] 
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router) {}

  login() {
    if (this.username === "thameem" && this.password === "password") {
      localStorage.setItem("username", this.username);
      localStorage.setItem("role", "admin"); 
      this.router.navigate(['/']);
      window.location.reload();
    } else {
      alert("Invalid Credentials");
    }
  }
}
