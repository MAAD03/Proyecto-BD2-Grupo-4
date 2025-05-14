import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-anuncio',
  templateUrl: './anuncio.component.html',
  styleUrls: ['./anuncio.component.css']
})

export class AnuncioComponent {
  anuncios:any = [];

  constructor(private htpp:HttpClient){
    this.buscarAnuncio();
  }

  buscarAnuncio(){
    this.servicioBuscarAnuncios().subscribe(
      (u:any)=>this.anuncios = u
    )
  }
  servicioBuscarAnuncios():Observable<any>{
    return this.htpp.get<any>("http://localhost:8080/anuncio/buscar");
  }
}
