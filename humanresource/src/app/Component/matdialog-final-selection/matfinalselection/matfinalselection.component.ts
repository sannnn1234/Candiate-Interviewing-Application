import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Agreementlist } from 'src/app/model/agreement-list/agreementlist';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { AgreementService } from 'src/app/service/agreement-service/agreement.service';
import { DepartmentService } from 'src/app/service/departmentround/department.service';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';

@Component({
  selector: 'app-matfinalselection',
  templateUrl: './matfinalselection.component.html',
  styleUrls: ['./matfinalselection.component.css']
})
export class MatfinalselectionComponent {
  scheduledInterview: ScheduledInterview = new ScheduledInterview();
  roundStatus: string;
  roundList: Roundmodule[];
  candidateFullName:string;
  scheduledInterviewId:number;
  roundDetails:number;
  roundType:string;
  constructiveFeedback: string;
  candidateUniqueNumber:string;
  agreementList:Agreementlist[];

  interviewSchedules = new FormGroup({
    constructiveFeedback: new FormControl('', [Validators.required]),
    communicationRating: new FormControl('', [Validators.required,  Validators.pattern('^\\d+(\\.\\d{1,2})?$')]),
    readyToRelocate: new FormControl('', [Validators.required]),
    agreement: new FormControl('', [Validators.required]),
    profileRelevance: new FormControl('', [Validators.required, Validators.pattern('^\\d+(\\.\\d{1,2})?$')]),
    });
  
    get ConstructiveFeedback() {
      return this.interviewSchedules.get("constructiveFeedback") as FormControl;
    }
  
    get CommunicationRating() {
      return this.interviewSchedules.get("communicationRating") as FormControl;
    }
  
    get ReadyToRelocate() {
      return this.interviewSchedules.get("readyToRelocate") as FormControl;
    }
  
    get Agreement() {
      return this.interviewSchedules.get("agreement") as FormControl;
    }
  
    get ProfileRelevance() {
      return this.interviewSchedules.get("profileRelevance") as FormControl;
    }
  constructor(private departmentService: DepartmentService,private scheduleInterviewService: ScheduleInterviewService, private agreementService: AgreementService, private router: Router,private dialog:MatDialog ,private snack: MatSnackBar, @Inject(MAT_DIALOG_DATA) data: any){

    this.scheduledInterviewId = data["id"];
    this.roundDetails = data["round"];
    this.candidateFullName=data["name"];
    this.candidateUniqueNumber=data["candidateId"];
    this.scheduledInterview.interviewerEmployeeId = data["employeeId"];
    this.scheduledInterview.profileId= data["profile"];
  
  }
  ngOnInit(): void {
    this.roundListFetch();
    this.getAgreentList();
  }

  //get the roundlist
  roundListFetch() {
    this.departmentService.getRoundList().subscribe(
      data => {
      this.roundList = data;
        this.roundType = data.filter((ee,ii,ar)=>{ return this.roundDetails == ee.roundId })[0].roundName;
      }, error => alert("Error in getting round List"))
  
  }

  getAgreentList(){
    this.agreementService.getAgreemnetBondList().subscribe(data=>{
      this.agreementList=data;
    })
  }

  //[Final Selected Candidate]
  selectedCandidate(scheduledInterviewId: number, roundDetails: number) {
    if(this.interviewSchedules.controls['communicationRating'].valid && this.interviewSchedules.controls['constructiveFeedback'].valid && this.interviewSchedules.controls['profileRelevance'].valid ){
    this.roundStatus = "Selected";
    this.constructiveFeedback= new String(this.ConstructiveFeedback.value).replace(/\n/g,"");
    this.scheduleInterviewService.updateFinalRoundInterview(this.scheduledInterviewId, this.roundDetails, this.roundStatus, this.constructiveFeedback, this.scheduledInterview).subscribe(data => {
    this.snack.open("Candidate Selected", '', {
      duration: 1000,
    });
    },
    error => console.log(error));
    this.dialog.closeAll();
    location.reload();
   }
  }
  
}
