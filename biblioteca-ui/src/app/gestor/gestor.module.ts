import { DropdownModule } from 'primeng/dropdown';
import { AppRoutingModule } from './../app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GestorGridComponent } from './gestor-grid/gestor-grid.component';
import { GestorPesquisaComponent } from './gestor-pesquisa/gestor-pesquisa.component';
import { GestorCadastroComponent } from './gestor-cadastro/gestor-cadastro.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { Interceptor } from '../login/auth-interceptor.service';



@NgModule({
  declarations: [
    GestorGridComponent,
    GestorPesquisaComponent,
    GestorCadastroComponent
  ],

  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    DropdownModule,
    FormsModule,
    BrowserAnimationsModule,
    InputTextModule,
    ButtonModule,
    TooltipModule,
    TableModule,
    CalendarModule,
    Interceptor
    
  ]
  ,
  exports:[
    GestorGridComponent,
    GestorCadastroComponent,
    GestorCadastroComponent]
})
export class GestorModule { }
