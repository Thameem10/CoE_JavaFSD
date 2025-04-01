import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css'] 
})
export class MenuBarComponent {
  status: boolean = false;
  loginMenu: string = "Login";
  isAdmin: boolean = false; 

  constructor(private router: Router) {
    let username = localStorage.getItem("username");
    if (username) {
      this.status = true;
      this.loginMenu = `${username}, Logout`;
      this.isAdmin = localStorage.getItem("role") === "admin"; 
    }
  }

  loginHandler() { 
    if (this.status) {
      localStorage.removeItem("username");
      localStorage.removeItem("role"); 
      window.location.reload();
    } else {
      this.router.navigate(['/login']);
    }
  }
}
