import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
// import { data } from 'jquery';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';

@Component({
  selector: 'app-matdialogrejection',
  templateUrl: './matdialogrejection.component.html',
  styleUrls: ['./matdialogrejection.component.css']
})
export class MatdialogrejectionComponent implements OnInit {

  scheduledInterview: ScheduledInterview = new ScheduledInterview();
  roundStatus: string;
  scheduledInterviewId:number;
  roundType:string;
  roundDetails:number;
  constructiveFeedback: string;
  candidateFullName:string;
  candidateUniqueNumber:string;
  

  interviewSchedules = new FormGroup({
    constructiveFeedback: new FormControl('', [Validators.required]),
    communicationRating: new FormControl('', [Validators.required,   Validators.pattern('^\\d+(\\.\\d{1,2})?$')]),
    });
  
    get ConstructiveFeedback() {
      return this.interviewSchedules.get("constructiveFeedback") as FormControl;
    }
  
    get CommunicationRating() {
      return this.interviewSchedules.get("communicationRating") as FormControl;
    }
    
  constructor(private scheduleInterviewService: ScheduleInterviewService, private router: Router,private dialog:MatDialog, private snack: MatSnackBar, @Inject(MAT_DIALOG_DATA) data: any) {
  
    this.scheduledInterviewId = data["id"];
    this.roundDetails = data["round"];
    this.candidateFullName=data["name"];
    this.scheduledInterview.candidateUniqueNumber=data["candidateId"];
    this.scheduledInterview.interviewerEmployeeId = data["employeeId"];
    this.scheduledInterview.profileId= data["profile"];
  }

  ngOnInit(): void {

  }

  //rejected candidate round-wise
  rejectedCandidate(scheduledInterviewId: number, roundDetails: number ) {
    if(this.interviewSchedules.valid){
      this.roundStatus = "Rejected";
      this.constructiveFeedback= new String(this.ConstructiveFeedback.value).replace(/\n/g,"");
      // console.log(this.scheduledInterviewId, this.roundDetails,this.roundStatus, this.constructiveFeedback, this.scheduledInterview);
      this.scheduleInterviewService.updateScheduleStatus(this.scheduledInterviewId, this.roundDetails,this.roundStatus, this.constructiveFeedback, this.scheduledInterview).subscribe(data=>{
        this.snack.open("Candidate Rejected", '', {
          duration: 1000,
        });
      },
      error=> console.log(error));
      this.dialog.closeAll();
      location.reload();
    }
  }

}
