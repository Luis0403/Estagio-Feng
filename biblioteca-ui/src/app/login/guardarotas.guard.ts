import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ServicosService } from './servicos.service';

@Injectable({
  providedIn: 'root'
})
export class GuardarotasGuard implements CanActivate {

  constructor(private logInSer:ServicosService, private rotear: Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
      if(route.data['roles'] && this.temPernissao(route.data['roles'])){}
    return true;


  }

  temPernissao(arg0: []) :boolean{
    
    for(let roles of arg0){
        if(this.logInSer.paylaodToken.roles[0]!=roles)
        console.log("Nao autorizado com a regra: ",roles)
        //this.rotear.navigate(['/nao-autorizado']);
        return false
    }
    return true;
  }
  
}
