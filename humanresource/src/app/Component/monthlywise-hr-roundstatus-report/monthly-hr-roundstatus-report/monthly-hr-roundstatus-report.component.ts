import { Component, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TooltipPosition } from '@angular/material/tooltip';
import { MonthList } from 'src/app/model/month/month-list';
import { ScheduledInterview } from 'src/app/model/sheduledinterview/scheduled-interview';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';
import { saveAs } from 'file-saver';
import * as XLSX from 'xlsx';
import { EmployeegroupService } from 'src/app/service/employee/employeegroup.service';


@Component({
  selector: 'app-monthly-hr-roundstatus-report',
  templateUrl: './monthly-hr-roundstatus-report.component.html',
  styleUrls: ['./monthly-hr-roundstatus-report.component.css']
})
export class MonthlyHrRoundstatusReportComponent {
   createdBy:any;
   month:any;
   pageIndex:1;
   roundstatus:any[];
   scheduledinterview:any[];
   months:any[];
   report:any[];
   scheduled:ScheduledInterview = new ScheduledInterview();

   displayedColumns: string[] = ['createdBy', 'candidateFullName', 'interviewerName','roundName', 'roundDate', 'roundStatus'];
   dataSource = new MatTableDataSource<any>();

   @ViewChild(MatPaginator) paginator!: MatPaginator;
   @ViewChild(MatSort) sort!: MatSort;
 
  constructor(private scheduleInterviewService : ScheduleInterviewService,private empService:EmployeegroupService, private monthlist:MonthList){
    // this.getMonthlyHRAndRoundStatusReport();
    this.getMonthList();
    this.getHRList();
    this.getRoundStatus();
  }

  ngOnInit(){
    setTimeout(() =>this.dataSource.paginator=this.paginator);
    this.dataSource.sort = this.sort;
    
  }

  fextNext(){
    this.getMonthlyHRAndRoundStatusReport();
  }

  getMonthList(){
    this.months = this.monthlist.monthList;
  }
  
  getHRList(){
      this.scheduleInterviewService.getHRList().subscribe(data=>{
        this.scheduledinterview= data;
    })
  }

  getRoundStatus(){
    this.scheduleInterviewService.getRoundStatusList().subscribe(data=>{
       this.roundstatus= data;
    });
  }

  //filter the list of monthly round status report
  getMonthlyHRAndRoundStatusReport(){
    if(this.month && this.scheduled.createdBy && this.scheduled.roundStatus){
      this.empService.getEmployeeDetailsList().subscribe(d =>{
        this.scheduleInterviewService.getMonthlyHRAndRoundstatusReport(this.month , this.scheduled.createdBy , this.scheduled.roundStatus).subscribe(data=>{
          this.report =data;
          this.report.forEach((e, i, arr) => {
            let s = d.filter((ee) => { return e.createdBy == ee.empId })[0];
            if (s) {
              e.createdByHR = s.fullName;
            }
            this.report.forEach((c: { roundDate: string | number | Date; }) => {
              const dateWithoutTime = new Date(c.roundDate).toISOString().split("T")[0];
              c.roundDate = dateWithoutTime;
            });
            this.dataSource = new MatTableDataSource<any>(this.report);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sort;
          })
        })
      })
    }

  }

  //search
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


positionOptions: TooltipPosition[] = ['after', 'before', 'above', 'below', 'left', 'right'];
position = new FormControl(this.positionOptions[0]);

//excel file download
exportToExcel(): void {
  let d = new Date();
  const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.report);
   

  const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
  const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
  const excelBlob: Blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8' });

  // Save the file using FileSaver.js
  saveAs(excelBlob, 'Monthly_RoundStatus_Report_'+d+'.xlsx');
   }
}
