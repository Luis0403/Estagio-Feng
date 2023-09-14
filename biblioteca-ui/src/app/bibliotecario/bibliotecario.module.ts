import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BibliotecarioCadastroComponent } from './bibliotecario-cadastro/bibliotecario-cadastro.component';
import { BibliotecarioListaComponent } from './bibliotecario-lista/bibliotecario-lista.component';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { PartilhadoModule } from '../partilhado/partilhado.module';
import { FormsModule } from '@angular/forms';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { Interceptor } from '../login/auth-interceptor.service';



@NgModule({
  declarations: [
    BibliotecarioCadastroComponent,
    BibliotecarioListaComponent
  ],
  imports: [
    CommonModule,
    InputTextModule,
    PasswordModule,
    PartilhadoModule,
    FormsModule,
    CalendarModule,
    DropdownModule,
    TableModule,
    // Interceptor
  ],
  exports:[BibliotecarioCadastroComponent,BibliotecarioListaComponent]
})
export class BibliotecarioModule { }
