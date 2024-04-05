import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { CandidateserviceService } from 'src/app/service/candidateservice.service';
import { DepartmentService } from 'src/app/service/departmentround/department.service';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';
import { MatdialogrejectionComponent } from '../matdialogrejection/matdialogrejection.component';
import { MatdialogselectionComponent } from '../matdialogselection/matdialogselection.component';
import { MatfinalselectionComponent } from '../matdialog-final-selection/matfinalselection/matfinalselection.component';
import { MatdialogfeedbackComponent } from '../mat-dialog-feedback/matdialogfeedback/matdialogfeedback.component';
import { AuthenticationService } from 'src/app/service/login/authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatupadtejoiningdateComponentComponent } from '../mat-joining-date/matupadtejoiningdate-component/matupadtejoiningdate-component.component';
import { saveAs } from 'file-saver';
import * as XLSX from 'xlsx';
import { TooltipPosition } from '@angular/material/tooltip';
import { OnholdCandidateComponent } from '../matdialog-on-hold/onhold-candidate/onhold-candidate.component';
import { EmployeegroupService } from 'src/app/service/employee/employeegroup.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {



  currentrole: any;

  today: number = Date.now();
  scheduled:any[];
  scheduledcount: any;
  scheduledInterview: ScheduledInterview = new ScheduledInterview();
  scheduledInterviewId: number;
  constructiveFeedback: string;
  round1Hr: string;
  roundStatus: string;
  roundId: any;
  isselected = true;
  roundList: Roundmodule[];
  schedule: any;
  schedulereject: any;
  schedulehired: any;
  scheduledetails: any;
  displaymodel = false;
  maticondisplay = false;
  feedbackscheduled: any[];
  candiadteScheduled: ScheduledInterview[];
  empId: any;
  empIds: any;
  fullName: string;
  flag: boolean;
  currentDate: Date = new Date();
  displayedColumns: string[] = ['candidateFullName', 'modeNo', 'interviewerName', 'roundDate', 'roundTime', 'roundType', 'createdBy','candidateCreatedByName', 'action'];
  dataSource = new MatTableDataSource<ScheduledInterview>();
  displayedColumns1: string[] = ['candidateUniqueNumber', 'candidateFullName', 'roundDate', 'days', 'action'];
  dataSource1 = new MatTableDataSource<ScheduledInterview>();

  constructor(private departmentService: DepartmentService, private authenticationService: AuthenticationService, private scheduleInterviewService: ScheduleInterviewService, private router: Router, private candidateserviceService: CandidateserviceService, private empService: EmployeegroupService, private dialog: MatDialog, private snack: MatSnackBar) {
    this.getScheduleInfo();
    this.getTotalCandidate();
    this.getInterviewSchedule();
    this.getTotalRejectedCandiadet();
    this.getTotalHiredCandidate();
    this.getCandidateListAfterDuration();
  }

  //get all the list of schedule information
  private getScheduleInfo() {
    this.scheduleInterviewService.getScheduleDetailsList().subscribe(data => {
      this.scheduled = data;
      this.dataSource = new MatTableDataSource<ScheduledInterview>(this.scheduled);
      this.roundListFetch();
    });
  }

  //get all the list of schedule information
  //Update on 06 Feb 2023
  // private getScheduleInformationInfo() {
  //   this.scheduleInterviewService.getInterviewerScheduleDetailsList().subscribe(d => {
  //     this.scheduleInterviewService.getScheduleDetailsList().subscribe(data => {
  //       this.scheduled = data;
  //       this.dataSource = new MatTableDataSource<ScheduledInterview>(this.scheduled);
  //       this.roundListFetch();
  //       this.scheduled.forEach((e, i, arr) => {
  //         let s = d.filter((ee) => { return e.interviewerEmployeeId == ee.empId })[0];
  //         let emp = d.filter((ee) => { return e.createdBy == ee.empId })[0];
  //         if (s) {
  //           e.interviewerName = s.fullName;
  //         }
  //         if (emp) {
  //           e.createdByHR = emp.fullName;
  //         }
  //       });
  //     });
  //   });
  // }

  async ngOnInit(): Promise<void> {
    // this.getScheduleInformationInfo();
    this.currentrole = this.authenticationService.getUserRole();
    try {
      const data = await this.authenticationService.getCurrentUser().toPromise();
      this.empIds = data;
      if (this.empIds && this.empIds.fullName) {
        this.fullName = this.empIds.fullName;
      }
      if (this.empIds.groupDescription === 'Interviewer Level 1' || this.empIds.groupDescription === 'Interviewer Level 2') {
        this.displaymodel = false;
      } else {
        this.displaymodel = true;
      }
    } catch (error) {
      console.error(error);
    }
  }

  // Get candidate list after duration when no feedback received from HR
  private getCandidateListAfterDuration() {
    this.scheduleInterviewService.getCandidateAfterDuration().subscribe(data => {
      this.schedule = data;
      this.schedule.forEach((c: { roundDate: string | number | Date; }) => {
        const dateWithoutTime = new Date(c.roundDate).toISOString().split("T")[0];
        c.roundDate = dateWithoutTime;
      });
      this.dataSource1 = new MatTableDataSource<ScheduledInterview>(this.schedule);
    });
  }

  // [Rescheudle Interview]
  updateScheduleDetails(scheduledInterviewId: number) {
    this.scheduleInterviewService.getIsValidUserForUpdateAndReschedule(scheduledInterviewId).subscribe(data => {
      this.empId = data;      
      if ((this.empId == this.empIds.empId) || (this.currentrole == 'Admin')) {
        this.router.navigate(['update-schedule-interview', scheduledInterviewId]);

      }
      else {
        this.snack.open("you are not authorized to access this module", 'Ok');
      }
    }, error => {
      console.log("Error Fetching Valid User" + error);
    });
  }

  // [Next Round Schedule Interview]
  updateInterviewdetailsOfCandidate(scheduledInterviewId: number) {
    this.scheduleInterviewService.getIsValidUserForUpdateAndReschedule(scheduledInterviewId).subscribe(data => {
      this.empId = data;
      if ((this.empId == this.empIds.empId) || (this.currentrole == 'Admin')) {
        this.router.navigate(['update-next-round-schedule-interview', scheduledInterviewId]);
      }
      else {
        this.snack.open("you are not authorized to access this module", 'Ok');
      }
    }, error => {
      console.log("Error Fetching Valid User" + error);
    });
  }



  //get the count of Schedule Interview
  private getInterviewSchedule() {
    this.scheduleInterviewService.getInterviewSchedule().subscribe(data => {
      this.scheduledcount = data;

    });
  }

  //get the count of candidate created
  private getTotalCandidate() {
    this.candidateserviceService.getCountTotatCandiadte().subscribe(data => {
      this.schedule = data;

    });
  }

  //get the count of rejected candidate
  private getTotalRejectedCandiadet() {
    this.scheduleInterviewService.getTotalRejectedCandidate().subscribe(data => {
      this.schedulereject = data;

    });
  }



  //get the count of hired candidate
  private getTotalHiredCandidate() {
    this.scheduleInterviewService.getTotalHiredCandidate().subscribe(data => {
      this.schedulehired = data;

    });
  }

  //get the roundlist
  roundListFetch() {
    this.departmentService.getRoundList().subscribe(
      data => {
        this.roundList = data;
        this.scheduled.forEach((e, i, arr) => {
          e.roundType = data.filter((ee, ii, ar) => { return e.roundDetails == ee.roundId })[0].roundName;

        });
      }, error => alert("Error in getting round List"))

  }

  //round-wise selection matdialogselectioncomponent
  //Update 6 FEB Authorizattion
  selectionDailog(scheduledInterviewId: number, candidateUniqueNumber: string, profileId: number, candidateFullName: string, roundDetails: number) {
    this.scheduleInterviewService.getIsValidUser(scheduledInterviewId).subscribe(data => {
      const empId = data;
      this.flag = false;
      for (let index = 0; index < empId.length; index++) {
        if ((empId[index] == this.empIds.empId) || (this.currentrole == 'Admin')) {
          this.scheduleInterviewService.getRoundName(scheduledInterviewId, profileId, roundDetails).subscribe(data => {
            this.roundId = data;
            const dialogRef = this.dialog.open(MatdialogselectionComponent, {
              data: {
                id: scheduledInterviewId,
                round: roundDetails,
                nextround: this.roundId,
                candidateId: candidateUniqueNumber,
                name: candidateFullName,
                profile: profileId
              }
            });
          });
          this.flag = true;
          break;
        }
      }
      if (!this.flag) {
        this.snack.open("you are not authorized to access this module", 'Ok');
      }
    })
  }

  //rejected candidate matdialog
  //Update 6 FEB Authorizattion
  rejectionDialog(scheduledInterviewId: number, candidateUniqueNumber: string, candidateFullName: string, roundDetails: number, interviewerEmployeeId: number, profileId: number) {
    this.scheduleInterviewService.getIsValidUser(scheduledInterviewId).subscribe(data => {
      const empId = data;
      this.flag = false;
      for (let index = 0; index < empId.length; index++) {
        if ((empId[index] == this.empIds.empId) || (this.currentrole == 'Admin')) {
          const dialogRef = this.dialog.open(MatdialogrejectionComponent, {
            data: {
              id: scheduledInterviewId,
              round: roundDetails,
              candidateId: candidateUniqueNumber,
              name: candidateFullName,
              employeeId: interviewerEmployeeId,
              profile: profileId
            }
          });
          this.flag = true;
          break;
        }
      }
      if (!this.flag) {
        this.snack.open("you are not authorized to access this module", 'Ok');
      }
    }, error => {
      console.log("Error Fetching Valid User" + error);
    });
  }


  //final selection matdialog 
  //Update 6 FEB Authorizattion
  finalSelection(scheduledInterviewId: number, candidateUniqueNumber: string, candidateFullName: string, roundDetails: number, roundType: string, interviewerEmployeeId: number, profileId: number) {
    this.scheduleInterviewService.getIsValidUser(scheduledInterviewId).subscribe(data => {
      const empId = data;
      this.flag = false;
      for (let index = 0; index < empId.length; index++) {
        if ((empId[index] == this.empIds.empId) || (this.currentrole == 'Admin')) {
          const dialogRef = this.dialog.open(MatfinalselectionComponent, {
            data: {
              id: scheduledInterviewId,
              round: roundType,
              candidateId: candidateUniqueNumber,
              name: candidateFullName,
              employeeId: interviewerEmployeeId,
              profile: profileId
            }
          });
          this.flag = true;
          break;
        }
      }
      if (!this.flag) {
        this.snack.open("you are not authorized to access this module", 'Ok');
      }
    }, error => {
      console.log("Error Fetching Valid User" + error);
    });
  }

  //onholdCandidate
  //Update 6 FEB Authorizattion
  onholdCandidateInterview(scheduledInterviewId: number, candidateUniqueNumber: string, candidateFullName: string, roundDetails: number, interviewerEmployeeId: number, profileId: number) {
    this.scheduleInterviewService.getIsValidUserForUpdateAndReschedule(scheduledInterviewId).subscribe(data => {
      this.empId = data;
      if ((this.empId == this.empIds.empId) || (this.currentrole == 'Admin')) {
        this.scheduleInterviewService.getRoundName(scheduledInterviewId, profileId, roundDetails).subscribe(data => {
          this.roundId = data;
          const dialogRef = this.dialog.open(OnholdCandidateComponent, {
            data: {
              id: scheduledInterviewId,
              round: roundDetails,
              nextround: this.roundId,
              candidateId: candidateUniqueNumber,
              name: candidateFullName,
              employeeId: interviewerEmployeeId,
              profile: profileId
            }
          })
        });
      }
      else {
        this.snack.open("you are not authorized to access this module", 'Ok');
      }
    }, error => {
      console.log("Error Fetching Valid User" + error);
    });
  }

  // Open candidate dialog for editing joining date 
  editCandidateJoiningDate(scheduledInterviewId: number, candidateUniqueNumber: string, candidateFullName: string, joiningDate: Date) {
    const dialogRef = this.dialog.open(MatupadtejoiningdateComponentComponent, {
      data: {
        id: scheduledInterviewId,
        name: candidateFullName,
        candidateId: candidateUniqueNumber,
        joiningdate: joiningDate
      }

    });

  }

  positionOptions: TooltipPosition[] = ['after', 'before', 'above', 'below', 'left', 'right'];
  position = new FormControl(this.positionOptions[0]);

  //excel file download
  exportToExcel(): void {
    let d = new Date();
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.schedule);


    const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    const excelBlob: Blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8' });

    // Save the file using FileSaver.js
    saveAs(excelBlob, 'UpdateJoiningDate' + d + '.xlsx');
  }
}


