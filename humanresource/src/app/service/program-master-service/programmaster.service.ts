import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Groupmaster } from 'src/app/model/groupmaster/groupmaster';
import { Program } from 'src/app/model/program-model/program';

@Injectable({
  providedIn: 'root'
})
export class ProgrammasterService {

  baseUrl= this.gc.baseUrl;
  constructor(private httpClient: HttpClient, private gc:GlobalBaseUrl) { }

  getList(): Observable<Program[]> {
    return this.httpClient.get<[]>(`${this.baseUrl}/program-list`);
  }

  getMapList(gc:Groupmaster): Observable<Program[]> {
    return this.httpClient.post<[]>(`${this.baseUrl}/program-map-list`, gc);
  }

  saveProgramGroupMapList(p: Program[] , groupCode:Number): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/program-group-link-edit?groupCode=${groupCode}`, p);
  }

}
