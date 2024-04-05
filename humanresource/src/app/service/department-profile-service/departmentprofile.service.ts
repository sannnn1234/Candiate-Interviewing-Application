import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Departmentprofile } from 'src/app/model/department-profile-model/departmentprofile';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';

@Injectable({
  providedIn: 'root'
})
export class DepartmentprofileService {

  //url
  baseUrl = this.gc.baseUrl;
  constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) { 
 
   }

  //get all the the list of Department and profile Information
  getDepartmentProfileList(): Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseUrl}/department-profile-details`);
  }

  //get all the the list of Department and profile Information
  getDepartmentProfileMapping(departmentId:any): Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseUrl}/department-profile-mapping?departmentId=${departmentId}`);
  }
  

  //create the Department and profile information 
  createDepartmentProfile(departmentprofile:Departmentprofile): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/department-profile-details`, departmentprofile);
  }

  // fetch the Department and profile information through Id
  getDepartmentProfileById(departmentProfileId: number): Observable<any>{
    return this.httpClient.get<any>(`${this.baseUrl}/department-profile-details/${departmentProfileId}`);
  }

  //edit/update the Department and profile information
  updateDepartmentProfileInfo(departmentProfileId: number, departmentprofile: Departmentprofile): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/department-profile-details/${departmentProfileId}`, departmentprofile);
  }

}
