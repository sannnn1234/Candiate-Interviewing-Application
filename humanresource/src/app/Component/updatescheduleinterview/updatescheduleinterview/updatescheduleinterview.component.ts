import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
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
import {MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
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
  selector: 'app-updatescheduleinterview',
  templateUrl: './updatescheduleinterview.component.html',
  styleUrls: ['./updatescheduleinterview.component.css'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
],
})
export class UpdatescheduleinterviewComponent implements OnInit {

  minDate = new Date();
  data: any = [];
  candidatestatus: any = [];
  interviewer: any = [];
  interviewlist: any = [];
  video: any;
  time: string;
  profileId: number;
  twelveHourTime: string;
  ampm: string;
  scheduledInterviewId: any;
  roundStatus: string;
  departmentprofile: any;
  profiledetails: any;
  displayVideoslink = false;
  videoLinks: any;
  roundList: Roundmodule[]
  round: Roundmodule[];
  interviewernames: any;
  selection: any;
  displayContactLink = true;
  schedule: any[];
  file: File;
  isProcess = false;
  scheduleInteview: ScheduledInterview = new ScheduledInterview();

  interviewSchedule = new FormGroup({
    candidateFullName: new FormControl('', [Validators.required]),
    department: new FormControl('', [Validators.required]),
    profile: new FormControl('', [Validators.required]),
    interviewerName: new FormControl('', [Validators.required]),
    interviewerContactNo: new FormControl(''),
    interviewerEmail: new FormControl('', [Validators.required]),
    modeNo: new FormControl('', [Validators.required]),
    videoLinkDetails: new FormControl('', [Validators.required]),
    roundDate: new FormControl('', [Validators.required]),
    roundTime: new FormControl('', [Validators.required]),
    icsFile: new FormControl(),
  });



  get CandidateFullName() {
    return this.interviewSchedule.get("candidateFullName") as FormControl;
  }


  get Department() {
    return this.interviewSchedule.get("department") as FormControl;
  }

  get Profile() {
    return this.interviewSchedule.get("profile") as FormControl;
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
    return this.interviewSchedule.get("interviewerName") as FormControl;
  }





  constructor(private candidateService: CandidateserviceService, private departmentService: DepartmentService, private departmentdetailsService: DepartmentDetailsService, private profileService: ProfileServiceService, private employeeService: EmployeegroupService, private interviewInfoService: InterviewInfoService, private scheduleInterviewService: ScheduleInterviewService, private departmentprofileService: DepartmentprofileService, private gc: GlobalBaseUrl, private route: ActivatedRoute, private router: Router, private snack: MatSnackBar) {
  }
  ngOnInit(): void {
    this.scheduledInterviewId = this.route.snapshot.params['scheduledInterviewId'];
    this.scheduleInterviewService.getScheduleDeatilById(this.scheduledInterviewId).subscribe(data => {
      this.scheduleInteview = data;
      this.departmentInterviewer();
      this.getInterviewSCheduleDetails();
      this.roundListFetch();
      this.getInterviewerInfo();
      this.getInterviewDetails();
      this.getcandiadteInfo();
      this.getdepartment();
      this.getProfile();
      this.getselectioncriteria();
      this.videoLinkDisplay();
      // this.availableVideoLinks();
    }, error => console.log(error));
  }

  private getcandiadteInfo() {
    this.candidateService.getCandidateListByStatus().subscribe(data => {
      this.candidatestatus = data;
    }, error => console.log(error));
  }


  selectResume(event: any) {
    this.file = event.target.files[0];
  }

  //get all the list of Interviwer
  private getInterviewerInfo() {
    this.employeeService.getEmployeeDetailsList().subscribe(data => {
      this.interviewer = data;
      data
      this.scheduleInteview.interviewerContactNo = data.filter((e) => { return e.empId == this.scheduleInteview.interviewerEmployeeId })[0].empPhone;
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
      this.departmentprofile = data
    }, error => console.log(error));

  }

  //get all the profile from profile master
  private getProfile() {
    this.profileService.getProfileList().subscribe(data => {
      this.profiledetails = data;
    }, error => console.log(error));

  }
  //get all the list of selection criteria
  private getselectioncriteria() {
    this.departmentService.getDepartmentRoundInfoList().subscribe(data => {
      this.selection = data;
    }, error => console.log(error));
  }


  //fetch interviewer contact number based on interviewer name
  // modelChangeFn(w: any) {
  //   let conctact: { interviewerEmployeeId: any, interviewerName: any, interviewerEmail: any, interviewerContactNo: any, department: any }[] = this.interviewer.filter((e: { interviewerEmployeeId: any, interviewerName: any }) => {
  //     return e.interviewerEmployeeId == w

  //   });
  //   this.video = conctact[0]['interviewerContactNo'];
  //   this.scheduleInteview.interviewerContactNo = conctact[0]['interviewerContactNo']


  // }


  //get all the round list 
  roundListFetch() {
    this.departmentService.getRoundList().subscribe(data => {
      this.roundList = data;
    }, error => alert("Error in getting round List"))

  }

  //get all the list of Interview Schedule list
  private getInterviewSCheduleDetails() {
    this.scheduleInterviewService.getInterviewerScheduleDetailsList().subscribe(data => {
      this.schedule = data;
      // console.log(this.schedule);
    });
  }

  //get interviewer list through selecting department
  departmentInterviewer() {
    if (this.scheduleInteview.roundDetails == 1) {
      this.scheduleInterviewService.getHRProfileId().subscribe(data => {
        this.profileId = data;
        this.scheduleInterviewService.getInterviewerName(this.profileId).subscribe(data => {
          this.interviewernames = data;
        });
      });

    }
    else {
      this.scheduleInterviewService.getInterviewerName(this.scheduleInteview.profileId).subscribe(data => {
        this.interviewernames = data;
      });
    }
  }

  //videolink 
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

  //update schedule information
  OnSubmit() {
    if (this.scheduleInteview.modeNo == 'Video') {
      if (this.interviewSchedule.controls['candidateFullName'].valid && this.interviewSchedule.controls['department'].valid && this.interviewSchedule.controls['profile'].valid && this.interviewSchedule.controls['roundDate'].valid && this.interviewSchedule.controls['roundTime'].valid && this.interviewSchedule.controls['modeNo'].valid && this.interviewSchedule.controls['videoLinkDetails'].valid) {
        this.isProcess=true;
        this.roundStatus = "Rescheduled"
        const fromDt = new FormData();
        if (this.scheduleInteview.modeNo === "Video") {
          if (this.file == undefined) {
            alert("Kindly upload the .ics file");
            return;
          } else {
            fromDt.append('file', this.file, this.file.name);
          }
        }
        if (this.file != undefined) {
          fromDt.append('file', this.file, this.file.name);
        }
        fromDt.append('candidateUniqueNumber', String(this.scheduleInteview.candidateUniqueNumber));
        fromDt.append('candidateFullName', String(this.scheduleInteview.candidateFullName));
        fromDt.append('departmentId', String(this.scheduleInteview.departmentId));
        fromDt.append('profileId', String(this.scheduleInteview.profileId));
        fromDt.append('modeNo', String(this.scheduleInteview.modeNo));
        fromDt.append('interviewerEmployeeId', String(this.scheduleInteview.interviewerEmployeeId));
        fromDt.append('roundDetails', String(this.scheduleInteview.roundDetails));
        fromDt.append('roundDate', this.gc.formatDate(new Date(this.scheduleInteview.roundDate)));
        fromDt.append('roundTime', String(this.scheduleInteview.roundTime));
        fromDt.append('videoLinkDetails', String(this.scheduleInteview.videoLinkDetails));

        // console.log(this.scheduledInterviewId,this.scheduleInteview.roundDetails ,this.roundStatus,this.scheduleInteview)
        this.scheduleInterviewService.updateScheduleDeatil(this.scheduledInterviewId, this.scheduleInteview.roundDetails, this.roundStatus, fromDt).subscribe(data => {
          this.isProcess=false;
          this.snack.open("Rescheduled Interview  Successfully", '', {
            duration: 1000,
          });
          this.router.navigate(['home']);
        },
        error => {
          this.isProcess=false;
          this.snack.open("Unable to Rescheduled Interview", '', {
            duration: 1000,
          });
          console.log("Error:", error);
        }
      );
      }
    } else {
      if (this.interviewSchedule.controls['candidateFullName'].valid && this.interviewSchedule.controls['department'].valid && this.interviewSchedule.controls['profile'].valid && this.interviewSchedule.controls['roundDate'].valid && this.interviewSchedule.controls['roundTime'].valid && this.interviewSchedule.controls['modeNo'].valid) {
        this.roundStatus = "Rescheduled"
        const fromDt = new FormData();
        if (this.scheduleInteview.modeNo === "Video") {
          if (this.file == undefined) {
            alert("Kindly upload the .ics file");
            return;
          } else {
            fromDt.append('file', this.file, this.file.name);
          }
        }
        if (this.file != undefined) {
          fromDt.append('file', this.file, this.file.name);
        }
        fromDt.append('candidateUniqueNumber', String(this.scheduleInteview.candidateUniqueNumber));
        fromDt.append('candidateFullName', String(this.scheduleInteview.candidateFullName));
        fromDt.append('departmentId', String(this.scheduleInteview.departmentId));
        fromDt.append('profileId', String(this.scheduleInteview.profileId));
        fromDt.append('modeNo', String(this.scheduleInteview.modeNo));
        fromDt.append('interviewerEmployeeId', String(this.scheduleInteview.interviewerEmployeeId));
        fromDt.append('roundDetails', String(this.scheduleInteview.roundDetails));
        fromDt.append('roundDate', this.gc.formatDate(new Date(this.scheduleInteview.roundDate)));
        fromDt.append('roundTime', String(this.scheduleInteview.roundTime));
        fromDt.append('videoLinkDetails', String(this.scheduleInteview.videoLinkDetails));

        // console.log(this.scheduledInterviewId,this.scheduleInteview.roundDetails ,this.roundStatus,this.scheduleInteview)
        this.scheduleInterviewService.updateScheduleDeatil(this.scheduledInterviewId, this.scheduleInteview.roundDetails, this.roundStatus, fromDt).subscribe(data => {
          this.isProcess=false;
          this.snack.open("Rescheduled Interview  Successfully", '', {
            duration: 1000,
          });
          this.router.navigate(['home']);
        },
        error => {
          this.isProcess=false;
          this.snack.open("Unable to Rescheduled Interview", '', {
            duration: 1000,
          });
          console.log("Error:", error);
        }
      );
      }
    }


  }
}


