import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-crear-anuncio',
  templateUrl: './crear-anuncio.component.html',
  styleUrls: ['./crear-anuncio.component.css']
})
export class CrearAnuncioComponent {
  anuncio:any = {};
  constructor(private http:HttpClient){

  }
  crAnuncio(){
    let formularioValido:any = document.getElementById("aForm");
    if(formularioValido.reportValidity()){
      this.serviciAnuncio().subscribe(
        (u:any) => this.darAnunci(u)
      )
    }
  }

  darAnunci(anuncio:any){
    if(anuncio){
      location.href="/anuncio";
    }
  }
  serviciAnuncio(){
    let httpOptions ={
      headers:new HttpHeaders({
        'Content-Type':'application/json'
      })
  }

  return this.http.post(
    "http://localhost:8080/anuncio/guardar",
    this.anuncio, 
    httpOptions);
  }
}