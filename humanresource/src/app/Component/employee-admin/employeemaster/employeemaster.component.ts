import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Employee } from 'src/app/model/employee/employee';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';
import { EmployeegroupService } from 'src/app/service/employee/employeegroup.service';

@Component({
  selector: 'app-employeemaster',
  templateUrl: './employeemaster.component.html',
  styleUrls: ['./employeemaster.component.css']
})
export class EmployeemasterComponent implements OnInit {

  employee: Employee[];

  displayedColumns: string[] = ['empId','fullName', 'email', 'groupDescription', 'profileId','action'];
  dataSource = new MatTableDataSource<Employee>();

  @ViewChild(MatPaginator,{static:false} ) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private employeegroupService:EmployeegroupService, private departmentdetailsService:DepartmentDetailsService,private router: Router) { 
    this.getEmployeeDetails();
    
  }

   //get all the list of employee details
   private getEmployeeDetails(){
    this.employeegroupService.getEmployeeDetailsList().subscribe(data => {
      this.employee = data;
      this.profileListFetch();
      this.dataSource = new MatTableDataSource<Employee>(this.employee);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
       });
  }
  ngOnInit(){
    setTimeout(() =>this.dataSource.paginator=this.paginator);
    this.dataSource.sort = this.sort;
   
  }


    //get the roundlist
  profileListFetch() {
    this.employee.forEach((e, i, arr) => {
      e.profileId = e.profilemapping.map<string>((e) => { return e.profile}).toString();
    });

  }

   //search 
   applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

 
  
  
  

  //update the employee details through Id
  updateEmployeeDetails(empId: number){
    this.router.navigate(['update-employee-details', empId]);
  }

}
