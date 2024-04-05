import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Agreementlist } from 'src/app/model/agreement-list/agreementlist';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';

@Injectable({
  providedIn: 'root'
})
export class AgreementService {
  //url
  baseUrl=this.gc.baseUrl;
  
  constructor(private httpClient: HttpClient, private gc:GlobalBaseUrl) { }
  //get all the list of Agreement
  getAgreemnetList(): Observable<Agreementlist[]>{
    return this.httpClient.get<Agreementlist[]>(`${this.baseUrl}/agreement`);
  }

   //get all the list of Agreement
   getAgreemnetBondList(): Observable<Agreementlist[]>{
    return this.httpClient.get<Agreementlist[]>(`${this.baseUrl}/agreement-list`);
  }
  //create the Agreement List
  createAgreement(agreement:Agreementlist):Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/agreement`,agreement);
  }

  //fetch the Agreement List through Id
  getAgreementById(agreementId: number): Observable<Agreementlist>{
    return this.httpClient.get<Agreementlist>(`${this.baseUrl}/agreement/${agreementId}`);
  }

  //edit/update the details of Agreement 
  updateAgreementList(agreementId: number, agreement:Agreementlist): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/agreement/${agreementId}`, agreement);
  }
  
}
