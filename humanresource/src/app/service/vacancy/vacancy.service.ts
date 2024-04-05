import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Vacancydetails } from 'src/app/model/vacancydetails/vacancydetails';

@Injectable({
  providedIn: 'root'
})
export class VacancyService {

  //url 
  baseUrl = this.gc.baseUrl;
  constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) {  }


  //get all the list of vacancy details
  getVacancyDetailsList(): Observable<Vacancydetails[]>{
    return this.httpClient.get<Vacancydetails[]>(`${this.baseUrl}/vacancy-details`);
  }
  //create the vacancy details 
  registerVacancydetails(vacancydetails:Vacancydetails): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/vacancy-details`, vacancydetails);
  }

  //fetch the vacancy details through Id
  getVacancyDeatilById(vacancyId: number): Observable<Vacancydetails>{
    return this.httpClient.get<Vacancydetails>(`${this.baseUrl}/vacancy-details/${vacancyId}`);
  }

  //edit/update vacancy details
  updateVacancyDeatil(vacancyId: number, vacancy: Vacancydetails): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/vacancy-details/${vacancyId}`, vacancy);
  }
  //delete the vacancy details
  deleteVacancyDeatil(vacancyId: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/vacancy-details/${vacancyId}`);
  }
}
