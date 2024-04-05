import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Onboarding } from 'src/app/model/onboarding-model/onboarding';
import { OnboardingService } from 'src/app/service/onboarding-service/onboarding.service';

@Component({
  selector: 'app-onboardingmaster',
  templateUrl: './onboardingmaster.component.html',
  styleUrls: ['./onboardingmaster.component.css']
})
export class OnboardingmasterComponent {
  onboarding!: Onboarding[];
  displayedColumns: string[] = ['candidateUniqueNumber', 'candidateFullName', 'experienceLevel', 'allMarkSheets', 'residenceProof', 'photoIdProof', 'passportPhotograph', 'relievingLetterCurrentOrg', 'lastThreeMonthsPayslips', 'earlierJobRelievingLetter', 'canceledCheque', 'action'];
  dataSource = new MatTableDataSource<Onboarding>();

  today: Date = new Date();


  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private onboardingService: OnboardingService, private router: Router) {
    this.getOnboardingDetails();
  }
  //get all the list of vacancy details
  private getOnboardingDetails() {
    this.onboardingService.getOnboardingList().subscribe(data => {
      this.onboarding = data;
      this.dataSource = new MatTableDataSource<Onboarding>(this.onboarding);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  ngOnInit() {
    setTimeout(() => this.dataSource.paginator = this.paginator);
    this.dataSource.sort = this.sort;


  }

  // Changing color of documents 
  getColorBasedonDocument(experienceLevel: String, allMarkSheets: Boolean, residenceProof: Boolean, photoIdProof: Boolean, passportPhotograph: Boolean, relievingLetterCurrentOrg: Boolean, lastThreeMonthsPayslips: Boolean, earlierJobRelievingLetter: Boolean, canceledCheque: Boolean) {
    if ((experienceLevel == 'Fresher') && (allMarkSheets == false || residenceProof == false || photoIdProof == false || passportPhotograph == false || canceledCheque == false )) {

      return '#f5c6cb';
      
    
    } else if((experienceLevel=='Experience')  && (allMarkSheets == false || residenceProof == false || photoIdProof == false || passportPhotograph == false || canceledCheque == false || relievingLetterCurrentOrg== false || lastThreeMonthsPayslips== false || earlierJobRelievingLetter==false )     ) {
     
      return '#f5c6cb';
    }
    else{
      return
    }
   
  }


  //search 
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  //update the vacancy details through Id
  updateOnboardingDetails(onboardingId: number) {
    this.router.navigate(['update-onboarding', onboardingId]);
  }


}
