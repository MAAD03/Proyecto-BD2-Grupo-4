import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DatabaseService } from './services/database.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  username: string = '';
  password: string = '';
  sqlScript: string = '';
  result: any = null;
  isLoggedIn: boolean = false;

  constructor(private databaseService: DatabaseService) {}

  login() {
    this.databaseService.login(this.username, this.password).subscribe({
      next: (response) => {
        console.log('Respuesta del backend (login):', response);
        this.result = response;
        if (response === "Login exitoso") {
          this.isLoggedIn = true;
          this.result = null;
        }
      },
      error: (error) => {
        console.error('Error en login:', error);
        this.result = 'Error al iniciar sesión: ' + error.message;
      }
    });
  }

  executeScript() {
    this.databaseService.executeScript(this.sqlScript).subscribe({
      next: (response: string) => {
        console.log('Respuesta del backend (execute):', response);
        try {
          this.result = JSON.parse(response);
        } catch (e) {
          this.result = response;
        }
      },
      error: (error) => {
        console.error('Error en execute:', error);
        this.result = 'Error: ' + (error.message || error);
      }
    });
  }

  logout() {
    this.isLoggedIn = false;
    this.username = '';
    this.password = '';
    this.sqlScript = '';
    this.result = null;
  }
}
