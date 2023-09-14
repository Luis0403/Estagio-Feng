import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EstudanteComponent } from './estudante-cadastro/estudante.component';
import { PartilhadoModule } from '../partilhado/partilhado.module';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { FormsModule, NgForm } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { PasswordModule } from "primeng/password";
import { EstudanteGridComponent } from './estudante-grid/estudante-grid.component';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { LoginComponent } from '../login/login/login.component';
import { AuthInterceptorService, Interceptor } from '../login/auth-interceptor.service';


@NgModule({
  declarations: [
    EstudanteComponent,
    EstudanteGridComponent
  ],
  imports: [
    CommonModule,
    DropdownModule,
    CalendarModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    PasswordModule,
    TooltipModule,
    TableModule,
    //Interceptor,


    PartilhadoModule
  ],
  exports:[EstudanteComponent,EstudanteGridComponent],
  providers:[LoginComponent, AuthInterceptorService]
})
export class EstudanteModule { }
