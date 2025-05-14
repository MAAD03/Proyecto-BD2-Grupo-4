import { Component } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-elimnar-a',
  templateUrl: './elimnar-a.component.html',
  styleUrls: ['./elimnar-a.component.css']
})
export class ElimnarAComponent {
  anuncio:any = {};
  constructor(private http:HttpClient){

  }
  eliminarAnuncio() {
    if (this.anuncio && this.anuncio.id) {
      this.serviciAnuncio(this.anuncio.id).subscribe(
        (response: any) => {
          // Realiza acciones después de la eliminación si es necesario
          console.log('Anuncio eliminado con éxito.');
          // Puedes redirigir a otra página aquí si lo deseas.
        },
        (error: any) => {
          console.error('Error al eliminar el anuncio:', error);
        }
      );
    }
  }

  darAnunci(anuncio:any){
    if(anuncio){
      location.href="/anuncio";
    }
  }
  serviciAnuncio(id: number) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
  
    return this.http.delete(
      `http://localhost:8080/anuncio/eliminar/${id}`,
      httpOptions
    );
  }
}
