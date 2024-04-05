import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Groupmaster } from 'src/app/model/groupmaster/groupmaster';
import { GroupmasterService } from 'src/app/service/group-master/groupmaster.service';

@Component({
  selector: 'app-groupmasterinfo',
  templateUrl: './groupmasterinfo.component.html',
  styleUrls: ['./groupmasterinfo.component.css']
})
export class GroupmasterinfoComponent implements OnInit {

  groupmaster: Groupmaster[];
  displayedColumns: string[] = ['groupdesc', 'action'];
  dataSource = new MatTableDataSource<Groupmaster>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  constructor(private groupmasterService:GroupmasterService, private router: Router) {
    this.getGroupmasterDetails();
   }
  
   //get all the list of group details
   private getGroupmasterDetails(){
    this.groupmasterService.getGroupmasterDetailsList().subscribe(data => {
      this.groupmaster = data;
      this.dataSource = new MatTableDataSource<Groupmaster>(this.groupmaster);
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
  
  //update the group details through Id
  updateGroupmasterDetails(groupCode: number){
    this.router.navigate(['update-group-details', groupCode]);
  }
  //delete the group details through Id
  deleteroupmasterdetails(groupCode: number){
    this.groupmasterService.deleteGroupmasterDeatil(groupCode).subscribe( data => {
      this.getGroupmasterDetails();
    })
  }

}
