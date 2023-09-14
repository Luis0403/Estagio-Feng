import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-mensagem-erro',
  template: `
    <small *ngIf = "temErro()" class="p-error">
      {{text}}
    </small>
  `,
  styles: [
  ]
})
export class MensagemErroComponent {
  @Input() error: string = "";
  @Input() control!: FormControl;
  @Input() text: string = "";

  temErro():boolean{
    return this.control.hasError(this.error) && this.control.dirty
  }

}
