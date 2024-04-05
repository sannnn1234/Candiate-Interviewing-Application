import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { DepartmentService } from 'src/app/service/departmentround/department.service';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';

@Component({
  selector: 'app-onhold-candidate',
  templateUrl: './onhold-candidate.component.html',
  styleUrls: ['./onhold-candidate.component.css']
})
export class OnholdCandidateComponent {

  scheduledInterview: ScheduledInterview = new ScheduledInterview();
  roundStatus: string;
  displayFalse = true;
  roundList: Roundmodule[];
  candidateFullName:string;
  scheduledInterviewId:number;
  roundDetails:number;
  nextRoundId:any;
  nextRound: number;
  nextroundName: string;
  roundType:string;
  constructiveFeedback: string;
  candidateUniqueNumber:string;

  interviewSchedules = new FormGroup({
    constructiveFeedback: new FormControl('', [Validators.required]),
    });
  
    get ConstructiveFeedback() {
      return this.interviewSchedules.get("constructiveFeedback") as FormControl;
    }
 
  constructor(private departmentService: DepartmentService,private scheduleInterviewService: ScheduleInterviewService, private router: Router,private dialog:MatDialog ,private snack: MatSnackBar, @Inject(MAT_DIALOG_DATA) data: any){

    this.scheduledInterviewId = data["id"];
    this.roundDetails = data["round"];
    this.nextRoundId = data["nextround"];
    this.scheduledInterview.candidateFullName=data["name"];
    this.scheduledInterview.candidateUniqueNumber=data["candidateId"];
    this.scheduledInterview.interviewerEmployeeId = data["employeeId"];
    this.scheduledInterview.profileId= data["profile"];
  
  }
  ngOnInit(): void {
    this.roundListFetch();
  }

  //get the roundlist
  roundListFetch() {
    this.departmentService.getRoundList().subscribe(
      data => {
        this.roundList = data;
        this.roundType = data.filter((ee, ii, ar) => { return this.roundDetails == ee.roundId })[0].roundName;
        if (this.nextRoundId) {
          this.nextroundName = data.filter((ee, ii, ar) => { return this.nextRoundId == ee.roundId })[0].roundName;
        }
        else {
          this.displayFalse = false;
        }
      }, error => alert("Error in getting round List"))

  }
  //On-hold Candidate Interview 
  OnHoldCandidateInterview(scheduledInterviewId: number, roundDetails: number, candidateUniqueNumber:string) {
    if(this.interviewSchedules.valid){
      this.roundStatus = "On-hold";
      this.constructiveFeedback= new String(this.ConstructiveFeedback.value).replace(/\n/g,"");
      // console.log(this.scheduledInterviewId, this.roundDetails,this.roundStatus, this.scheduledInterview);
      this.scheduleInterviewService.updateOnholdCandidateInterview(this.scheduledInterviewId, this.roundDetails, this.roundStatus, this.constructiveFeedback, this.scheduledInterview).subscribe(data => {
        this.snack.open("Candidate On-Hold", '', {
          duration: 1000,
        });
      },
      error => console.log(error));
      this.dialog.closeAll();
      location.reload();
    }
   }
    

}
