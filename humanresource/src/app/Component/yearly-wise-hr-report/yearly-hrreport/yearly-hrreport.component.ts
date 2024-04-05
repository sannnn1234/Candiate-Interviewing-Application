import { Component, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TooltipPosition } from '@angular/material/tooltip';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';
import { saveAs } from 'file-saver';
import * as XLSX from 'xlsx';
import { EmployeegroupService } from 'src/app/service/employee/employeegroup.service';

@Component({
  selector: 'app-yearly-hrreport',
  templateUrl: './yearly-hrreport.component.html',
  styleUrls: ['./yearly-hrreport.component.css']
})
export class YearlyHRReportComponent {

  year:any;
  hrReport:any[];
  d: Date = new Date();
  financialYear: any[];
  displayedColumns: string[] = ['createdBy', 'candidateFullName', 'interviewerName','roundName', 'roundDate', 'roundStatus'];
  dataSource = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private scheduledInterviewService: ScheduleInterviewService, private empService:EmployeegroupService){
   this.getFinancialYear();
  }


  ngOnInit(){
    setTimeout(() =>this.dataSource.paginator=this.paginator);
    this.dataSource.sort = this.sort;
    
  }

  //financial year
  getFinancialYear(){
    this.scheduledInterviewService.getFinancialYear().subscribe(data => {
      this.financialYear = data;
    })
  }

  fextNext() {
    this.getYearlyHRList();
  }
  
   getYearlyHRList(){
    this.empService.getEmployeeDetailsList().subscribe(d=>{
      this.scheduledInterviewService.getYearBasedHRList(this.year).subscribe(data=>{
        this.hrReport =data;
        this.hrReport.forEach((e, i, arr) => {
          let s = d.filter((ee) => { return e.createdBy == ee.empId })[0];
          if (s) {
            e.createdByHR = s.fullName;
          }
          this.hrReport.forEach((c: { roundDate: string | number | Date; }) => {
            const dateWithoutTime = new Date(c.roundDate).toISOString().split("T")[0];
            c.roundDate = dateWithoutTime;
          });
          this.dataSource = new MatTableDataSource<any>(this.hrReport);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        })

      })
    })
   }

   applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    console.log(this.dataSource.filter);
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

   positionOptions: TooltipPosition[] = ['after', 'before', 'above', 'below', 'left', 'right'];
position = new FormControl(this.positionOptions[0]);

//excel file download
exportToExcel(): void {
  let d = new Date();
  const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.hrReport
    );
   

  const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
  const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
  const excelBlob: Blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8' });

  // Save the file using FileSaver.js
  saveAs(excelBlob, 'Yearly_HR_Report_'+d+'.xlsx');
   }
}
