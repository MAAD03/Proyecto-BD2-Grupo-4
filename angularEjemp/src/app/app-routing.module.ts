import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { BienvenidaComponent } from './bienvenida/bienvenida.component';
import { AnuncioComponent } from './anuncio/anuncio.component';
import { CrearAnuncioComponent } from './crear-anuncio/crear-anuncio.component';
import { ElimnarAComponent } from './elimnar-a/elimnar-a.component';


const routes: Routes = [
    {path:'',component:LoginComponent},
    {path:'bienvenida', component: BienvenidaComponent},
    {path:'anuncio', component: AnuncioComponent},
    {path: 'crear-anuncio', component: CrearAnuncioComponent},
    {path: 'eliminar-a', component: ElimnarAComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
