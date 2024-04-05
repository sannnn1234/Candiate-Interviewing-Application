import { Component, OnInit, ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Vacancydetails } from 'src/app/model/vacancydetails/vacancydetails';
import { EmployeegroupService } from 'src/app/service/employee/employeegroup.service';
import { VacancyService } from 'src/app/service/vacancy/vacancy.service';


@Component({
  selector: 'app-vacancymaster',
  templateUrl: './vacancymaster.component.html',
  styleUrls: ['./vacancymaster.component.css']
})
export class VacancymasterComponent implements OnInit{
 
  vacancy: Vacancydetails[];
  pageIndex:1;
  displayedColumns: string[] = ['department','profile', 'position','location', 'experience', 'target', 'positionsFilled','year', 'maxSalary', 'createdBy','modifiedBy','action'];
  dataSource = new MatTableDataSource<Vacancydetails>();


  constructor(private vacancyService: VacancyService,private empService:EmployeegroupService,private router: Router) {
    this.getVacancyDetails();
 
  }
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  //get all the list of vacancy details
  private getVacancyDetails(){
    this.empService.getEmployeeDetailsList().subscribe(d=>{
      this.vacancyService.getVacancyDetailsList().subscribe(data => {
        this.vacancy = data;
        this.vacancy.forEach((e, i, arr) => {
          let s = d.filter((ee) => { return e.createdBy == ee.empId })[0];
          let m = d.filter((ee) => { return e.modifiedBy == ee.empId })[0];
          if (s) {
            e.createdByName = s.fullName;
          }
          if(m){
            e.modifiedByName=s.fullName
          }
          
          this.dataSource = new MatTableDataSource<Vacancydetails>(this.vacancy); 
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        })
      })
    });
  }

  ngOnInit(){
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
  
  //search 
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

   
  }

  //update the vacancy details through Id
  updateVacancyDetails(vacancyId: number){
    this.router.navigate(['update-vacancy-details', vacancyId]);
  }
  //delete the vacancy details through Id
  deleteVacancydetails(vacancyId: number){
    this.vacancyService.deleteVacancyDeatil(vacancyId).subscribe( data => {
      this.getVacancyDetails();
    })
  }
  
 
}
