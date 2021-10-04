import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component'
import { CadastroComponent } from './cadastro/cadastro.component';
import { ListaProfissionalComponent } from './lista-profissional/lista-profissional.component';
import { CadastroEquipeComponent } from './cadastro-equipe/cadastro-equipe.component'
import { ListaEquipesComponent } from './lista-equipes/lista-equipes.component'

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'cadastro', component: CadastroComponent},
  {path: 'cadastro/:id', component: CadastroComponent},
  {path: 'listar', component: ListaProfissionalComponent},
  {path: 'cadastro-equipe', component: CadastroEquipeComponent},
  {path: 'listar-equipes', component: ListaEquipesComponent},
  {path: 'cadastro-equipe/:id', component: CadastroEquipeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
