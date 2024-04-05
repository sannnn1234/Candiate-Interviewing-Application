import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Onboarding } from 'src/app/model/onboarding-model/onboarding';

@Injectable({
  providedIn: 'root'
})
export class OnboardingService {


  baseUrl = this.gc.baseUrl;

 constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) { }


 //get all the list of onboarding details
 getOnboardingList(): Observable<Onboarding[]>{
 return this.httpClient.get<Onboarding[]>(`${this.baseUrl}/onboarding`);
 }

 //get all the list of onboarding details
 getOnboardingCandidateList(): Observable<any[]>{
 return this.httpClient.get<any[]>(`${this.baseUrl}/onboarding-selected`);
 }
 //create the onboarding details 
 createOnboardingdetails(onboarding:Onboarding): Observable<Object>{
 return this.httpClient.post(`${this.baseUrl}/onboarding`, onboarding);
 }

 //fetch the onboarding details through Id
 getOnboardingDetailById(onboardingId: number): Observable<Onboarding>{
 return this.httpClient.get<Onboarding>(`${this.baseUrl}/onboarding/${onboardingId}`);
 }

 //edit/update onboarding details
 updateOnboardingDetail(onboardingId: number, onboarding: Onboarding): Observable<Object>{
 return this.httpClient.put(`${this.baseUrl}/onboarding/${onboardingId}`, onboarding);
 }

}