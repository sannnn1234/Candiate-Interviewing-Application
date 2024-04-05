import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Agreementlist } from 'src/app/model/agreement-list/agreementlist';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { AgreementService } from 'src/app/service/agreement-service/agreement.service';
import { DepartmentService } from 'src/app/service/departmentround/department.service';
import { AuthenticationService } from 'src/app/service/login/authentication.service';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';

@Component({
  selector: 'app-matdialogselection',
  templateUrl: './matdialogselection.component.html',
  styleUrls: ['./matdialogselection.component.css']
})
export class MatdialogselectionComponent implements OnInit {

  displayFalse = true;
  scheduledInterview: ScheduledInterview = new ScheduledInterview();
  roundStatus: string;
  roundList: Roundmodule[];
  candidateFullName: string;
  scheduledInterviewId: number;
  roundDetails: number;
  roundName: string;
  roundType: string;
  nextRoundId: any;
  nextRound: number;
  nextroundName: string;
  constructiveFeedback: string;
  candidateUniqueNumber: string;
  profileRelevance: number;
  empIds: any;
  agreementList: Agreementlist[];

  interviewSchedules = new FormGroup({
    constructiveFeedback: new FormControl('', [Validators.required]),
    communicationRating: new FormControl('', [Validators.required, Validators.pattern('^\\d+(\\.\\d{1,2})?$')]),
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


  constructor(private departmentService: DepartmentService, private scheduleInterviewService: ScheduleInterviewService, private agreementService: AgreementService, private authenticationService: AuthenticationService, private router: Router, private dialog: MatDialog, private snack: MatSnackBar, @Inject(MAT_DIALOG_DATA) data: any) {
    this.scheduledInterviewId = data["id"];
    this.roundDetails = data["round"];
    this.nextRoundId = data["nextround"];
    this.candidateFullName = data["name"];
    this.scheduledInterview.profileId = data["profile"];
    this.scheduledInterview.candidateUniqueNumber = data["candidateId"];
  }
  ngOnInit(): void {
    this.authenticationService.getCurrentUser().subscribe(data => {
      this.empIds = data;
    })
    this.roundListFetch();
    this.getAgreentList();
  }


  //get the roundlist
  roundListFetch() {
    this.departmentService.getRoundList().subscribe(
      data => {
        this.roundList = data;
        this.roundType = data.filter((ee, ii, ar) => { return this.roundDetails == ee.roundId })[0].roundName;
        // console.log(this.nextRoundId.length);
        if (this.nextRoundId) {
          this.nextroundName = data.filter((ee, ii, ar) => { return this.nextRoundId == ee.roundId })[0].roundName;
        }
        else {
          this.displayFalse = false;
        }
      }, error => alert("Error in getting round List"))

  }

  getAgreentList() {
    this.agreementService.getAgreemnetBondList().subscribe(data => {
      this.agreementList = data;
    })
  }

  //selected candidate round-wise
  selectedCandidate(scheduledInterviewId: number, roundDetails: number, nextroundName: string) {
    if (this.roundType === 'HR') {
      if (this.interviewSchedules.controls['communicationRating'].valid && this.interviewSchedules.controls['readyToRelocate'].valid && this.interviewSchedules.controls['agreement'].valid && this.interviewSchedules.controls['constructiveFeedback'].valid) {
        if (nextroundName) {
          this.roundStatus = "Shortlisted"
        }
        else {
          this.roundStatus = "Selected";
        }
        this.constructiveFeedback = new String(this.ConstructiveFeedback.value).replace(/\n/g, "");
        this.scheduleInterviewService.updateScheduleStatus(this.scheduledInterviewId, this.roundDetails, this.roundStatus, this.constructiveFeedback, this.scheduledInterview).subscribe(data => {
          if (this.empIds.groupDescription === 'HR' || this.empIds.groupDescription === 'Admin') {
            if (this.nextroundName != undefined) {
              this.router.navigate(['update-next-round-schedule-interview', data]);
              this.dialog.closeAll();
            } else {
              location.reload();
            }
          } else {
            location.reload();
          }

        },
          error => console.log(error));
        this.dialog.closeAll();
      }
    }else{
      if (this.interviewSchedules.controls['communicationRating'].valid && this.interviewSchedules.controls['profileRelevance'].valid && this.interviewSchedules.controls['constructiveFeedback'].valid) {
        if (nextroundName) {
          this.roundStatus = "Shortlisted"
        }
        else {
          this.roundStatus = "Selected";
        }
        this.constructiveFeedback = new String(this.ConstructiveFeedback.value).replace(/\n/g, "");
        this.scheduleInterviewService.updateScheduleStatus(this.scheduledInterviewId, this.roundDetails, this.roundStatus, this.constructiveFeedback, this.scheduledInterview).subscribe(data => {
          if (this.empIds.groupDescription === 'HR' || this.empIds.groupDescription === 'Admin') {
            if (this.nextroundName != undefined) {
              this.router.navigate(['update-next-round-schedule-interview', data]);
              this.dialog.closeAll();
            } else {
              location.reload();
            }
          } else {
            location.reload();
          }

        },
          error => console.log(error));
        this.dialog.closeAll();
      }
    }
  }
}
