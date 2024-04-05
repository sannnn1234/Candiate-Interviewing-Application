import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { Departmentprofile } from 'src/app/model/department-profile-model/departmentprofile';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Profiledetails } from 'src/app/model/profile-details/profiledetails';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { DepartmentprofileService } from 'src/app/service/department-profile-service/departmentprofile.service';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';

@Component({
  selector: 'app-updatedepartmentprofile',
  templateUrl: './updatedepartmentprofile.component.html',
  styleUrls: ['./updatedepartmentprofile.component.css']
})
export class UpdatedepartmentprofileComponent implements OnInit {
  departmentProfileId: number;
  data: any;
  status:Status[];
  departmentprofile: Departmentprofile = new Departmentprofile();
  departmentprofiledetails: Departmentprofile[];
  department: Departmentdetails[];
  profile: Profiledetails[];

  constructor(private departmentprofileService: DepartmentprofileService, private gc:GlobalBaseUrl, private departmentService: DepartmentDetailsService, private profileService: ProfileServiceService, private route: ActivatedRoute, private router: Router) {
    this.status =gc.status;
   }

  //validation of vacancy details form
  updatedepartmentprofile = new FormGroup({
    departmentId: new FormControl('', [Validators.required]),
    profileId: new FormControl('', [Validators.required]),
    active: new FormControl('', [Validators.required]),

  });

  get Department() {
    return this.updatedepartmentprofile.get("departmentId") as FormControl;
  }

  get Profile() {
    return this.updatedepartmentprofile.get("profileId") as FormControl;
  }

  get Active() {
    return this.updatedepartmentprofile.get("active") as FormControl;
  }

  //get all the list of department profile details by vacancy id
  ngOnInit(): void {

    this.departmentProfileId = this.route.snapshot.params['departmentProfileId'];
    this.departmentprofileService.getDepartmentProfileById(this.departmentProfileId).subscribe(data => {
      this.departmentprofile.departmentId =data.departmentId;
      this.departmentprofile.profileId =data.profileId;
      this.departmentprofile.departmentProfileId =data.departmentProfileId;
      this.departmentprofile.active =data.active;
      this.getdepartmentList();
      this.getprofileList();
    }, error => console.log(error));
  }
  getdepartmentList() {
    this.departmentService.getDepartmentList().subscribe(data => {
      this.department = data;
    });
  }

  getprofileList() {
    this.profileService.getProfileList().subscribe(data => {
      this.profile = data;
    });
  }
  //update department profile details form
  onSubmit() {
    
      this.departmentprofileService.updateDepartmentProfileInfo(this.departmentProfileId, this.departmentprofile).subscribe(data => {
        this.router.navigate(['department-profile-master']);
      }
        , (error: any) => alert("Duplicate Department-Profile Mapping"));
  }

}
