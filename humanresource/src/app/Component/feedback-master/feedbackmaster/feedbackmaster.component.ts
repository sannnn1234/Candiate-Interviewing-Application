import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { CandidateserviceService } from 'src/app/service/candidateservice.service';
import { DepartmentService } from 'src/app/service/departmentround/department.service';
import { AuthenticationService } from 'src/app/service/login/authentication.service';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';
import { MatdialogfeedbackComponent } from '../../mat-dialog-feedback/matdialogfeedback/matdialogfeedback.component';
import { MatfinalselectionComponent } from '../../matdialog-final-selection/matfinalselection/matfinalselection.component';
import { MatdialogrejectionComponent } from '../../matdialogrejection/matdialogrejection.component';
import { MatdialogselectionComponent } from '../../matdialogselection/matdialogselection.component';
import { saveAs } from 'file-saver';
import * as XLSX from 'xlsx';
import { TooltipPosition } from '@angular/material/tooltip';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-feedbackmaster',
  templateUrl: './feedbackmaster.component.html',
  styleUrls: ['./feedbackmaster.component.css']
})
export class FeedbackmasterComponent {

 
  scheduled!: ScheduledInterview[];   
  feedbackscheduled!: any[];
  feeback !: any;
  scheduledInterview: ScheduledInterview = new ScheduledInterview();

  displayedColumns: string[] = ['scheduledInterviewId', 'candidateUniqueNumber', 'candidateFullName', 'interviewerName','roundDate', 'roundType','action'];
  dataSource =new MatTableDataSource<ScheduledInterview>();
  roundId!: String;

  constructor(private departmentService: DepartmentService, private authenticationService:AuthenticationService, private scheduleInterviewService: ScheduleInterviewService, private router: Router, private candidateserviceService: CandidateserviceService, private dialog: MatDialog,private snack:MatSnackBar) {
  //this.getfeedbackList();
  this.getCandidateFeedbackList();
 
  
  }
  


 // feedback
  feedbackData(scheduledInterviewId:number, candidateUniqueNumber:string, candidateFullName:string) {
    const dialogRef = this.dialog.open(MatdialogfeedbackComponent, {
      data: {
        id:scheduledInterviewId,
        candidateId: candidateUniqueNumber,
        name: candidateFullName,
      }
    });
  }

  //get all the feedback list 
  private getCandidateFeedbackList(){
    this.scheduleInterviewService.getCandidateFeedbackAfterDuration().subscribe(data=>{
    this.feeback=data;
      this.dataSource=new MatTableDataSource<ScheduledInterview>(this.feeback);
    });
  }


   //round-wise selection matdialogselectioncomponent
   selectionDailog(scheduledInterviewId: number, candidateUniqueNumber: string, profileId:number, candidateFullName: string, roundDetails:number) {
    this.scheduleInterviewService.getRoundName(scheduledInterviewId, profileId, roundDetails).subscribe(data=>{
      this.roundId =data;
          
      const dialogRef = this.dialog.open(MatdialogselectionComponent, {
        data: {
        id: scheduledInterviewId,
        round:roundDetails,
        nextround:this.roundId,
        candidateId: candidateUniqueNumber,
        name: candidateFullName

      }
    });
  });
  }


  //rejected candidate matdialog
  rejectionDialog(scheduledInterviewId: number, candidateUniqueNumber: string, candidateFullName: string, roundDetails:number, interviewerEmployeeId:number, profileId:number) {
    const dialogRef = this.dialog.open(MatdialogrejectionComponent, {

      data: {
        id: scheduledInterviewId,
        round: roundDetails,
        candidateId: candidateUniqueNumber,
        name: candidateFullName,
        employeeId:interviewerEmployeeId,
        profile:profileId
      }


    });
  }

  //final selection matdialog 
  finalSelection(scheduledInterviewId: number, candidateUniqueNumber: string, candidateFullName: string, roundDetails:number, roundType: string, interviewerEmployeeId:number, profileId:number) {
    const dialogRef = this.dialog.open(MatfinalselectionComponent, {
      data: {
        id: scheduledInterviewId,
        round: roundType,
        candidateId: candidateUniqueNumber,
        name: candidateFullName,
        employeeId:interviewerEmployeeId,
        profile:profileId
      }
    });
  }

  positionOptions: TooltipPosition[] = ['after', 'before', 'above', 'below', 'left', 'right'];
  position = new FormControl(this.positionOptions[0]);  
//excel file download
exportToExcel(): void {
  let d = new Date();
  const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.feeback);
  const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
  const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
  const excelBlob: Blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8' });
  // Save the file using FileSaver.js
  saveAs(excelBlob, 'Feedback'+d+'.xlsx');
   }

}
