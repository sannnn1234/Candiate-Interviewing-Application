import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Profiledetails } from 'src/app/model/profile-details/profiledetails';

@Injectable({
  providedIn: 'root'
})
export class ProfileServiceService {


  baseUrl = this.gc.baseUrl;

  constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) { }
  
    //get all the list of Profile details
    getProfileDetailsList(): Observable<Profiledetails[]>{
      return this.httpClient.get<Profiledetails[]>(`${this.baseUrl}/profile`);
    }

    //get all the list of Profile details
    getProfileList(): Observable<Profiledetails[]>{
      return this.httpClient.get<Profiledetails[]>(`${this.baseUrl}/profile-list`);
    }
    //create the Profile details 
    createProfiledetails(profile:Profiledetails): Observable<Object>{
      return this.httpClient.post(`${this.baseUrl}/profile`, profile);
    }

    //fetch the Profile details through Id
    getProfileDeatilById(profileID: number): Observable<Profiledetails>{
      return this.httpClient.get<Profiledetails>(`${this.baseUrl}/profile/${profileID}`);
    }

    //edit/update Profile details
    updateProfileDeatil(profileID: number, profile: Profiledetails): Observable<Object>{
      return this.httpClient.put(`${this.baseUrl}/profile/${profileID}`, profile);
    }

}
