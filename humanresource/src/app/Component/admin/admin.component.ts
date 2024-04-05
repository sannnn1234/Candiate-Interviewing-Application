import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DxiSortByGroupSummaryInfoComponent } from 'devextreme-angular/ui/nested';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { Department } from 'src/app/model/departmentround/department';
import { Employee } from 'src/app/model/employee/employee';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Profiledetails } from 'src/app/model/profile-details/profiledetails';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { EmployeegroupService } from 'src/app/service/employee/employeegroup.service';
import { GroupmasterService } from 'src/app/service/group-master/groupmaster.service';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  employee: Employee = new Employee();
  groupdesc: any;
  profileList: Profiledetails[];
  status: Status[];
  isProcess=false;
  //validate employee dewtails form
  employeedetails = new FormGroup({
    empId: new FormControl('', [Validators.required]),
    fullName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    empPhone: new FormControl('', [Validators.required, Validators.maxLength(10), Validators.minLength(10), Validators.pattern("[0-9]*")]),
    password: new FormControl('', [Validators.required]),
    active: new FormControl('', [Validators.required]),
    profile: new FormControl('', [Validators.required]),
    groupDescription: new FormControl('', [Validators.required]),
  });


  get EmployeeID() {
    return this.employeedetails.get("empId") as FormControl;
  }
  get EmployeeFullName() {
    return this.employeedetails.get("fullName") as FormControl;
  }
  get ContactNumber() {
    return this.employeedetails.get("empPhone") as FormControl;
  }
  get EmailAddress() {
    return this.employeedetails.get("email") as FormControl;
  }
  get Password() {
    return this.employeedetails.get("password") as FormControl;
  }
  get Active() {
    return this.employeedetails.get("active") as FormControl;
  }
  get GroupDescription() {
    return this.employeedetails.get("groupDescription") as FormControl;
  }

  get Profile() {
    return this.employeedetails.get("profile") as FormControl;
  }

  constructor(private employeegroupService: EmployeegroupService, private gc: GlobalBaseUrl, private groupmasterService: GroupmasterService, private profileService: ProfileServiceService, private router: Router, private snack: MatSnackBar) {
    this.status = gc.status;
  }

  ngOnInit() {
    this.getGroupmaster();
    this.getProfile();
  }
  //get all list group master
  private getGroupmaster() {

    this.groupmasterService.getGroupmasterList().subscribe(data => {
      this.groupdesc = data;

    });
  }

  //get all the department from department master
  private getProfile() {
    this.profileService.getProfileDetailsList().subscribe(data => {
      this.profileList = data;
    });

  }
  //create employee details
  employeeDetails() {
    try {
      this.isProcess=true;
      this.employee.profileId = this.employee.profileId.toString();
      this.employee.profilemapping = this.employee.profileId.split(",").map((e) => {
        let d = new Profiledetails();
        d.profileId = Number(e);
        return d;
      });

      // Create employee details using employeegroupService
      this.employeegroupService.createEmployeedetails(this.employee).subscribe(
        (data) => {
          this.isProcess=false;
          this.snack.open("Successfully Created", '', { duration: 1000 });
          this.router.navigate(['employee-master']);
        },
        (error) => {
          this.isProcess=false;
          console.error(error);
          this.snack.open("Error creating employee", '', { duration: 2000 });
        }
      );
    } catch (e) {
      // Handle unexpected exceptions
      console.error("An unexpected error occurred", e);
      this.snack.open("An unexpected error occurred", '', { duration: 2000 });
    }
  }
}


