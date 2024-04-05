import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';

@Component({
  selector: 'app-update-department-details',
  templateUrl: './update-department-details.component.html',
  styleUrls: ['./update-department-details.component.css']
})
export class UpdateDepartmentDetailsComponent {

departmentId:number;
department:Departmentdetails = new Departmentdetails();
status:Status[];


  updateDepartmentDetails = new FormGroup({
    department: new FormControl('', [Validators.required ]),
    active: new FormControl('', [Validators.required ]),
   });
  
   get Department() {
    return this.updateDepartmentDetails.get("department") as FormControl;
   }

   get Active() {
    return this.updateDepartmentDetails.get("active") as FormControl;
   }

   constructor(private departmentService:DepartmentDetailsService ,private gc:GlobalBaseUrl,private route: ActivatedRoute,private router: Router) { 
    this.status=gc.status;
   }


   //get all the list of department details by department id
  ngOnInit(){
    this.departmentId = this.route.snapshot.params['departmentId'];
    this.departmentService.getDepartmentDeatilById(this.departmentId).subscribe(data => {
      this.department = data;
    }, error => console.log(error));
  }
 
  //update department details form
  updatedepartmentDetails(){
    this.departmentService.updateDepartmentDeatil(this.departmentId, this.department).subscribe( data =>{
      this.router.navigate(['department-details-master']);
    }
    , (error: any) => alert("Duplicate Department"));
  }


}
