import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';


@Injectable({
  providedIn: 'root'
})
export class DepartmentDetailsService {

  baseUrl = this.gc.baseUrl;

  constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) { }


  //get all the list of Department details
getDepartmentDetailsList(): Observable<Departmentdetails[]>{
  return this.httpClient.get<Departmentdetails[]>(`${this.baseUrl}/department-details`);
}

  //get all the list of Department details
  getDepartmentList(): Observable<Departmentdetails[]>{
    return this.httpClient.get<Departmentdetails[]>(`${this.baseUrl}/department-list`);
  }

//create the Department details 
createDepartmentdetails(department:Departmentdetails): Observable<Object>{
  return this.httpClient.post(`${this.baseUrl}/department-details`, department);
}

//fetch the Department details through Id
getDepartmentDeatilById(departmentId: number): Observable<Departmentdetails>{
  return this.httpClient.get<Departmentdetails>(`${this.baseUrl}/department-details/${departmentId}`);
}

//edit/update Department details
updateDepartmentDeatil(departmentId: number, department: Departmentdetails): Observable<Object>{
  return this.httpClient.put(`${this.baseUrl}/department-details/${departmentId}`, department);
}

}
