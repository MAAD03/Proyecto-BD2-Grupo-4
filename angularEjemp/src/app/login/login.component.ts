import { Component } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  usuario:any = {};
  constructor(private htpp:HttpClient){

  }

  login(){
    let formularioValido:any = document.getElementById("loginForm");
    if(formularioValido.reportValidity()){

      this.servicioLogin().subscribe(
        (u:any) => this.darBienvenida(u)
      )
    }
  }

  darBienvenida(usuario:any){
    if(usuario){
      location.href="/bienvenida";
    }
    else{
      alert("Usuario o password invalido.")
    }
  }
  servicioLogin(){
    let httpOptions ={
      headers:new HttpHeaders({
        'Content-Type':'application/json'
      })
  }

  return this.htpp.post(
    "http://localhost:8080/api/auth/login",
    this.usuario, httpOptions);
  }
}
