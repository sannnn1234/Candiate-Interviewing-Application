import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Candidate } from 'src/app/model/candidate';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { DepartmentprofileService } from 'src/app/service/department-profile-service/departmentprofile.service';
import { DepartmentService } from 'src/app/service/departmentround/department.service';
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
  selector: 'app-updatecandidateinterview',
  templateUrl: './updatecandidateinterview.component.html',
  styleUrls: ['./updatecandidateinterview.component.css'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
],
})
export class UpdatecandidateinterviewComponent {

  minDate = new Date();
  currentDate: any = new Date();
  selectedMatDate !: Date;
  isProcess = false;

  roundmodule: Roundmodule = new Roundmodule();
  candidate: Candidate = new Candidate();
  scheduledInterviewId: number;
  candidatestatus: any = [];
  interviewer: any = [];
  interviewlist: any = [];
  interviewerlist: any = [];
  video: any;
  profileId: number;
  twelveHourTime: string;
  ampm: string;
  departmentprofile: any;
  profiledetails: any;
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
  file: File;

  // interviewernames: Role[];

  scheduleInteview: ScheduledInterview = new ScheduledInterview();

  updateSchedule = new FormGroup({
    candidateUniqueNumber: new FormControl('', [Validators.required]),
    candidateFullName: new FormControl('', [Validators.required]),
    candidateEmailAddress: new FormControl('', [Validators.required, Validators.email]),
    department: new FormControl('', [Validators.required]),
    profile: new FormControl('', [Validators.required]),
    interviewerName: new FormControl('', [Validators.required]),
    interviewerContactNo: new FormControl(''),
    interviewerEmail: new FormControl('', [Validators.required]),
    modeNo: new FormControl('', [Validators.required]),
    videoLinkDetails: new FormControl('', [Validators.required]),
    roundDate: new FormControl('', [Validators.required]),
    roundTime: new FormControl('', [Validators.required]),
    roundStatus: new FormControl('', [Validators.required]),
    roundDetails: new FormControl('', [Validators.required]),
    icsFile: new FormControl(),
  });



  get CandidateUniqueNumber() {
    return this.updateSchedule.get("candidateUniqueNumber") as FormControl;
  }
  get CandidateFullName() {
    return this.updateSchedule.get("candidateFullName") as FormControl;
  }

  get CandidateEmailAddress() {
    return this.updateSchedule.get("candidateEmailAddress") as FormControl;
  }
  get Department() {
    return this.updateSchedule.get("department") as FormControl;
  }

  get Profile() {
    return this.updateSchedule.get("profile") as FormControl;
  }

  get Mode() {
    return this.updateSchedule.get("modeNo") as FormControl;
  }

  get VideoLink() {
    return this.updateSchedule.get("videoLinkDetails") as FormControl;
  }


  get RoundDate() {
    return this.updateSchedule.get("roundDate") as FormControl;
  }

  get RoundTime() {
    return this.updateSchedule.get("roundTime") as FormControl;
  }

  get InterviewerName() {
    return this.updateSchedule.get("interviewerName") as FormControl;
  }

  get RoundDetails() {
    return this.updateSchedule.get("roundDetails") as FormControl;
  }

  get RoundStatus() {
    return this.updateSchedule.get("roundStatus") as FormControl;
  }








  constructor(private departmentService: DepartmentService, private interviewInfoService: InterviewInfoService, private departmentdetailsService: DepartmentDetailsService, private profileService: ProfileServiceService, private scheduleInterviewService: ScheduleInterviewService, private departmentprofileService: DepartmentprofileService, private gc: GlobalBaseUrl, private route: ActivatedRoute, private router: Router, private snack: MatSnackBar) {

  }

  ngOnInit(): void {
    this.scheduledInterviewId = this.route.snapshot.params['scheduledInterviewId'];
    this.scheduleInterviewService.getScheduleDeatilById(this.scheduledInterviewId).subscribe(data => {
      this.scheduleInteview = data;
      this.profileInterviewer();
      this.getInterviewDetails();
      this.getdepartment();
      this.getProfile();
      this.getselectioncriteria();
      this.roundListFetch();
    });
  }

  selectResume(event: any) {
    this.file = event.target.files[0];
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


  roundListFetch() {
    this.departmentService.getRoundList().subscribe(data => {
      this.roundList = data;
    }, error => alert("Error in getting round List"))

  }


  //get interviewer list through selecting department
  profileInterviewer() {
    this.scheduleInterviewService.getInterviewerName(this.scheduleInteview.profileId).subscribe(data => {
      this.interviewernames = data;
    });
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

  //schedule interview information
  OnSubmit() {
    if (this.scheduleInteview.modeNo == 'Video') {
      if (this.updateSchedule.controls['candidateFullName'].valid && this.updateSchedule.controls['department'].valid && this.updateSchedule.controls['profile'].valid && this.updateSchedule.controls['roundDate'].valid && this.updateSchedule.controls['roundTime'].valid && this.updateSchedule.controls['modeNo'].valid && this.updateSchedule.controls['videoLinkDetails'].valid) {
        this.isProcess = true;
        this.roundStatus = "Scheduled";
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
        fromDt.append('modeNo', String(this.scheduleInteview.modeNo));
        fromDt.append('interviewerContactNo', String(this.scheduleInteview.interviewerContactNo));
        fromDt.append('interviewerEmployeeId', String(this.scheduleInteview.interviewerEmployeeId));
        fromDt.append('profileId', String(this.scheduleInteview.profileId));
        fromDt.append('roundDetails', String(this.scheduleInteview.roundDetails));
        fromDt.append('roundDate', this.gc.formatDate(new Date(this.scheduleInteview.roundDate)));
        fromDt.append('roundTime', String(this.scheduleInteview.roundTime));
        fromDt.append('videoLinkDetails', String(this.scheduleInteview.videoLinkDetails));
        this.scheduleInterviewService.updateNextRoundScheduleInterview(this.scheduledInterviewId, this.roundStatus, fromDt).subscribe(data => {
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
      if (this.updateSchedule.controls['candidateFullName'].valid && this.updateSchedule.controls['department'].valid && this.updateSchedule.controls['profile'].valid && this.updateSchedule.controls['roundDate'].valid && this.updateSchedule.controls['roundTime'].valid && this.updateSchedule.controls['modeNo'].valid) {
        this.roundStatus = "Scheduled";
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
        fromDt.append('modeNo', String(this.scheduleInteview.modeNo));
        fromDt.append('interviewerContactNo', String(this.scheduleInteview.interviewerContactNo));
        fromDt.append('interviewerEmployeeId', String(this.scheduleInteview.interviewerEmployeeId));
        fromDt.append('profileId', String(this.scheduleInteview.profileId));
        fromDt.append('roundDetails', String(this.scheduleInteview.roundDetails));
        fromDt.append('roundDate', this.gc.formatDate(new Date(this.scheduleInteview.roundDate)));
        fromDt.append('roundTime', String(this.scheduleInteview.roundTime));
        fromDt.append('videoLinkDetails', String(this.scheduleInteview.videoLinkDetails));
        this.scheduleInterviewService.updateNextRoundScheduleInterview(this.scheduledInterviewId, this.roundStatus, fromDt).subscribe(data => {
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


