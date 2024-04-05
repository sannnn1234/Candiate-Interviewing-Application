import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from 'src/app/model/departmentround/department';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  //url
  baseUrl = this.gc.baseUrl;
  constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) { 
 
   }

  //get all the the list of Department round Information
  getDepartmentRoundInfoList(): Observable<Department[]>{
    return this.httpClient.get<Department[]>(`${this.baseUrl}/department`);
  }

  //get all the the selction criteria list 
  getSelectionCriteriaList(): Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseUrl}/department-selection-list`);
  }

  
  //get all the the list of Department round Information
  getRoundList(): Observable<Roundmodule[]>{
    return this.httpClient.get<Roundmodule[]>(`${this.baseUrl}/department-round`);
  }

  //create the department round information 
  registerdepartment(department:Department): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/department`, department);
  }

  // fetch the department round information through Id
  getDepartmentRoundInfoById(itemNo: number): Observable<Department>{
    return this.httpClient.get<Department>(`${this.baseUrl}/department/${itemNo}`);
  }

  //edit/update the department round information
  updateDepartmentRoundInfo(itemNo: number, department: Department): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/department/${itemNo}`, department);
  }

  //delete department round information
  deleteDepartmentRoundInfo(itemNo: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/department/${itemNo}`);
  }
}
