import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { Departmentprofile } from 'src/app/model/department-profile-model/departmentprofile';
import { Department } from 'src/app/model/departmentround/department';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Profiledetails } from 'src/app/model/profile-details/profiledetails';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { DepartmentprofileService } from 'src/app/service/department-profile-service/departmentprofile.service';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';


@Component({
  selector: 'app-departmentprofile',
  templateUrl: './departmentprofile.component.html',
  styleUrls: ['./departmentprofile.component.css']
})
export class DepartmentprofileComponent implements OnInit{
 
  departmentprofile:Departmentprofile=new Departmentprofile();
  department:Departmentdetails[]
  profile:Profiledetails[]
  status:Status[];
createDepartmentDetails = new FormGroup({
  department: new FormControl('', [Validators.required ]),
  profile: new FormControl('',[Validators.required]),
  active: new FormControl('',[Validators.required]),
 });

 get Department() {
  return this.createDepartmentDetails.get("department") as FormControl;
 }
 
 get Profile() {
  return this.createDepartmentDetails.get("profile") as FormControl;
 }

 get Active() {
  return this.createDepartmentDetails.get("active") as FormControl;
 }

 constructor(private departmentprofileService: DepartmentprofileService,private gc:GlobalBaseUrl, private departmentService: DepartmentDetailsService, private profileService:ProfileServiceService,private router: Router, private snack:MatSnackBar) {
  this.status=gc.status;
  }
  ngOnInit(){
    this.getdepartmentList();
    this.getprofileList();
  }

  getdepartmentList(){
   this.departmentService.getDepartmentList().subscribe(data=>{
    this.department = data;
   });
  }

  getprofileList(){
   this.profileService.getProfileList().subscribe(data=>{
    this.profile = data;
   });
  }

  //create department and profile list
  departmentprofileDetails() {
    this.departmentprofileService.createDepartmentProfile(this.departmentprofile).subscribe(data=>{
      this.snack.open("Successfully  Created", '', {
        duration: 1000,
      });
      this.router.navigate(['department-profile-master']);
      
    },
    error=> alert("Duplicate Department-Profile Mapping"));
  }
  
}