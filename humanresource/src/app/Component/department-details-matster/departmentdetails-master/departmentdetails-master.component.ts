import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Departmentdetails } from 'src/app/model/department-details/departmentdetails';
import { DepartmentDetailsService } from 'src/app/service/department-details-service/department-details.service';


@Component({
  selector: 'app-departmentdetails-master',
  templateUrl: './departmentdetails-master.component.html',
  styleUrls: ['./departmentdetails-master.component.css']
})
export class DepartmentdetailsMasterComponent {

  department:Departmentdetails[];
  pageIndex:1;

  displayedColumns: string[] = ['departmentId', 'department','action'];
  dataSource = new MatTableDataSource<Departmentdetails>();


  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private departmentService: DepartmentDetailsService,private router: Router) {
    this.getDepartmentDetails();
  }
  //get all the list of Department details
  private getDepartmentDetails(){
    this.departmentService.getDepartmentDetailsList().subscribe(data => {
      this.department = data;
      this.dataSource = new MatTableDataSource<Departmentdetails>(this.department);
      this.dataSource.paginator=this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  ngOnInit(){
    setTimeout(() =>this.dataSource.paginator=this.paginator);
    this.dataSource.sort = this.sort;
    
  }
  
  //search 
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  //update the department details details through Id
  updateDepartmentDetails(departmentId: number){
    this.router.navigate(['update-department-details', departmentId]);
  }
  
}
