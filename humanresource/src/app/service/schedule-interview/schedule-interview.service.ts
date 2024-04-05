import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Agreementlist } from 'src/app/model/agreement-list/agreementlist';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';


@Injectable({
  providedIn: 'root'
})
export class ScheduleInterviewService {

  //url 
  baseUrl = this.gc.baseUrl;
 
  constructor(private httpClient:HttpClient, private gc:GlobalBaseUrl) {  }


  //get all the list of Inerview schedule details
  //update on 06 Feb 2024
  getScheduleDetailsList(): Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details`);
  }

 
   //get all Interview Schedule details
   getInterviewSchedule(): Observable<ScheduledInterview[]>{
    return this.httpClient.get<ScheduledInterview[]>(`${this.baseUrl}/schedule-details-candidate`);
  }

   

   //get all  Rejected Candidate
   getTotalRejectedCandidate(): Observable<ScheduledInterview[]>{
    return this.httpClient.get<ScheduledInterview[]>(`${this.baseUrl}/schedule-details-rejected`);
  }

  //get all  Hired Candidate
  getTotalHiredCandidate(): Observable<ScheduledInterview[]>{
   return this.httpClient.get<ScheduledInterview[]>(`${this.baseUrl}/schedule-details-hired`);
  }

 
  //get all  Hired Candidate
  getInterviewerName(profileId:any): Observable<[]>{
    return this.httpClient.get<[]>(`${this.baseUrl}/schedule-details-Interviewer?profileId=${profileId}`);
  }

    

  //get all  Available VideoLinks
  getAvailableVideolinks(roundDate:any): Observable<[]>{
    return this.httpClient.get<[]>(`${this.baseUrl}/schedule-details-videolinks?roundDate=${roundDate}`);
  }
  //create the Inerview schedule details 
  createScheduledetails(fm:FormData, roundStatus:string, roundDetails:number): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/schedule-details/${roundStatus}/${roundDetails}`, fm).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error Creating Scheduled Interview:', error.error);
        return throwError(error);
      })
    );
  }

  //fetch the Inerview schedule through Id
  getScheduleDeatilById(scheduledInterviewId: number): Observable<ScheduledInterview>{
    return this.httpClient.get<ScheduledInterview>(`${this.baseUrl}/schedule-details/${scheduledInterviewId}`);
  }

  //edit/update Inerview schedule details
  updateScheduleDeatil(scheduledInterviewId: number, roundDetails:number, roundStatus:string, fm: FormData): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/schedule-details-edits/${scheduledInterviewId}/${roundDetails}/${roundStatus}`, fm);
  }

  //edit/updateInerview schedule status
  updateScheduleStatus(scheduledInterviewId: number, roundDetails:number, roundStatus:string, constructiveFeedback:String, scheduledInterview: ScheduledInterview): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/schedule-details/${scheduledInterviewId}/${roundDetails}/${roundStatus}/${constructiveFeedback}`, scheduledInterview);
  }

  //edit/updateInerview schedule status
  updateNextRoundScheduleInterview(scheduledInterviewId: number, roundStatus:string, fm:FormData): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/schedule-details-update/${scheduledInterviewId}/${roundStatus}`, fm);
  }

   //edit/updateInerview final round interview schedule status
   updateFinalRoundInterview(scheduledInterviewId: number, roundDetails:number, roundStatus:string, constructiveFeedback:String, scheduledInterview: ScheduledInterview): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/schedule-details-final-round/${scheduledInterviewId}/${roundDetails}/${roundStatus}/${constructiveFeedback}`, scheduledInterview);
  }

  //updateInerview on hold candidate interview
  updateOnholdCandidateInterview(scheduledInterviewId: number, roundDetails:number, roundStatus:string, constructiveFeedback:String, scheduledInterview: ScheduledInterview): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/schedule-details/${scheduledInterviewId}/${roundDetails}/${roundStatus}/${constructiveFeedback}`, scheduledInterview);
  }


  //get all list Schedule interview Dto
  getInterviewerScheduleDetailsList(): Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details-interviewer-schedule`);
  }

  //get all list Schedule interview Dto
  getRoundName(scheduledInterviewId:number, profileId:number, roundDetails:number): Observable<String>{
  return this.httpClient.get<String>(`${this.baseUrl}/schedule-details-roundname/${scheduledInterviewId}/${profileId}/${roundDetails}`);
  }
  
 
  //get the HR List
  getHRList(): Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details-hr-list`);
  }

  //get the HR List
  getAgreementList(): Observable<Agreementlist[]>{
    return this.httpClient.get<Agreementlist[]>(`${this.baseUrl}/schedule-details-agreement`);
  }
  
  //edit/updateInerview final round interview schedule status
  updateFeedbackDetails(scheduledInterviewId: number, scheduledInterview: ScheduledInterview): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/schedule-details-update-feedback/${scheduledInterviewId}`, scheduledInterview);
  }

  
  //get all Interview Schedule details
  getIsValidUser(scheduledInterviewId: number): Observable<any>{
    return this.httpClient.get<any>(`${this.baseUrl}/schedule-details-verifyRole/${scheduledInterviewId}`);
  }

  //get User Authorization
  getIsValidUserForUpdateAndReschedule(scheduledInterviewId: number): Observable<any>{
    return this.httpClient.get<any>(`${this.baseUrl}/schedule-details-roleaccess/${scheduledInterviewId}`);
  }

   //get all the list of Inerview schedule details
   getCandiadteInterviewScheduleList(): Observable<ScheduledInterview[]>{
    return this.httpClient.get<ScheduledInterview[]>(`${this.baseUrl}/schedule-details-status`);
  }

   //get all the list of Inerview schedule details
   getStatusChangeIfNotReverted(): Observable<ScheduledInterview[]>{
    return this.httpClient.get<ScheduledInterview[]>(`${this.baseUrl}/schedule-details-update-joining`);
  }
   // Get candidates selected list after some duration when no update received
   getCandidateAfterDuration(): Observable<ScheduledInterview[]> {
    return this.httpClient.get<ScheduledInterview[]>(`${this.baseUrl}/schedule-details-candidate-selected`);
  }

 // Get candidates selected list after some duration when no feedback receives within 5 days
  getCandidateFeedbackAfterDuration(): Observable<ScheduledInterview[]> {
    return this.httpClient.get<ScheduledInterview[]>(`${this.baseUrl}/schedule-details-candidate-feedback`);
  }

  // Get the list created by ,round status and count of candidate based on month
  getHRReport(month:any): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details-hr-report?month=${month}`);
  }

  // get the list of department wise candidate selection based on month and year
  getDepartmentWiseSelection(year:any): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details-department-report?year=${year}`);
  }

  // get the list of
  getFinancialYear(): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details-year`);
  }
  // get the list of Monthly Hr and roundstatus report
  getMonthlyHRAndRoundstatusReport(month:any, createdBy:any, roundStatus:any): Observable<any[]> {
    const params = new HttpParams().set('month', month).set('createdBy', createdBy).set('roundStatus', roundStatus);
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details-hr-roundstatus-report`, { params });
  }

  //get the roundstatus Listmonth
  getRoundStatusList(): Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details-roundstatus-list`);
  }

   //get the HR Profile Id
   getHRProfileId(): Observable<any>{
    return this.httpClient.get<any>(`${this.baseUrl}/schedule-details-hr-profileid`);
  }

  //get the roundstatus Listmonth
  getRoundFeedbackList(candidateUniqueNumber:string): Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details-feedbacklist?candidateUniqueNumber=${candidateUniqueNumber}`);
  }

  //get the roundstatus Listmonth
  getYearBasedHRList(year:any): Observable<any[]>{
    const params = new HttpParams().set('year', year);
    return this.httpClient.get<any[]>(`${this.baseUrl}/schedule-details-yearly-hr-report` , { params });
  }
}
