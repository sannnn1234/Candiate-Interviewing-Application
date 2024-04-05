import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { Department } from 'src/app/model/departmentround/department';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { DepartmentprofileService } from 'src/app/service/department-profile-service/departmentprofile.service';
import { DepartmentService } from 'src/app/service/departmentround/department.service';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';

@Component({
  selector: 'app-updatedepartmentround',
  templateUrl: './updatedepartmentround.component.html',
  styleUrls: ['./updatedepartmentround.component.css']
})
export class UpdatedepartmentroundComponent implements OnInit {
  itemNo: number;
  departmentlist:Departmentdetails[];
  department: Department = new Department();
  departmentprofile: any[] = [];
  roundList: Roundmodule[];
  profile: any;
  status:Status[];

  constructor(private departmentService: DepartmentService, private gc:GlobalBaseUrl, private departmentprofileService: DepartmentprofileService, private departmentdetailsService:DepartmentDetailsService, private profileService:ProfileServiceService, private route: ActivatedRoute, private router: Router) {
    this.status=gc.status;
   }
  //validation 
  updateDepartmentRound = new FormGroup(
    {
      departmentId: new FormControl("", [Validators.required]),
      profileId: new FormControl("", [Validators.required]),
      roundNo: new FormControl("", [Validators.required]),
      active: new FormControl("", [Validators.required]),
      interviewLengthMins: new FormControl("", [Validators.required])
    });

  get Department(): FormControl {
    return this.updateDepartmentRound.get("departmentId") as FormControl;
  }

  get Profile(): FormControl {
    return this.updateDepartmentRound.get("profileId") as FormControl;
  }

  get RoundNo(): FormControl {
    return this.updateDepartmentRound.get("roundNo") as FormControl;
  }

  get InterviewLength(): FormControl {
    return this.updateDepartmentRound.get("interviewLengthMins") as FormControl;
  }

  get Active() {
    return this.updateDepartmentRound.get("active") as FormControl;
  }



  ngOnInit(): void {
    this.itemNo = this.route.snapshot.params['itemNo'];
    this.departmentService.getDepartmentRoundInfoById(this.itemNo).subscribe(data => {
      this.department = data;
      this.department.roundNoList = data.round.map<number>((e)=>{return e.roundId;});

    }, error => console.log(error));

    this.getProfile();
    this.getdepartment();
    this.getRoundList();
  }

  //get all the  round list master
  private getRoundList() {
    this.departmentService.getRoundList().subscribe(data => {
      this.roundList = data;
    }, error => console.log(error));

  }

  //get all the department from department master
  private getdepartment() {
    this.departmentdetailsService.getDepartmentList().subscribe(data => {
      this.departmentlist= data;
    }, error => console.log(error));

  }

  //get all the profile from department master
  private getProfile() {
    this.profileService.getProfileList().subscribe(data => {
      this.profile = data;
    }, error => console.log(error));
  }



  // update department round information
  onSubmit() {
    this.department.roundNo = this.department.roundNoList.toString();
    this.department.round = this.department.roundNo.toString().split(",").map((Number)).map<Roundmodule>((e) => {
      let d = new Roundmodule();
      d.roundId = e;
      return d;
    });
    this.departmentService.updateDepartmentRoundInfo(this.itemNo, this.department).subscribe(data => {
      this.router.navigate(['department-master']);
    }, (error: any) => alert("Duplicate Selection Criteria"));
  }

}
