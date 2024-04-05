import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Candidate } from 'src/app/model/candidate';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { CandidateserviceService } from 'src/app/service/candidateservice.service';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { DepartmentprofileService } from 'src/app/service/department-profile-service/departmentprofile.service';
import { DepartmentService } from 'src/app/service/departmentround/department.service';
import { EmployeegroupService } from 'src/app/service/employee/employeegroup.service';
import { InterviewInfoService } from 'src/app/service/interview-info/interview-info.service';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';
import { Moment } from 'moment';
import { MatDatepicker } from '@angular/material/datepicker';
import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import * as moment from 'moment';


const MY_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};


@Component({
  selector: 'app-scheduledinterview',
  templateUrl: './scheduledinterview.component.html',
  styleUrls: ['./scheduledinterview.component.css'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
  ],
})
export class ScheduledinterviewComponent implements OnInit {

  minDate = new Date();

  currentDate: any = new Date();
  selectedMatDate !: Date;
  isTimeDisabled: boolean = false;
  file: File;
  isProcess = false;



  roundmodule: Roundmodule = new Roundmodule();
  candidate: Candidate = new Candidate();
  candidatestatus: any = [];
  interviewer: any = [];
  interviewlist: any = [];
  interviewerlist: any = [];
  departmentlist: Departmentdetails[];
  video: any;
  twelveHourTime: string;
  ampm: string;
  profile: any;
  round1Hr: String;
  dateTimes: string;
  selection: any;
  roundList: Roundmodule[];
  round: Roundmodule[];
  roundStatus: string;
  interviewernames: any;
  displayVideoslink = false;
  videoLinks: any;
  displayContactLink = false;
  schedule: any[];
  profileId: number;

  roundDetails: number;


  scheduleInteview: ScheduledInterview = new ScheduledInterview();

  interviewSchedule = new FormGroup({
    candidateUniqueNumber: new FormControl('', [Validators.required]),
    candidateFullName: new FormControl('', [Validators.required]),
    departmentId: new FormControl('', [Validators.required]),
    profileId: new FormControl('', [Validators.required]),
    interviewerEmployeeId: new FormControl('', [Validators.required]),
    interviewerContactNo: new FormControl('', [Validators.required]),
    modeNo: new FormControl('', [Validators.required]),
    videoLinkDetails: new FormControl('', [Validators.required]),
    roundDate: new FormControl('', [Validators.required]),
    roundTime: new FormControl('', [Validators.required]),
    roundStatus: new FormControl('', [Validators.required]),
    roundDetails: new FormControl('', [Validators.required]),
    icsFile: new FormControl(''),

  });



  get CandidateUniqueNumber() {
    return this.interviewSchedule.get("candidateUniqueNumber") as FormControl;
  }
  get CandidateFullName() {
    return this.interviewSchedule.get("candidateFullName") as FormControl;
  }

  get Department() {
    return this.interviewSchedule.get("departmentId") as FormControl;
  }

  get Profile() {
    return this.interviewSchedule.get("profileId") as FormControl;
  }

  get Mode() {
    return this.interviewSchedule.get("modeNo") as FormControl;
  }

  get VideoLink() {
    return this.interviewSchedule.get("videoLinkDetails") as FormControl;
  }


  get RoundDate() {
    return this.interviewSchedule.get("roundDate") as FormControl;
  }

  get RoundTime() {
    return this.interviewSchedule.get("roundTime") as FormControl;
  }

  get InterviewerName() {
    return this.interviewSchedule.get("interviewerEmployeeId") as FormControl;
  }

  get RoundDetails() {
    return this.interviewSchedule.get("roundDetails") as FormControl;
  }

  get RoundStatus() {
    return this.interviewSchedule.get("roundStatus") as FormControl;
  }





  constructor(private candidateService: CandidateserviceService, private departmentService: DepartmentService, private departmentdetailsService: DepartmentDetailsService, private profileService: ProfileServiceService, private employeeService: EmployeegroupService, private interviewInfoService: InterviewInfoService, private scheduleInterviewService: ScheduleInterviewService, private departmentprofileService: DepartmentprofileService, private gc: GlobalBaseUrl, private router: Router, private snack: MatSnackBar) {

  }

  ngOnInit(): void {
    this.getcandiadteInfo();
    this.getInterviewerInfo();
    this.getInterviewDetails();
    this.getdepartment();
    this.profileInterviewer();
    this.getselectioncriteria();
    this.getInterviewSCheduleDetails();
  }


  selectResume(event: any) {
    this.file = event.target.files[0];
  }

  // [get all the list of candidate]
  private getcandiadteInfo() {
    this.candidateService.getCandidateListByStatus().subscribe(data => {
      this.candidatestatus = data;
    }, error => console.log(error));
  }

//[Search For Employee List]
onKey(event:any) {
  this.candidatestatus=this.filterCandiadteList(event.target.value);
  if (event.target.value =='') {
  this.getcandiadteInfo();
  }
  }
  
  filterCandiadteList(value:string) {
  let filter=value.toLowerCase().trim();
  return this.candidatestatus.filter((op:any) =>op.candidateFullName.toLowerCase().includes(filter));
  }

  // [get all the list of Interviwer]
  private getInterviewerInfo() {
    this.employeeService.getEmployeeDetailsList().subscribe(data => {
      this.interviewer = data;
    });
  }

  //[get all the list of Interview Schedule]
  private getInterviewSCheduleDetails() {
    this.scheduleInterviewService.getInterviewerScheduleDetailsList().subscribe(data => {
      this.schedule = data;
    });
  }

  //get all the list of Interviwer
  private getInterviewDetails() {
    this.interviewInfoService.getInterviewInformationList().subscribe(data => {
      this.interviewlist = [...new Set(data.map(x => x.modeNo))];
    });
  }

  //get all the department from department master
  private getdepartment() {
    this.departmentdetailsService.getDepartmentList().subscribe(data => {
      this.departmentlist = data;
    }, error => console.log(error));

  }



  //[get all the department from department master]
  getdepartmentProfileList(departmentId: any) {
    this.departmentprofileService.getDepartmentProfileMapping(departmentId).subscribe(data => {
      this.profile = data;
    }, error => console.log(error));

  }
  //[get all the list of selection criteria]
  private getselectioncriteria() {
    this.departmentService.getDepartmentRoundInfoList().subscribe(data => {
      this.selection = data;
    }, error => console.log(error));
  }

  //[get interviewer list through selecting department]
  profileInterviewer() {
    this.scheduleInterviewService.getHRProfileId().subscribe(data => {
      this.profileId = data;
      this.scheduleInterviewService.getInterviewerName(this.profileId).subscribe(data => {
        this.interviewernames = data;
      }, error => console.log(error));
    })

  }

  //[videolink view]
  videoLinkDisplay() {
    if ((this.scheduleInteview.modeNo == 'Video')) {
      this.displayVideoslink = true;
    }
    else {
      this.displayVideoslink = false;
      this.scheduleInteview.videoLinkDetails = '';
    }

    if (this.scheduleInteview.modeNo == 'Video' || this.scheduleInteview.modeNo == 'Face to Face') {
      this.displayContactLink = false;
    }
    else {
      this.displayContactLink = true;
    }
  }



  //create schedule interview information
  OnSubmit() {
    if (this.scheduleInteview.modeNo == 'Video') {
      if (this.interviewSchedule.controls['candidateUniqueNumber'].valid && this.interviewSchedule.controls['departmentId'].valid && this.interviewSchedule.controls['profileId'].valid && this.interviewSchedule.controls['roundDate'].valid && this.interviewSchedule.controls['roundTime'].valid && this.interviewSchedule.controls['modeNo'].valid && this.interviewSchedule.controls['videoLinkDetails'].valid) {
        this.isProcess = true;
        this.roundStatus = "Scheduled";
        this.roundDetails = 1;

        const fromDt = new FormData();

        if (this.scheduleInteview.modeNo === "Video") {
          if (this.file == undefined) {
            alert("Kindly upload the .ics file");
          } else {
            fromDt.append('file', this.file, this.file.name);
          }
        }
        if (this.file != undefined) {
          fromDt.append('file', this.file, this.file.name);
        }
        fromDt.append('candidateUniqueNumber', String(this.scheduleInteview.candidateUniqueNumber));
        fromDt.append('departmentId', String(this.scheduleInteview.departmentId));
        fromDt.append('modeNo', String(this.scheduleInteview.modeNo));
        fromDt.append('interviewerContactNo', String(this.scheduleInteview.interviewerContactNo));
        fromDt.append('interviewerEmployeeId', String(this.scheduleInteview.interviewerEmployeeId));
        fromDt.append('profileId', String(this.scheduleInteview.profileId));
        fromDt.append('roundDate', this.gc.formatDate(new Date(this.scheduleInteview.roundDate)));
        fromDt.append('roundTime', String(this.scheduleInteview.roundTime));
        fromDt.append('videoLinkDetails', String(this.scheduleInteview.videoLinkDetails));

        this.scheduleInterviewService.createScheduledetails(fromDt, this.roundStatus, this.roundDetails)
          .subscribe(
            data => {
              this.isProcess = false;
              this.snack.open("Interview Scheduled Successfully", '', {
                duration: 1000,
              });
              this.router.navigate(['home']);
            },
            error => {
              this.isProcess = false;
              this.snack.open("Unable to Scheduled Interview", '', {
                duration: 1000,
              });
              console.log("Error:", error);
            }
          );
      }
    } else {
      if (this.interviewSchedule.controls['candidateUniqueNumber'].valid && this.interviewSchedule.controls['departmentId'].valid && this.interviewSchedule.controls['profileId'].valid && this.interviewSchedule.controls['roundDate'].valid && this.interviewSchedule.controls['roundTime'].valid && this.interviewSchedule.controls['modeNo'].valid) {
        this.isProcess = true;
        this.roundStatus = "Scheduled";
        this.roundDetails = 1;

        const fromDt = new FormData();

        if (this.scheduleInteview.modeNo === "Video") {
          if (this.file == undefined) {
            alert("Kindly upload the .ics file");
          } else {
            fromDt.append('file', this.file, this.file.name);
          }
        }
        if (this.file != undefined) {
          fromDt.append('file', this.file, this.file.name);
        }
        fromDt.append('candidateUniqueNumber', String(this.scheduleInteview.candidateUniqueNumber));
        fromDt.append('departmentId', String(this.scheduleInteview.departmentId));
        fromDt.append('modeNo', String(this.scheduleInteview.modeNo));
        fromDt.append('interviewerContactNo', String(this.scheduleInteview.interviewerContactNo));
        fromDt.append('interviewerEmployeeId', String(this.scheduleInteview.interviewerEmployeeId));
        fromDt.append('profileId', String(this.scheduleInteview.profileId));
        fromDt.append('roundDate', this.gc.formatDate(new Date(this.scheduleInteview.roundDate)));
        fromDt.append('roundTime', String(this.scheduleInteview.roundTime));
        fromDt.append('videoLinkDetails', String(this.scheduleInteview.videoLinkDetails));

        this.scheduleInterviewService.createScheduledetails(fromDt, this.roundStatus, this.roundDetails)
          .subscribe(
            data => {
              this.isProcess = false;
              this.snack.open("Interview Scheduled Successfully", '', {
                duration: 1000,
              });
              this.router.navigate(['home']);
            },
            error => {
              this.isProcess = false;
              this.snack.open("Unable to Scheduled Interview", '', {
                duration: 1000,
              });
              console.log("Error:", error);
            }
          );
      }
    }

  }

}






