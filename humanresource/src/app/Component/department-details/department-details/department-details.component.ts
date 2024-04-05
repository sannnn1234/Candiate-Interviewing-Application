import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';

@Component({
  selector: 'app-department-details',
  templateUrl: './department-details.component.html',
  styleUrls: ['./department-details.component.css']
})
export class DepartmentDetailsComponent {
  department:Departmentdetails= new Departmentdetails();
  status:Status[];
  createDepartmentDetails = new FormGroup({
    department: new FormControl('', [Validators.required ]),
    active: new FormControl('', [Validators.required ]),
   });
  
   get Department() {
    return this.createDepartmentDetails.get("department") as FormControl;
   }
   
   get Active() {
    return this.createDepartmentDetails.get("active") as FormControl;
   }
   constructor(private departmentService: DepartmentDetailsService, private gc:GlobalBaseUrl,private router: Router, private snack:MatSnackBar) { 
    this.status=gc.status;
   }
  ngOnInit(): void {
    
  }

  departmentDetails() {
    this.departmentService.createDepartmentdetails(this.department).subscribe(data=>{
      this.snack.open("Successfully  Created", '', {
        duration: 1000,
      });
      this.router.navigate(['department-details-master']);
      
    },
    error=> alert("Duplicate Department"));
  }
   
}
