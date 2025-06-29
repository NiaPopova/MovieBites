import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {


  constructor(private auth: AuthService) {}

  onLogin(email: string, password: string) {
    console.log('Login clicked:', email, password);
    this.auth.login(email, password).subscribe({
      next: (res) => console.log('Успешен вход:', res),
      error: (err) => console.error('Грешка при вход:', err)
    });
  }
}
