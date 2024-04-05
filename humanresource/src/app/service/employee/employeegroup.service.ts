import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/model/employee/employee';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';

@Injectable({
  providedIn: 'root'
})
export class EmployeegroupService {
//url 
baseUrl = this.gc.baseUrl;
constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) {  }


//get all the list of employee details
getEmployeeDetailsList(): Observable<Employee[]>{
  return this.httpClient.get<Employee[]>(`${this.baseUrl}/employee-details`);
}
//create the employee details 
createEmployeedetails(employee:Employee): Observable<Object>{
  return this.httpClient.post(`${this.baseUrl}/employee-details`, employee);
}

//fetch the employee details through Id
getEmployeeDeatilById(empId: number): Observable<Employee>{
  return this.httpClient.get<Employee>(`${this.baseUrl}/employee-details/${empId}`);
}

//edit/update vacancy details
updateEmployeeDeatil(empId: number, employee: Employee): Observable<Object>{
  return this.httpClient.put(`${this.baseUrl}/employee-details/${empId}`, employee);
}
//delete the vacancy details
deleteEmployeeDeatil(empId: number): Observable<Object>{
  return this.httpClient.delete(`${this.baseUrl}/employee-details/${empId}`);
}
}
