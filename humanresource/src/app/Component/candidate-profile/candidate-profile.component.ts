import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar} from '@angular/material/snack-bar';
import { Candidate } from 'src/app/model/candidate';
import { CandidateserviceService } from 'src/app/service/candidateservice.service';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Moment } from 'moment';
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
  selector: 'app-candidate-profile',
  templateUrl: './candidate-profile.component.html',
  styleUrls: ['./candidate-profile.component.css'],
  providers: [
      {
        provide: DateAdapter,
        useClass: MomentDateAdapter,
        deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
      },
      { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
  ],
})

export class CandidateProfileComponent implements OnInit {


  selectedFile: File;
  selectedFiles: File;


  candidateImage: any;
  location: any;
  isProcess=false;

  //model
  candidate: Candidate = new Candidate();
  scheduleInterview: ScheduledInterview = new ScheduledInterview();

  registerCandidate = new FormGroup({
    fullName: new FormControl('', [Validators.required]),
    dateOfBirth: new FormControl('',[Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    officialEmailId: new FormControl('', [Validators.required, Validators.email]),
    contactnumber: new FormControl('', [Validators.required, Validators.maxLength(10), Validators.minLength(10), Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]),
    candidateImage: new FormControl(),
    currentlocation: new FormControl('', [Validators.required]),
    profilePreference1: new FormControl('', [Validators.required, Validators.pattern(/^\D+$/)]),
    profilePreference2: new FormControl('',[Validators.pattern(/^\D+$/)]),
    relevantexperience: new FormControl('', [Validators.minLength(5), Validators.pattern('^\\d+(\\.\\d{1,2})?$')]),
    totalexperience: new FormControl('', [Validators.minLength(5), Validators.pattern('^\\d+(\\.\\d{1,2})?$')]),
    keySkills: new FormControl(''),
    locationpreference: new FormControl(''),
    currentsalary: new FormControl(),
    expectedsalary: new FormControl(),
    candidateResume: new FormControl('', [Validators.required]),
    readyToNegotiate: new FormControl(''),
    noticePeriod: new FormControl('',[ Validators.pattern('^\\d+(\\.\\d{1,2})?$')]),
  }, { validators: this.isExperienceComparisonError });



  get CandidateFullName() {
    return this.registerCandidate.get("fullName") as FormControl;
  }
  get DateOfBirth() {
    return this.registerCandidate.get("dateOfBirth") as FormControl;
  }
  get ContactNumber() {
    return this.registerCandidate.get("contactnumber") as FormControl;
  }
  get EmailAddress() {
    return this.registerCandidate.get("email") as FormControl;
  }
  get OfficialEmailId() {
    return this.registerCandidate.get("officialEmailId") as FormControl;
  }
  get CurrentLocation() {
    return this.registerCandidate.get("currentlocation") as FormControl;
  }
  get ProfilePreference1() {
    return this.registerCandidate.get("profilePreference1") as FormControl;
  }
  get ProfilePreference2() {
    return this.registerCandidate.get("profilePreference2") as FormControl;
  }
  get RelevantExperience() {
    return this.registerCandidate.get("relevantexperience") as FormControl;
  }
  get TotalExperience() {
    return this.registerCandidate.get("totalexperience") as FormControl;
  }
  get KeySkills() {
    return this.registerCandidate.get("keySkills") as FormControl;
  }
  get LocationPreference() {
    return this.registerCandidate.get("locationpreference") as FormControl;
  }
  get CurrentSalary() {
    return this.registerCandidate.get("currentsalary") as FormControl;
  }
  get ExpectedSalary() {
    return this.registerCandidate.get("expectedsalary") as FormControl;
  }
  get CandidateResume() {
    return this.registerCandidate.get("candidateResume") as FormControl;
  }

  get NoticePeriod() {
    return this.registerCandidate.get("noticePeriod") as FormControl;
  }
  //url; //Angular 8
  url: any; //Angular 11, for stricter type
  msg = "";

 

  //selectFile(event) { //Angular 8
  selectFile(event: any) {
    this.selectedFile = event.target.files[0];
    var mimeType = event.target.files[0].type;

    if (mimeType.match(/image\/*/) == null) {
      this.msg = "Only images are supported";
      return;
    }

    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);

    reader.onload = (_event) => {
      this.msg = "";
      this.url = reader.result;
    }
  }
  
  selectResume(event: any) {
    this.selectedFiles = event.target.files[0];
    const fileSizeInBytes = this.selectedFiles.size;
    const maxSizeInBytes = 2 * 1024 * 1024; // 2MB
    if (fileSizeInBytes > maxSizeInBytes) {
      this.snack.open("File size more than 2mb", '', {
        duration: 2000,
      });

    }
  }



  constructor(private candidateService: CandidateserviceService, private scheduleInterviewService: ScheduleInterviewService, private router: Router, private gc: GlobalBaseUrl, private snack: MatSnackBar) { }

  ngOnInit(): void {
   
  }


  //[validation for total experience and relevant experience ]
  isExperienceComparisonError(control: AbstractControl): ValidationErrors | null {
    const relevantexperience = control.get('relevantexperience')?.value;
    const totalexperience = control.get('totalexperience')?.value;    
    if(totalexperience < relevantexperience ){
      control.get('relevantexperience')?.setErrors({relExpGtr:true});
      control.get('totalexperience')?.setErrors({relExpGtr:true});
      return {relExpGtr:true};
    }else{
      control.get('relevantexperience')?.updateValueAndValidity({ onlySelf:true, emitEvent:true, });
        control.get('totalexperience')?.updateValueAndValidity({ onlySelf:true, emitEvent:true, });
        return null;
    }
  }

  // [Create Candidate Information]
  SaveCandidate() {
    if(this.registerCandidate.controls['fullName'].valid && this.registerCandidate.controls['dateOfBirth'].valid && this.registerCandidate.controls['email'].valid && this.registerCandidate.controls['contactnumber'].valid && this.registerCandidate.controls['currentlocation'].valid && this.registerCandidate.controls['profilePreference1'].valid, this.registerCandidate.controls['totalexperience'].valid, this.registerCandidate.controls['relevantexperience'].valid){
      this.isProcess=true;
      const fromDt = new FormData();
      if (this.selectedFile == undefined) {
      }
      else {
        fromDt.append('file', this.selectedFile, this.selectedFile.name);
      }
  
      if (this.selectedFiles == undefined) {
        alert("Kindly upload the resume");
      } else {
        fromDt.append('resumefile', this.selectedFiles, this.selectedFiles.name);
      }
      if (this.candidate.dateOfBirth) {
        fromDt.append('dateOfBirth', this.gc.formatDate(new Date(this.candidate.dateOfBirth)));
      }
      fromDt.append('candidateUniqueNumber', this.candidate.candidateUniqueNumber + '');
      fromDt.append('candidateFullName', this.candidate.candidateFullName + '');
      fromDt.append('emailAddress', this.candidate.emailAddress + '');
      fromDt.append('contactNumber', this.candidate.contactNumber + '');
      fromDt.append('currentLocation', this.candidate.currentLocation + '');
      fromDt.append('profilePreference1', this.candidate.profilePreference1 == undefined ? ' ' : this.candidate.profilePreference1 + '');
      fromDt.append('profilePreference2', this.candidate.profilePreference2 == undefined ? ' ' : this.candidate.profilePreference2 + '');
      fromDt.append('relevantExperience', this.candidate.relevantExperience == undefined ? '0' : this.candidate.relevantExperience + '');
      fromDt.append('totalExperience', this.candidate.totalExperience == undefined ? '0' : this.candidate.totalExperience + '');
      fromDt.append('keySkills', this.candidate.keySkills == undefined ? ' ' : this.candidate.keySkills + '');
      fromDt.append('locationPreference', this.candidate.locationPreference == undefined ? ' ' : this.candidate.locationPreference + '');
      fromDt.append('currentSalary', this.candidate.currentSalary == undefined ? '0' : this.candidate.currentSalary + '');
      fromDt.append('expectedSalary', this.candidate.expectedSalary == undefined ? '0' : this.candidate.expectedSalary + '');
      fromDt.append('readyToNegotiate', this.candidate.readyToNegotiate == undefined ? ' ' : this.candidate.readyToNegotiate + '');
      fromDt.append('noticePeriod', this.candidate.noticePeriod == undefined ? '0' : this.candidate.noticePeriod + '');
  
      this.candidateService.save(fromDt).subscribe(data => {
        this.isProcess=false;
        this.snack.open("Successfully  Created", '', {
          duration: 1000,
        });
        this.router.navigate(['scheduled-interview']);
  
      },
        error => {
          this.isProcess=false;
          if (error.status == 409) {
            this.snack.open("Candidate Already Exists", '', {
              duration: 1000,
            });
          } else {
            console.log("Error");
          }
        }
      );
    }
  }
}
