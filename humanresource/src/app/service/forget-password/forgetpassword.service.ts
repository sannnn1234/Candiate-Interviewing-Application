import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';



@Injectable({
  providedIn: 'root'
})
export class ForgetpasswordService {
  
   //url
   baseUrl= this.gc.baseUrl;
  constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) { 
  }

  //forget password send otp to email
  forgetPass(fd:FormData): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/forget-password`, fd);
  }  

}
