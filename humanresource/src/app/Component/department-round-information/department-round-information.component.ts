import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { Departmentprofile } from 'src/app/model/department-profile-model/departmentprofile';
import { Department } from 'src/app/model/departmentround/department';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { DepartmentprofileService } from 'src/app/service/department-profile-service/departmentprofile.service';
import { DepartmentService } from 'src/app/service/departmentround/department.service';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';



@Component({
  selector: 'app-department-round-information',
  templateUrl: './department-round-information.component.html',
  styleUrls: ['./department-round-information.component.css']
})




export class DepartmentRoundInformationComponent implements OnInit {
  formData: any = {};
  departmentlist: Departmentdetails[];
  departmentprofilelist:any[];
  departments: Department = new Department();
  profile: any;
  departmentprofile: any[] = [];
  status:Status[];
  roundList: Roundmodule[];


  //validation 
  profileForm = new FormGroup(
    {
      departmentId: new FormControl("", [Validators.required]),
      profileId: new FormControl("", [Validators.required]),
      roundNo: new FormControl('', [Validators.required]),
      interviewLengthMins: new FormControl("", [Validators.required]),
      active:new FormControl('',[Validators.required]),
    });

  get Department(): FormControl {
    return this.profileForm.get("departmentId") as FormControl;
  }

  get Profile(): FormControl {
    return this.profileForm.get("profileId") as FormControl;
  }

  get RoundNo(): FormControl {
    return this.profileForm.get("roundNo") as FormControl;
  }

  get InterviewLength(): FormControl {
    return this.profileForm.get("interviewLengthMins") as FormControl;
  }

  get Active() {
    return this.profileForm.get("active") as FormControl;
  }


  constructor(private departmentroundService: DepartmentService,private gc:GlobalBaseUrl, private departmentdetailsService: DepartmentDetailsService, private deptprofileService: DepartmentprofileService, private profileService:ProfileServiceService, private router: Router, private snack: MatSnackBar) { 
   this.status=gc.status;
  
  }

  ngOnInit(): void {
    this.getdepartment();
    this.getRoundList();
  }


  //get all the department from department master
  private getdepartment() {
    this.departmentdetailsService.getDepartmentList().subscribe(data => {
      this.departmentlist = data;
    }, error => console.log(error));

  }

    //get all the profile from department master
    // private getProfile(){
    //   this.profileService.getProfileList().subscribe(data => {
    //     this.profile = data;
    //   }, error => console.log(error));
  
    // }
    
  //get all the department from department master
  getdepartmentProfileList(departmentId:any) {
    this.deptprofileService.getDepartmentProfileMapping(departmentId).subscribe(data => {
      this.profile = data;
      console.log(data);
    }, error => console.log(error));

  }

 
  //get all the  round list master
  private getRoundList() {
    this.departmentroundService.getRoundList().subscribe(data => {
      this.roundList = data;
    }, error => console.log(error));

  }

  //  modelChangeFn(w: any) {
  //   let profile: {departmentProfileId:any, departmentId:any; profileId:any}[] = this.departmentprofilelist.filter((e: { departmentId: any, profileId: any }) => {
  //     return e.departmentId == w

  //   });
  
  //   this.departments.profileId = profile[0]['profileId'];
  

  // }

  //submit department round information
  submitInfo() {
    this.departments.roundNo = this.departments.roundNo.toString();
    this.departments.round = this.departments.roundNo.toString().split(",").map((Number)).map<Roundmodule>((e) => {
      let d = new Roundmodule();
      d.roundId = e;
      return d;
    });
 
    this.departmentroundService.registerdepartment(this.departments).subscribe(data => {
      this.snack.open("Successfully  Created", '', {
        duration: 1000,
      });
      this.router.navigate(['department-master']);
    }, error => alert("Duplicate Selection Criteria"));
  }

}


