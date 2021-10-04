import { Equipe } from '../Objetos/equipe';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CadastroEquipeService {
  private readonly API = `${environment.API}equipe`

  constructor(private $http: HttpClient) { }

  listar(){
    return this.$http.get<Equipe[]>(`${this.API}`)
  }

  excluirItem(id: any){
    return this.$http.delete(`${this.API}/${id}`)
  }

  adicionar(p: Equipe){
    return this.$http.post(this.API, p)
  }

  editar(p: Equipe){
    return this.$http.put(`${this.API}/${p.id}`, p)
  }

  buscarItemID(id:any){
    return this.$http.get<Equipe>(`${this.API}/${id}`)
  }

}
