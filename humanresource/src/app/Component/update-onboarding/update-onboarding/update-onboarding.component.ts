import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DxiSortByGroupSummaryInfoComponent } from 'devextreme-angular/ui/nested';
import { Onboarding } from 'src/app/model/onboarding-model/onboarding';
import { CandidateserviceService } from 'src/app/service/candidateservice.service';
import { OnboardingService } from 'src/app/service/onboarding-service/onboarding.service';

@Component({
  selector: 'app-update-onboarding',
  templateUrl: './update-onboarding.component.html',
  styleUrls: ['./update-onboarding.component.css']
})
export class UpdateOnboardingComponent {
  onboardingId!: number;
  onboardinglist!: Onboarding[];
  experiance !: any
  candidateList!: any[];
  onboarding: Onboarding = new Onboarding();
  updateOnbaordingDetails = new FormGroup({
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
  today: Date = new Date();

  get Candidate() {
    return this.updateOnbaordingDetails.get("candidateUniqueNumber") as FormControl;
  }
  get Experience() {
    return this.updateOnbaordingDetails.get("experienceLevel") as FormControl;
  }

  get Comment() {
    return this.updateOnbaordingDetails.get("isSubmittedDocument") as FormControl;
  }



  constructor(private onboardingService: OnboardingService, private route: ActivatedRoute, private router: Router, private candidateService: CandidateserviceService) {
    this.getOnboardingCandidate();
    this.getCandidateListDetails();
  }


  ngOnInit() {
    this.onboardingId = this.route.snapshot.params['onboardingId'];
    this.onboardingService.getOnboardingDetailById(this.onboardingId).subscribe(data => {
      this.onboarding = data;
      
    }, error => console.log(error));

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



  //get the list onboarding candidate
  private getOnboardingCandidate() {
    this.onboardingService.getOnboardingCandidateList().subscribe(data => {
      this.onboardinglist = data;
    });
  }


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
