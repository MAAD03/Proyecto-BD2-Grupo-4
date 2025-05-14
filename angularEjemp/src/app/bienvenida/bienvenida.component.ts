import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router }from '@angular/router'

@Component({
  selector: 'app-bienvenida',
  templateUrl: './bienvenida.component.html',
  styleUrls: ['./bienvenida.component.css']
})
export class BienvenidaComponent {
  anuncio:any = {};
  constructor(private http:HttpClient, private router: Router){

  }

  consultarAnuncios(){
    let formularioValido:any = document.getElementById("anuncioForm");
    if(formularioValido.reportValidity()){
      
      this.servicioAnuncio().subscribe(
        (u:any) => this.darAnuncio(u)
      )
    }
  }

  darAnuncio(anuncio:any){
    if(anuncio){
      location.href="/anuncio";
    }
  }
  servicioAnuncio(){
    let httpOptions ={
      headers:new HttpHeaders({
        'Content-Type':'application/json'
      })
  }

  return this.http.get(
    "http://localhost:8080/anuncio/buscar",
    httpOptions);
  }

  crearAnuncio(){
    this.router.navigate(['/crear-anuncio']);
  }

  eliminarAnuncio(){
    this.router.navigate(['/eliminar-a']);
  }
}