import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Interview } from 'src/app/model/interview-info/interview';
import { InterviewInfoService } from 'src/app/service/interview-info/interview-info.service';
import { MatdialogComponent } from '../../matdialog/matdialog.component';


@Component({
  selector: 'app-interviewmaster',
  templateUrl: './interviewmaster.component.html',
  styleUrls: ['./interviewmaster.component.css']
})
export class InterviewmasterComponent implements OnInit {

  videoLinkDetails : string;
  interview:Interview[];
  data:Interview;
 
  displayedColumns: string[] = ['modeNo', 'videoLinkDetails','action'];
  dataSource = new MatTableDataSource<Interview>();

  @ViewChild(MatPaginator,{static:false} ) paginator: MatPaginator;
 
  
  constructor(private interviewInfoService: InterviewInfoService, private dialog: MatDialog, private router: Router) { 

   this.getInterviewInformation();
  }
  //get all the list of Mode Information
  private getInterviewInformation(){
    this.interviewInfoService.getInterviewInformationList().subscribe(data => {
      this.interview = data;
      this.dataSource = new MatTableDataSource<Interview>(this.interview);
     
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
  //dialog box 
  openLink(interviewId: number){
     this.interviewInfoService.getInterviewInformationById(interviewId).subscribe(response=>{
      this.openDialog(response);
     })
     
  }
  openDialog(data: any) {
    const dialogRef = this.dialog.open(MatdialogComponent, {
        data: data
    });
  }
  //update the mode information through Id
  updateInterviewInformation(interviewId: number){
    this.router.navigate(['update-interview-information', interviewId]);
  }

  //delete the record of mode information
  deleteInterviewInformation(interviewId: number){
    this.interviewInfoService.deleteInterviewInformation(interviewId).subscribe( data => {
      this.getInterviewInformation();
    })
  }


  ngOnInit() {
    setTimeout(() =>this.dataSource.paginator = this.paginator);
  }

}
