import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';

@Injectable({
  providedIn: 'root'
})
export class ChangepasswordService {
  //url
  baseUrl= this.gc.baseUrl;
  constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) { }

  //change password
  changePassword(fd:FormData): Observable<Object>{

    return this.httpClient.post(`${this.baseUrl}/change-password`, fd);
  }

  
}
