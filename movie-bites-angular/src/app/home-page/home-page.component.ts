import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'home-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  isDropdownOpen = false;

  constructor(private router: Router) {}

  goToLogin() {
    this.router.navigate(['/login']);
  }

showDropdown() {
  this.isDropdownOpen = true;
}

hideDropdown() {
 
  setTimeout(() => {
    this.isDropdownOpen = false;
  }, 200);
}

toggleDropdown() {
  this.isDropdownOpen = !this.isDropdownOpen;
}

closeDropdown() {
  this.isDropdownOpen = false;
}

onDropdownClick() {
  this.isDropdownOpen = true;
}
}
