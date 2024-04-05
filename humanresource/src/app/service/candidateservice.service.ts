import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Candidate } from '../model/candidate';
import { Observable, catchError, throwError } from 'rxjs';
import { ScheduledInterview } from '../model/sheduledinterview/scheduled-interview';
import { GlobalBaseUrl } from '../model/global-baseUrl/global-base-url';

@Injectable({
  providedIn: 'root'
})
export class CandidateserviceService {

  baseUrl = this.gc.baseUrl;
  employeeRole:Candidate[];
  constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) {  }
  

  //get all the list of candidate Information
  getCandidateDetailList(): Observable<Candidate[]>{
    return this.httpClient.get<Candidate[]>(`${this.baseUrl}/candidate-information`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error fetching candidate information:', error.error);
        return throwError(error);
      })
    );
  }
  
 
  // Get all the list of candidate list by status
  getCandidateListByStatus(): Observable<Candidate[]>{
    return this.httpClient.get<Candidate[]>(`${this.baseUrl}/candidate-information-candidate-status`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error fetching candidate list by status:', error.error);
        return throwError(error);
      })
    );
  }

  // Get all the list of scheduled interviews
  getScheduleList(): Observable<ScheduledInterview[]>{
    return this.httpClient.get<ScheduledInterview[]>(`${this.baseUrl}/candidate-information-schedule`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error fetching scheduled interviews:', error.error);
        return throwError(error);
      })
    );
  }
  
  //get all the list of location
  getListOfLocation(): Observable<String>{
    return this.httpClient.get<String>(`${this.baseUrl}/candidate-information-location`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error fetching list of locations:', error.error);
        return throwError(error);
      })
    );
  }


  // Get the count of total candidates
  getCountTotatCandiadte(): Observable<Candidate[]>{
    return this.httpClient.get<Candidate[]>(`${this.baseUrl}/candidate-information-candidate`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error fetching total candidate count:', error.error);
        return throwError(error);
      })
    );
  }
  //Create the details of candidate information
  save(fd:FormData): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/candidate-information`, fd).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error saving candidate information:', error.error);
        return throwError(error);
      })
    );
  }
 
 
  //Fetch the record of candiadte information through Id
  getCandidateDeatilById(candidateNo: number): Observable<Candidate>{
    return this.httpClient.get<Candidate>(`${this.baseUrl}/candidate-information/${candidateNo}`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error fetching candidate information by ID:', error.error);
        return throwError(error);
      })
    );
  }

  //Edit/update the details of candidate information
  updateCandidateDeatil(candidateNo: number, fm: FormData): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/candidate-information/${candidateNo}`, fm).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error updating candidate information:', error.error);
        return throwError(error);
      })
    );
  }
 
    // Update the joining date of a candidate
  updateJoiningDate(candidateUniqueNumber: string, candidate: Candidate): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/candidate-information-joiningdate/${candidateUniqueNumber}`, candidate).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error updating joining date of a candidate:', error.error);
        return throwError(error);
      })
    );
  }

  // Delete the candidate information record
  deleteCandidateDeatil(candidateNo: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/candidate-information/${candidateNo}`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error deleting candidate information:', error.error);
        return throwError(error);
      })
    );
  }
  
}









  
 

