import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AppComponent } from 'src/app/app.component';
import { ServicosService } from 'src/app/login/servicos.service';
import { LivrosService } from '../livros.service';


@Component({
  selector: 'app-livro-cadastro',
  templateUrl: './livro-cadastro.component.html',
  styleUrls: ['./livro-cadastro.component.css']
})
export class LivroCadastroComponent {
  public formdata=new FormData(); 
  public file:File | any;
  public habilitar_upload=false;
  public contProgress=0;
  

    constructor(private livrosServicos: LivrosService, private lonServicos: ServicosService, private appComponent: AppComponent){}

    //metodo para upload file:File,
    uploadLivro(file:File){
      this.file=file; 
    }

    //metodo para gravar todo livro
    salvarConteudoLivro(formulario: NgForm){
      let fileUploaded=false;
      
      this.formdata.append('file', this.file, this.file.name);

      const livro= {
        
        "titulo": `${this.file.name}`,
        "autor": `${formulario.value.autor}`,
        "disciplina": `${formulario.value.disciplina}`,
        "editora": `${formulario.value.editora}`,
        "areaDeConhecimento": `${formulario.value.areaDeConhecimento}`,
        "descricao": `${formulario.value.descricao}`,
        "utilizador": {
                        "nome": `${this.lonServicos.paylaodToken.sub}`
                      }
           
                    };

      if(this.lonServicos.isLoggado){
          if(this.file.type.match('application/pdf') && this.file.size<=30*1024*1024){
            this.livrosServicos.upload(this.formdata)
            this.livrosServicos.saveBook(livro);
          }
          else{
            this.appComponent.mostarMessage("error","Erro","Apenas sao aceites arquivos PDFs com tamanho menor que 30MB");
          }
      }
      else
          this.lonServicos.openBtnLogin=true;

          
    }
}
