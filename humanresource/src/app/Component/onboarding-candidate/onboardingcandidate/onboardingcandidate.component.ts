import { Component, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatCheckbox } from '@angular/material/checkbox';
import { Router } from '@angular/router';
import { Onboarding } from 'src/app/model/onboarding-model/onboarding';
import { CandidateserviceService } from 'src/app/service/candidateservice.service';
import { OnboardingService } from 'src/app/service/onboarding-service/onboarding.service';

@Component({
  selector: 'app-onboardingcandidate',
  templateUrl: './onboardingcandidate.component.html',
  styleUrls: ['./onboardingcandidate.component.css']
})


export class OnboardingcandidateComponent {


  onboardinglist!: any[];
  candidateList!: any[];
  experiance !: any;
  checked: boolean = false;


  today: Date = new Date();
  onboarding: Onboarding = new Onboarding();
  createOnbaordingDetails = new FormGroup({
    candidateUniqueNumber: new FormControl('', [Validators.required]),
    experienceLevel: new FormControl('', [Validators.required]),
    allMarkSheets: new FormControl(),
    residenceProof: new FormControl(),
    photoIdProof: new FormControl(),
    passportPhotograph: new FormControl(),
    relievingLetterCurrentOrg: new FormControl(),
    lastThreeMonthsPayslips: new FormControl(),
    earlierJobRelievingLetter: new FormControl(),
    canceledCheque: new FormControl(),
    isSubmittedDocument: new FormControl(''),
  });


  get Candidate() {
    return this.createOnbaordingDetails.get("candidateUniqueNumber") as FormControl;
  }
  get Experience() {
    return this.createOnbaordingDetails.get("experienceLevel") as FormControl;
  }

  get Comment(){
    return this.createOnbaordingDetails.get("isSubmittedDocument") as FormControl;
  }



  constructor(private onboardingService: OnboardingService, private router: Router, private candidateService: CandidateserviceService) {
    this.getOnboardingCandidate();
    this.getCandidateListDetails();

  }

  ngOnInit() {


  }

  // Getting candidate list from candidate page
  private getCandidateListDetails() {
    this.candidateService.getCandidateDetailList().subscribe(data => {
      this.candidateList = data;

    });

  }


  totalExperienceChange(w: any) {
    let total_Experiance: { candidateUniqueNumber: any, candidateFullName: any, totalExperience: any }[] = this.candidateList?.filter((e: { candidateUniqueNumber: any, totalExperience: any }) => {
      return e.candidateUniqueNumber == w;
    });
    this.experiance = total_Experiance[0]['totalExperience'];
    if (this.experiance == 0) {
      this.onboarding.experienceLevel = "Fresher"
    } else if (this.experiance > 0) {
      this.onboarding.experienceLevel = "Experience"
    }
  }


  //get the list of  onboarding candidate
  private getOnboardingCandidate() {
    this.onboardingService.getOnboardingCandidateList().subscribe(data => {
      this.onboardinglist = data;
    });

  }





  //create candidate onboarding 
  Submit() {
    const documents = [
      { name: 'allMarkSheets', prop: 'allMarkSheetsDate' },
      { name: 'residenceProof', prop: 'residenceProofDate' },
      { name: 'photoIdProof', prop: 'photoIdProofDate' },
      { name: 'passportPhotograph', prop: 'passportPhotographDate' },
      { name: 'relievingLetterCurrentOrg', prop: 'relievingLetterCurrentOrgDate' },
      { name: 'lastThreeMonthsPayslips', prop: 'lastThreeMonthsPayslipsDate' },
      { name: 'earlierJobRelievingLetter', prop: 'earlierJobRelievingLetterDate' },
      { name: 'canceledCheque', prop: 'canceledChequeDate' }
    ];
    for (const { name, prop } of documents) {
      if (this.onboarding[name] == true) {
        this.onboarding[prop] = this.today;
      }
    }
    this.onboardingService.createOnboardingdetails(this.onboarding).subscribe(data => {
      this.router.navigate(['onboarding-master']);
    },
      error => {
        console.log(error);
      }
    );
  }
}
 