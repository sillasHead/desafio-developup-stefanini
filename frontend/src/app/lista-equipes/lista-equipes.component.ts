import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { Equipe } from '../Objetos/equipe'
import { CadastroEquipeService } from '../service/cadastro-equipe.service'

@Component({
  selector: 'app-lista-equipes',
  templateUrl: './lista-equipes.component.html',
  styleUrls: ['./lista-equipes.component.css']
})
export class ListaEquipesComponent implements OnInit {

  prod: any
  equipes: Array<Equipe> = []
  carregarLoading: boolean = false
  constructor(private cadastroService: CadastroEquipeService, private router: Router) { }

  ngOnInit(): void {

    this.cadastroService.listar().subscribe(e =>{
      setTimeout(() =>{
        this.carregarLoading  = true
        this.equipes = e
      },2000)
    })
  }

  excluirItem = (id: any) =>{
    this.cadastroService.excluirItem(id).subscribe(
      success => this.ngOnInit(),
      error => alert("Algo deu errado na transação"),
      () => console.log('Requisição completa')
      )
      //this.ngOnInit();
  }

  editar = (id:any) =>{
    this.router.navigate(['cadastro-equipe', id])
  }

  navegar = () =>{
    this.router.navigate(['cadastro-equipe'])
  }


}
