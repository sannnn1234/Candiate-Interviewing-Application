import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Vacancydetails } from 'src/app/model/vacancydetails/vacancydetails';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { DepartmentprofileService } from 'src/app/service/department-profile-service/departmentprofile.service';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';
import { VacancyService } from 'src/app/service/vacancy/vacancy.service';

@Component({
  selector: 'app-vacancy-details',
  templateUrl: './vacancy-details.component.html',
  styleUrls: ['./vacancy-details.component.css']
})
export class VacancyDetailsComponent implements OnInit {
  departmentprofile: any[] = [];
  profile: any;
  vacancydetails: Vacancydetails = new Vacancydetails();
  //validation
  createVacancyDetails = new FormGroup({
    department: new FormControl('', [Validators.required]),
    position: new FormControl('', [Validators.required]),
    profile: new FormControl('', [Validators.required]),
    experience: new FormControl('', [Validators.required]),
    target: new FormControl('', [Validators.required]),
    positionsfilled: new FormControl('', [Validators.required]),
    location: new FormControl('', [Validators.required]),
    year: new FormControl('', [Validators.required]),
    maxsalary: new FormControl('', [Validators.required]),
  });

  get Department() {
    return this.createVacancyDetails.get("department") as FormControl;
  }
  get Experience() {
    return this.createVacancyDetails.get("experience") as FormControl;
  }
  get Position() {
    return this.createVacancyDetails.get("position") as FormControl;
  }
  get Profile() {
    return this.createVacancyDetails.get("profile") as FormControl;
  }
  get Target() {
    return this.createVacancyDetails.get("target") as FormControl;
  }
  get PositionsFilled() {
    return this.createVacancyDetails.get("positionsfilled") as FormControl;
  }
  get Location() {
    return this.createVacancyDetails.get("location") as FormControl;
  }
  get Year() {
    return this.createVacancyDetails.get("year") as FormControl;
  }
  get MaxSalary() {
    return this.createVacancyDetails.get("maxsalary") as FormControl;
  }

  constructor(private vacancyService: VacancyService, private profileService: ProfileServiceService, private departmentdetailsService: DepartmentDetailsService, private router: Router, private snack: MatSnackBar) { }

  ngOnInit(): void {
    this.getdepartment();
    this.getProfile();
  }

  //get all the department from department master
  private getdepartment() {
    this.departmentdetailsService.getDepartmentList().subscribe(data => {
      this.departmentprofile = data;
    });

  }

  //get all the profile from department master
  private getProfile() {
    this.profileService.getProfileList().subscribe(data => {
      this.profile = data;
    }, error => console.log(error));

  }
  //create vacancy details 
  vacancyDetails() {
    this.vacancyService.registerVacancydetails(this.vacancydetails).subscribe(data => {
      this.snack.open("Successfully  Created", '', {
        duration: 1000,
      });
      this.router.navigate(['vacancy-master']);

    },
      error => console.log(error));

  }


}
