import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'
import { Equipe } from '../Objetos/equipe'
import { CadastroEquipeService } from '../service/cadastro-equipe.service'
import { CadastroService } from '../service/cadastro.service'
import { Pessoa } from './../Objetos/pessoa'

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  id: any
  pessoa: Pessoa = new Pessoa();
  textoBotao: string = 'Salvar';
  formInvalido = false;
  cargos = [
    {
      "id": 1,
      "nome": "Analista de Sistemas Jr."
    },
    {
      "id": 2,
      "nome": "Analista de Sistemas Pl."
    },
    {
      "id": 3,
      "nome": "Analista de Sistemas Sr."
    }
  ]
  equipes: Array<Equipe> = []
  rotaId: any
  editavel: boolean = true

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private cadastroService: CadastroService,
    private cadastroEquipeService: CadastroEquipeService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe(parametros => {
      if (parametros['id']) {
        this.textoBotao = 'Editar'
        this.id = parametros['id']
        this.cadastroService.buscarItemID(this.id).subscribe(p => {
          this.pessoa = p
        })
      }
    })

    this.cadastroEquipeService.listar().subscribe(e => {
      this.equipes = e
    })

    this.rotaId = this.route.snapshot.paramMap.get('id')
  }


  editar = () => {
    this.cadastroService.editar(this.pessoa).subscribe(
      success => this.navegar('listar'),
      error => console.log("Deu ruim"),
      () => console.log('Requisição completa'))

  }

  navegar = (rota: any) => {
    this.router.navigate([rota])
  }

  onSubmit(form: any) {

    if (form.valid == false) {
      this.formInvalido = true
    } else {
      if (this.textoBotao == 'Salvar') {
        this.cadastroService.adicionar(this.pessoa).subscribe(
          success => this.navegar('listar'),
          error => console.log("Deu ruim"),
          () => console.log('Requisição completa'))
      } else {
        this.editar()
      }
    }
  }

}
