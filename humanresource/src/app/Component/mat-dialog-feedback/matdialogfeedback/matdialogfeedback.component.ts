import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';

@Component({
  selector: 'app-matdialogfeedback',
  templateUrl: './matdialogfeedback.component.html',
  styleUrls: ['./matdialogfeedback.component.css']
})
export class MatdialogfeedbackComponent {


  scheduledInterview: ScheduledInterview = new ScheduledInterview();
  roundStatus: string;
  scheduledInterviewId:number;
  roundType:string;
  roundDetails:number;
  constructiveFeedback: string;
  candidateFullName:string;
  candidateUniqueNumber:string;
  

  interviewSchedules = new FormGroup({
    hrFeedback: new FormControl('', [Validators.required]),

  });
  get HRFeedback() {
    return this.interviewSchedules.get("hrFeedback") as FormControl;
  }

  
  constructor(private scheduleInterviewService: ScheduleInterviewService, private router: Router,private dialog:MatDialog, private snack: MatSnackBar, @Inject(MAT_DIALOG_DATA) data: any) {
  
    this.scheduledInterviewId = data["id"];
    this.candidateUniqueNumber=data["candidateId"];
    this.candidateFullName=data["name"];
    this.scheduledInterview.interviewerEmployeeId = data["employeeId"];
  }

  ngOnInit(): void {

  }

  //rejected candidate round-wise
  feedbackUpdate(scheduledInterviewId: number) {
     this.scheduledInterview.hrFeedback= new String(this.HRFeedback.value).replace(/\n/g,"");
      this.scheduleInterviewService.updateFeedbackDetails(this.scheduledInterviewId,  this.scheduledInterview).subscribe(data=>{
       this.dialog.closeAll();
       location.reload();
     },
     error=> console.log(error));
  }
}
