import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-professor-grid',
  templateUrl: './professor-grid.component.html',
  styleUrls: ['./professor-grid.component.css']
})
export class ProfessorGridComponent {
  @Input() professores: any[]=[];
}