import { Injectable, NgModule } from '@angular/core';
import {  HttpEvent,  HttpInterceptor,  HttpHandler,  HttpRequest, HTTP_INTERCEPTORS, HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServicosService } from './servicos.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {
   

   constructor(private jwt: JwtHelperService, private httpClient:HttpClient ){}

   intercept(req: HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>> {
    
     let idToken= localStorage.getItem("token");
      
         if (idToken) {
            
            let cloned:any;

            // if(this.jwt.isTokenExpired(idToken)){
            //    this.httpClient.get('http://localhost:8080/biblioteca/refreshToken').subscribe(
            //    resposta=>{
            //                console.log(resposta);
            //                const id_Token= Object.values(resposta)[0];
                           
            //                localStorage.setItem("token",id_Token);
            //                console.error(id_Token)
            //                idToken=id_Token;
            //             },
            //    erro=>console.error("ha erro...")
            // );
            // }
            
               console.error("ha sem utilizar o tokrnerro...")
               cloned =req.clone({
              
                 headers: req.headers.set("Authorization",
                    "Bearer "+idToken)
              });
            
           
            //this.tokenExpired(req,idToken);

            return next.handle(cloned);
         }
                       
         else{
            return next.handle(req);
         }

         

        
   }


   tokenExpired(req: HttpRequest<any>, token: string):any{
      
      if(!this.jwt.isTokenExpired(token)){
         return  req.clone({
            
            headers: req.headers.set("Authorization",
               "Bearer "+token)
         });

      }

         if(this.jwt.isTokenExpired(token)){
            this.httpClient.get('http://localhost:8080/biblioteca/refreshToken').subscribe(
               resposta=>{
                           console.log(resposta);
                           const idToken= Object.values(resposta)[0];
                           
                           localStorage.setItem("token",idToken);

                           return  req.clone({
               
                              headers: req.headers.set("Authorization",
                                 "Bearer "+idToken)
                           });
                        },
               erro=>console.log(erro)
            );
         }

      
      
      //Object.values(resposta)[0]
   }
}
@NgModule({
   providers: [{
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true,
   }]
})
export class Interceptor { }