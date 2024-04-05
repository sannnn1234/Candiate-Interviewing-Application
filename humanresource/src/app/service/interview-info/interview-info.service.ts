import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Interview } from 'src/app/model/interview-info/interview';

@Injectable({
  providedIn: 'root'
})
export class InterviewInfoService {

  //url
  baseUrl=this.gc.baseUrl;
  
  constructor(private httpClient: HttpClient, private gc:GlobalBaseUrl) { }
  //get all the list of Mode Information
  getInterviewInformationList(): Observable<Interview[]>{
    return this.httpClient.get<Interview[]>(`${this.baseUrl}/interview`);
  }
  //create the Mode Information details
  interviewInfo(interview:Interview):Observable<Object>{
    console.log(interview);
    return this.httpClient.post(`${this.baseUrl}/interview`,interview);
  }

  //fetch the Mode Information through Id
  getInterviewInformationById(interviewId: number): Observable<Interview>{
    return this.httpClient.get<Interview>(`${this.baseUrl}/interview/${interviewId}`);
  }

  //edit/update the details of Mode Information
  updateInterviewInformation(interviewId: number, Interview: Interview): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/interview/${interviewId}`, Interview);
  }
  //delete mode Information
  deleteInterviewInformation(interviewId: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/interview/${interviewId}`);
  }
  
 
  
}
