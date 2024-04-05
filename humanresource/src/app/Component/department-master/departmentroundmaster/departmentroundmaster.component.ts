import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Department } from 'src/app/model/departmentround/department';
import { Roundmodule } from 'src/app/model/round-model/roundmodule';
import { DepartmentService } from 'src/app/service/departmentround/department.service';

@Component({
  selector: 'app-departmentroundmaster',
  templateUrl: './departmentroundmaster.component.html',
  styleUrls: ['./departmentroundmaster.component.css']
})
export class DepartmentroundmasterComponent implements OnInit {

  roundList: Roundmodule[];
  departments: Department = new Department();
  department: Department[];
  selection: any[];
  pageIndex:1;
  displayedColumns: string[] = ['departmentId', 'profileId', 'roundNo', 'interviewLengthMins', 'action'];
  dataSource = new MatTableDataSource<Department>();


  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private departmentService: DepartmentService, private router: Router) {
    this.getDepartmentRoundInfo();
    this.getSelectionCriteriaList();
  }
  //get all the list of Deapartment Information
  private getDepartmentRoundInfo() {
    this.departmentService.getDepartmentRoundInfoList().subscribe(data => {
      this.department = data;
      this.roundListFetch();
      this.dataSource = new MatTableDataSource<Department>(this.department);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }


  private getSelectionCriteriaList() {
    this.departmentService.getSelectionCriteriaList().subscribe(d => {
      this.departmentService.getDepartmentRoundInfoList().subscribe(data => {
        this.department = data;
        this.roundListFetch();
        this.dataSource = new MatTableDataSource<Department>(this.department);
        this.department.forEach((e, i, arr) => {
          e.department = d.filter((ee) => { return e.departmentId == ee.departmentId })[0].department;
          e.profile = d.filter((ee) => { return e.profileId == ee.profileId })[0].profile;
        });
      });
    }, e => { });
  }

  //get the roundlist
  roundListFetch() {
    this.department.forEach((e, i, arr) => {
      e.roundNo = e.round.map<string>((e) => { return e.roundName }).toString();
    });

  }
  //search any field
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  //update the department round information through Id
  updateDepartmentRoundInfo(itemNo: number) {
    this.router.navigate(['update-department-round-details', itemNo]);
  }



  ngOnInit() {
    setTimeout(() => this.dataSource.paginator = this.paginator);
    this.dataSource.sort = this.sort;
  }

}
