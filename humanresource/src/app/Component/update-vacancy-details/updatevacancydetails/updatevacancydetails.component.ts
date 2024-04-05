import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Vacancydetails } from 'src/app/model/vacancydetails/vacancydetails';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { DepartmentprofileService } from 'src/app/service/department-profile-service/departmentprofile.service';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';
import { VacancyService } from 'src/app/service/vacancy/vacancy.service';

@Component({
  selector: 'app-updatevacancydetails',
  templateUrl: './updatevacancydetails.component.html',
  styleUrls: ['./updatevacancydetails.component.css']
})
export class UpdatevacancydetailsComponent implements OnInit {
  vacancyId: number;
  departmentprofile:any[] = [];
  profile:any;

  //model of vacancy detaiils
  vacancy: Vacancydetails = new Vacancydetails();


  constructor(private vacancyService: VacancyService,private profileService:ProfileServiceService, private departmentdetailsService:DepartmentDetailsService,private route: ActivatedRoute,private router: Router) { }

  //validation of vacancy details form
  updateVacancyDetails = new FormGroup({
    department: new FormControl('', [Validators.required ]),
    position: new FormControl('',[Validators.required]),
    profile: new FormControl('',[Validators.required]),
    experience:new FormControl('',[Validators.required]),
    target:new FormControl('',[Validators.required]),
    positionsfilled:new FormControl('',[Validators.required]),
    location: new FormControl('',[Validators.required]),
    year: new FormControl('',[Validators.required]),
    maxsalary:new FormControl('',[Validators.required]),
   });

   get Department() {
    return this.updateVacancyDetails.get("department") as FormControl;
   }
   get Experience() {
    return this.updateVacancyDetails.get("experience") as FormControl;
   }
   get Position() {
    return this.updateVacancyDetails.get("position") as FormControl;
   }
   get Profile() {
    return this.updateVacancyDetails.get("profile") as FormControl;
   }
   get Target() {
    return this.updateVacancyDetails.get("target") as FormControl;
   }
   get PositionsFilled() {
    return this.updateVacancyDetails.get("positionsfilled") as FormControl;
   }
   get Location() {
    return this.updateVacancyDetails.get("location") as FormControl;
   }
   get Year() {
    return this.updateVacancyDetails.get("year") as FormControl;
   }
   get MaxSalary() {
    return this.updateVacancyDetails.get("maxsalary") as FormControl;
   }
  //get all the list of vacancy details by vacancy id
  ngOnInit(): void {

    this.vacancyId = this.route.snapshot.params['vacancyId'];

    this.vacancyService.getVacancyDeatilById(this.vacancyId).subscribe(data => {
      this.vacancy = data;
    }, error => console.log(error));

    this.getProfile();
    this.getdepartment();
  }

    //get all the department from department master
 private getdepartment() {
  this.departmentdetailsService.getDepartmentList().subscribe(data => {
    this.departmentprofile =data;
  });

}

   //get all the profile from department master
  private getProfile(){
    this.profileService.getProfileList().subscribe(data => {
      this.profile = data;
    }, error => console.log(error));

  }

  //update vacancy details form
  onSubmit(){
    this.vacancyService.updateVacancyDeatil(this.vacancyId, this.vacancy).subscribe( data =>{
      this.router.navigate(['vacancy-master']);
    }
    , (error: any) => console.log(error));
  }

}
