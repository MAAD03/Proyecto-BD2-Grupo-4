import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angularEjemp';
  mensaje : String ="";

  saludar(){
    
    console.log("Hola mundo en consola");
    alert("Hola mundo en ventana");

    this.mensaje = "Hola mundo";
  }
  
}
