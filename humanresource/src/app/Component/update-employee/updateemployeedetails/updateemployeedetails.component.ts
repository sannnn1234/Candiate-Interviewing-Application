import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/model/employee/employee';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Profiledetails } from 'src/app/model/profile-details/profiledetails';
import { EmployeegroupService } from 'src/app/service/employee/employeegroup.service';
import { GroupmasterService } from 'src/app/service/group-master/groupmaster.service';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';

@Component({
  selector: 'app-updateemployeedetails',
  templateUrl: './updateemployeedetails.component.html',
  styleUrls: ['./updateemployeedetails.component.css']
})
export class UpdateemployeedetailsComponent implements OnInit {
  empId:number;
  groupdesc: any;
  employee:Employee=new Employee();
  profileList:Profiledetails[];
  status:Status[];

  //validation 
  employeedetails = new FormGroup({
    empId:new FormControl('', [Validators.required ]),
    fullName: new FormControl('', [Validators.required ]),
    email: new FormControl('', [Validators.required, Validators.email]),
    empPhone: new FormControl('', [Validators.required,  Validators.minLength(10),Validators.pattern("[0-9]*")]),
    password:new FormControl('',[Validators.required]),
    active:new FormControl(''),
    profile: new FormControl('', [Validators.required]),
    groupDescription:new FormControl('',[Validators.required]),
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

  constructor(private employeegroupService:EmployeegroupService, private gc:GlobalBaseUrl,private groupmasterService:GroupmasterService, private route: ActivatedRoute,private profileService: ProfileServiceService,private router: Router) {
    this.status=gc.status;
   }

  ngOnInit(): void {
    this.empId = this.route.snapshot.params['empId'];

    this.employeegroupService.getEmployeeDeatilById(this.empId).subscribe(data => {
      this.employee = data;
      this.employee.profileEmployee = data.profilemapping.map<number>((e)=>{return e.profileId;});
    }, error => console.log(error));

    this.getGroupmaster();
    this.getdepartment();
  }
  private getGroupmaster(){

    this.groupmasterService.getGroupmasterList().subscribe(data=>{
      this.groupdesc = data;
      
    });
  }

  //get all the department from department master
 private getdepartment() {
  this.profileService.getProfileDetailsList().subscribe(data => {
    this.profileList =data;
  });

}

   //update employee details form
   onSubmit(){
    delete this.employee.roleList;
    this.employee.profileId = this.employee.profileEmployee.toString();
    this.employee.profilemapping= this.employee.profileId.toString().split(",").map((Number)).map<Profiledetails>((e) => {
    let d = new Profiledetails();
    d.profileId = e;
    return d;
    });
  
     
    this.employeegroupService.updateEmployeeDeatil(this.empId, this.employee).subscribe( data =>{
      this.router.navigate(['employee-master']);
    }
    , (error: any) => console.log(error));
  }
}
