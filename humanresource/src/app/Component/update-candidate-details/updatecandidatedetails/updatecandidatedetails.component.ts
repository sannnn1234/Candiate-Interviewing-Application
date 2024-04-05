import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Candidate } from 'src/app/model/candidate';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { CandidateserviceService } from 'src/app/service/candidateservice.service';
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
  selector: 'app-updatecandidatedetails',
  templateUrl: './updatecandidatedetails.component.html',
  styleUrls: ['./updatecandidatedetails.component.css'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
  ],
})
export class UpdatecandidatedetailsComponent implements OnInit {

  date = new FormControl(moment());
  isProcess = false;

  setMonthAndYear(normalizedMonthAndYear: Moment, datepicker: MatDatepicker<Moment>) {
    const ctrlValue = this.date.value;
    ctrlValue?.month(normalizedMonthAndYear.month());
    ctrlValue?.year(normalizedMonthAndYear.year());
    this.date.setValue(ctrlValue);
    datepicker.close();
  }
  candidateNo: number;
  selectedFile: File;
  selectedFiles: File;
  location: any;
  candidate: Candidate = new Candidate();

  constructor(private candidateService: CandidateserviceService, private route: ActivatedRoute, private router: Router, private snack: MatSnackBar, private gc: GlobalBaseUrl) { }

  //validation 
  updateCandidateInfo = new FormGroup({
    fullName: new FormControl('', [Validators.required]),
    dateOfBirth: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    officialEmailId: new FormControl('', [Validators.required, Validators.email]),
    contactnumber: new FormControl('', [Validators.required, Validators.maxLength(10), Validators.minLength(10), Validators.pattern("[0-9]*")]),
    candidateImage: new FormControl(),
    currentlocation: new FormControl('', [Validators.required]),
    profilePreference1: new FormControl('', [Validators.required]),
    profilePreference2: new FormControl(),
    relevantexperience: new FormControl('', [Validators.minLength(5), Validators.pattern('^\\d+(\\.\\d{1,2})?$')]),
    totalexperience: new FormControl('', [Validators.minLength(5), Validators.pattern('^\\d+(\\.\\d{1,2})?$')]),
    keySkills: new FormControl(),
    locationpreference: new FormControl(),
    currentsalary: new FormControl(),
    expectedsalary: new FormControl(),
    readyToNegotiate: new FormControl(),
    candidateResume: new FormControl(),
    noticePeriod: new FormControl(),
  }, { validators: this.isExperienceComparisonError });

  get CandidateFullName() {
    return this.updateCandidateInfo.get("fullName") as FormControl;
  }
  get DateOfBirth() {
    return this.updateCandidateInfo.get("dateOfBirth") as FormControl;
  }
  get ContactNumber() {
    return this.updateCandidateInfo.get("contactnumber") as FormControl;
  }
  get EmailAddress() {
    return this.updateCandidateInfo.get("email") as FormControl;
  }
  get OfficialEmailId() {
    return this.updateCandidateInfo.get("officialEmailId") as FormControl;
  }
  get CurrentLocation() {
    return this.updateCandidateInfo.get("currentlocation") as FormControl;
  }
  get ProfilePreference1() {
    return this.updateCandidateInfo.get("profilePreference1") as FormControl;
  }
  get ProfilePreference2() {
    return this.updateCandidateInfo.get("profilePreference2") as FormControl;
  }
  get RelevantExperience() {
    return this.updateCandidateInfo.get("relevantexperience") as FormControl;
  }
  get TotalExperience() {
    return this.updateCandidateInfo.get("totalexperience") as FormControl;
  }
  get KeySkills() {
    return this.updateCandidateInfo.get("keySkills") as FormControl;
  }
  get LocationPreference() {
    return this.updateCandidateInfo.get("locationpreference") as FormControl;
  }
  get CurrentSalary() {
    return this.updateCandidateInfo.get("currentsalary") as FormControl;
  }
  get ExpectedSalary() {
    return this.updateCandidateInfo.get("expectedsalary") as FormControl;
  }
  get CandidateResume() {
    return this.updateCandidateInfo.get("candidateResume") as FormControl;
  }

  get NoticePeriod() {
    return this.updateCandidateInfo.get("noticePeriod") as FormControl;
  }

  url: any;
  msg = "";
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

  private getLocation() {
    this.candidateService.getListOfLocation().subscribe(data => {
      this.location = data;
    });
  }


  selectResume(event: any) {
    this.selectedFiles = event.target.files[0];
  }

  isExperienceComparisonError(control: AbstractControl): ValidationErrors | null {
    const relevantexperience = control.get('relevantexperience')?.value;
    const totalexperience = control.get('totalexperience')?.value;
    if (totalexperience < relevantexperience) {
      control.get('relevantexperience')?.setErrors({ relExpGtr: true });
      control.get('totalexperience')?.setErrors({ relExpGtr: true });
      return { relExpGtr: true };
    } else {
      control.get('relevantexperience')?.updateValueAndValidity({ onlySelf: true, emitEvent: true, });
      control.get('totalexperience')?.updateValueAndValidity({ onlySelf: true, emitEvent: true, });
      return null;
    }
  }

  ngOnInit(): void {
    this.candidateNo = this.route.snapshot.params['candidateNo'];
    this.candidateService.getCandidateDeatilById(this.candidateNo).subscribe(data => {
      this.candidate = data;
    }, error => console.log(error));
    this.getLocation();
  }


  //[update the candidate information]
  onSubmit() {
    if (this.updateCandidateInfo.controls['fullName'].valid && this.updateCandidateInfo.controls['dateOfBirth'].valid && this.updateCandidateInfo.controls['email'].valid && this.updateCandidateInfo.controls['contactnumber'].valid && this.updateCandidateInfo.controls['currentlocation'].valid && this.updateCandidateInfo.controls['profilePreference1'].valid, this.updateCandidateInfo.controls['totalexperience'].valid, this.updateCandidateInfo.controls['relevantexperience'].valid) {
      this.isProcess = true;
      const fromDt = new FormData();
      if (this.selectedFile == undefined) {
      }
      else {
        fromDt.append('file', this.selectedFile, this.selectedFile.name);
      }
      if (this.selectedFiles == undefined) {
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
      fromDt.append('profilePreference1', this.candidate.profilePreference1 + '');
      fromDt.append('profilePreference2', this.candidate.profilePreference2 == undefined ? ' ' : this.candidate.profilePreference2 + '');
      fromDt.append('relevantExperience', this.candidate.relevantExperience == undefined ? '0' : this.candidate.relevantExperience + '');
      fromDt.append('totalExperience', this.candidate.totalExperience == undefined ? '0' : this.candidate.totalExperience + '');
      fromDt.append('keySkills', this.candidate.keySkills == undefined ? ' ' : this.candidate.keySkills + '');
      fromDt.append('locationPreference', this.candidate.locationPreference == undefined ? ' ' : this.candidate.locationPreference + '');
      fromDt.append('currentSalary', this.candidate.currentSalary == undefined ? '0' : this.candidate.currentSalary + '');
      fromDt.append('expectedSalary', this.candidate.expectedSalary == undefined ? '0' : this.candidate.expectedSalary + '');
      fromDt.append('readyToNegotiate', this.candidate.readyToNegotiate == undefined ? ' ' : this.candidate.readyToNegotiate + '');
      fromDt.append('noticePeriod', this.candidate.noticePeriod == undefined ? '0' : this.candidate.noticePeriod + '');

      this.candidateService.updateCandidateDeatil(this.candidateNo, fromDt).subscribe(data => {
        this.isProcess=false;
        this.snack.open("Successfully  Updated", '', {
          duration: 1000,
        });
        this.router.navigate(['candidate-master']);
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
