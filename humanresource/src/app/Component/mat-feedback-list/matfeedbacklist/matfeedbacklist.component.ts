import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Candidate } from 'src/app/model/candidate';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';



@Component({
  selector: 'app-matfeedbacklist',
  templateUrl: './matfeedbacklist.component.html',
  styleUrls: ['./matfeedbacklist.component.css']
})
export class MatfeedbacklistComponent {

  candidate: Candidate = new Candidate();
  scheduledFeedback: any[] = [];
  scheduledInterviewId: number;
  candidateFullName: string;
  candidateUniqueNumber: string;
 



  constructor(private scheduledInterviewService:ScheduleInterviewService,private router: Router, private snack: MatSnackBar, @Inject(MAT_DIALOG_DATA) data: any) {
    this.scheduledInterviewId = data["id"];
    this.candidateFullName = data["name"];
    this.candidateUniqueNumber = data["candidateId"];
    this.scheduledInterviewService.getRoundFeedbackList(this.candidateUniqueNumber).subscribe(data=>{
    this.scheduledFeedback = data;   
     })
  }





}
