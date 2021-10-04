import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { Equipe } from '../Objetos/equipe'
import { CadastroEquipeService } from '../service/cadastro-equipe.service'

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro-equipe.component.html',
  styleUrls: ['./cadastro-equipe.component.css']
})
export class CadastroEquipeComponent implements OnInit {

  id: any
  equipe: Equipe = new Equipe();
  textoBotao: string = 'Salvar';
  formInvalido = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private cadastroService: CadastroEquipeService
  ) { }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe(parametros =>{
      if(parametros['id']){
        this.textoBotao = 'Editar'
        this.id = parametros['id']
        this.cadastroService.buscarItemID(this.id).subscribe(p =>{
          this.equipe = p
        })
      }
    })
  }


  editar = () =>{
    this.cadastroService.editar(this.equipe).subscribe(
      success => this.navegar('listar-equipes'),
      error => console.log("Deu ruim"),
      () => console.log('Requisição completa'))

  }

  navegar = (rota: any) =>{
    this.router.navigate([rota])
  }

  onSubmit(form:any){

    if(form.valid == false){
      this.formInvalido = true
    }else{
      if(this.textoBotao == 'Salvar'){
        this.cadastroService.adicionar(this.equipe).subscribe(
          success => this.navegar('listar-equipes'),
          error => console.log("Deu ruim"),
          () => console.log('Requisição completa'))
      }else{
        this.editar()
      }
    }
  }

}
