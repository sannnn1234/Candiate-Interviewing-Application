import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Groupmaster } from 'src/app/model/groupmaster/groupmaster';


@Injectable({
  providedIn: 'root'
})
export class GroupmasterService {
//url 
baseUrl = this.gc.baseUrl;
constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) {  }


//get all the list of group master details
getGroupmasterDetailsList(): Observable<Groupmaster[]>{
  return this.httpClient.get<Groupmaster[]>(`${this.baseUrl}/groupmaster-details`);
}

//get all the list of group master details
getGroupmasterList(): Observable<Groupmaster[]>{
  return this.httpClient.get<Groupmaster[]>(`${this.baseUrl}/groupmaster-list`);
}
//create the group master details 
registerGroupmasterdetails( groupmaster:Groupmaster): Observable<Object>{
  return this.httpClient.post(`${this.baseUrl}/groupmaster-details`, groupmaster);
}

//fetch the group master details through Id
getGroupmasterDeatilById(vacancyId: number): Observable<Groupmaster>{
  return this.httpClient.get<Groupmaster>(`${this.baseUrl}/groupmaster-details/${vacancyId}`);
}

//edit/update group master details
updateGroupmasterDeatil(groupCode: number,  groupmaster:Groupmaster): Observable<Object>{
  return this.httpClient.put(`${this.baseUrl}/groupmaster-details/${groupCode}`, groupmaster);
}
//delete the group master details
deleteGroupmasterDeatil(groupCode: number): Observable<Object>{
  return this.httpClient.delete(`${this.baseUrl}/groupmaster-details/${groupCode}`);
}
}
