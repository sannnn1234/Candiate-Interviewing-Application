
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Candidate } from 'src/app/model/candidate';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { CandidateserviceService } from 'src/app/service/candidateservice.service';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';
import { MatDialog } from '@angular/material/dialog';
import { MatupadtejoiningdateComponentComponent } from '../../mat-joining-date/matupadtejoiningdate-component/matupadtejoiningdate-component.component';
import { AuthenticationService } from 'src/app/service/login/authentication.service';
import { HttpClient} from '@angular/common/http';
import { MatfeedbacklistComponent } from '../../mat-feedback-list/matfeedbacklist/matfeedbacklist.component';
import { MatSort } from '@angular/material/sort';




@Component({
  selector: 'app-candidatemaster',
  templateUrl: './candidatemaster.component.html',
  styleUrls: ['./candidatemaster.component.css']
})
export class CandidatemasterComponent implements OnInit {

  ScheduledInterview: ScheduledInterview[]
  candidate: Candidate[];
  candidates: Candidate = new Candidate();
  selectRow: any;
  scheduled: any;
  empIds: any;
  displaymodel = false;
  displayedColumns: string[] = ['candidateUniqueNumber', 'candidateFullName', 'dateOfBirth', 'contactNumber', 'profilePreference1', 'profilePreference2', 'emailAddress', 'totalExperience', 'relevantExperience','currentSalary', 'expectedSalary', 'KeySkills', 'currentLocation', 'locationPreference','noticePeriod',   'readyToNegotiate','roundStatus', 'joiningDate', 'candidateResume', 'feedback', 'action'];
  dataSource = new MatTableDataSource<Candidate>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private candidateService: CandidateserviceService, private scheduleInterviewService: ScheduleInterviewService, private authenticationService: AuthenticationService, private router: Router, private http: HttpClient, private dialog: MatDialog) {
    this.getCandidateDetail();
    this.getScheduleInfo();
  }

  ngOnInit() {
    setTimeout(() => this.dataSource.paginator = this.paginator);
    this.authenticationService.getCurrentUser().subscribe(data => {
      this.empIds = data;
      if (this.empIds.groupDescription === 'Interviewer Level 1' || this.empIds.groupDescription === 'Interviewer Level 2') {
        this.displaymodel = false;
      } else {
        this.displaymodel = true;
      }
      if(this.empIds.groupDescription == 'Interviewer Level 1'){
       this.displayedColumns = ['candidateUniqueNumber', 'candidateFullName', 'contactNumber', 'profilePreference1', 'profilePreference2', 'emailAddress', 'totalExperience', 'relevantExperience',  'KeySkills', 'currentLocation', 'locationPreference', 'noticePeriod',  'readyToNegotiate', 'roundStatus', 'joiningDate', 'candidateResume','feedback'];
      }

      if(this.empIds.groupDescription == 'Interviewer Level 2'){
        this.displayedColumns = ['candidateUniqueNumber', 'candidateFullName',  'contactNumber', 'profilePreference1', 'profilePreference2', 'emailAddress', 'totalExperience', 'relevantExperience',  'currentSalary', 'expectedSalary', 'KeySkills', 'currentLocation', 'locationPreference', 'noticePeriod',  'readyToNegotiate', 'roundStatus', 'joiningDate', 'candidateResume','feedback'];
       }
    });

  }
  //get all the details of candidate information
  private getCandidateDetail() {
    this.candidateService.getCandidateDetailList().subscribe(data => {
      this.candidate = data; 
      this.dataSource = new MatTableDataSource<Candidate>(this.candidate);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
     
    });
  }

  //Download resume file
  downloadFile(url:any ) {
    const a = document.createElement('a')
    a.href = url;
    a.download = url.split('/').pop();
    a.target="_blank";
    document.body.appendChild(a);
    a.click()
    document.body.removeChild(a);
  }


  //color chnage based on status
  getCellColor(roundStatus: String, joiningFeedback:string) {
    if(joiningFeedback !=null){
      // return '#ecc9ee';
      return '#ffeaea';
    }else{

      if (roundStatus == 'Selected') {
        //green
        return '#c3e6cb';
      } else if (roundStatus == 'Scheduled') {
        //yellow
        return '#ffeeba ';
  
      } else if (roundStatus == 'Rejected') {
        //red
        return '#f5c6cb';
      } else if (roundStatus == 'Shortlisted') {
        //light blue
        return '#cfe2ff';
      } else if (roundStatus == 'Rescheduled') {
        //light violet
        return '#c6c8ca';
      } else if (roundStatus == 'On-hold') {
        return '#c0f1f7';
      }
      else {
        return '';
      }
    }
    
  }

  private getScheduleInfo() {
    this.candidateService.getScheduleList().subscribe(data => {
      this.scheduled = data;
      // console.log(this.scheduled);
    });
  }

  
  //search
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  // Open candidate dialog for editing joining date
  editCandidateJoiningDate(scheduledInterviewId: number, candidateUniqueNumber: string, candidateFullName: string, joiningDate: Date, joiningFeedback: string) {
    const dialogRef = this.dialog.open(MatupadtejoiningdateComponentComponent, {
      data: {
        id: scheduledInterviewId,
        name: candidateFullName,
        candidateId: candidateUniqueNumber,
        joiningdate: joiningDate,
        joiningfeedback: joiningFeedback
      }
    });


  }

  //Mat Dialogbox feedback List
  feedbackList(scheduledInterviewId: number, candidateUniqueNumber: string, candidateFullName: string) {
    const dialogRef = this.dialog.open(MatfeedbacklistComponent, {
      data: {
        id: scheduledInterviewId,
        name: candidateFullName,
        candidateId: candidateUniqueNumber,
      }
    });


  }
  //upadte the record of candidate information through Id
  updateCandidateDetails(candidateNo: number) {
    this.router.navigate(['update-candidate-details', candidateNo]);
  }

  //delete the candidate information through Id
  deleteCandidatedetails(candidateNo: number) {
    this.candidateService.deleteCandidateDeatil(candidateNo).subscribe(data => {
      this.getCandidateDetail();
    })
  }



}
