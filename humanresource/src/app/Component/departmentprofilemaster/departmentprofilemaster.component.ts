import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DepartmentprofileService } from 'src/app/service/department-profile-service/departmentprofile.service';

@Component({
  selector: 'app-departmentprofilemaster',
  templateUrl: './departmentprofilemaster.component.html',
  styleUrls: ['./departmentprofilemaster.component.css']
})
export class DepartmentprofilemasterComponent implements OnInit{
  // departmentprofile: Departmentprofile[];
  pageIndex:1;
  displayedColumns: string[] = ['departmentId','profileId', 'action'];
  dataSource = new MatTableDataSource<any[]>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private departmentprofileService: DepartmentprofileService,private router: Router) {
    this.getDepartmentProfileDetails();
  }
  //get all the list of Department profile details
  private getDepartmentProfileDetails(){
    this.departmentprofileService.getDepartmentProfileList().subscribe(data => {
      this.dataSource = new MatTableDataSource<any[]>(data);
      this.dataSource.paginator = this.paginator;
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
  updateDepartmentProfileDetails(departmentProfileId: number){
    this.router.navigate(['update-department-profile', departmentProfileId]);
  }
  
  
}
