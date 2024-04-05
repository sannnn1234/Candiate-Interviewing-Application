import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Profiledetails } from 'src/app/model/profile-details/profiledetails';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';

@Component({
  selector: 'app-profile-details-master',
  templateUrl: './profile-details-master.component.html',
  styleUrls: ['./profile-details-master.component.css']
})
export class ProfileDetailsMasterComponent {

  profile:Profiledetails[];
  pageIndex:1;

  displayedColumns: string[] = ['profileId', 'profile','action'];
  dataSource = new MatTableDataSource<Profiledetails>();


  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private profileService: ProfileServiceService,private router: Router) {
    this.getProfileDetails();
  }
  //get all the list of Department details
  private getProfileDetails(){
    this.profileService.getProfileDetailsList().subscribe(data => {
      this.profile = data;
      this.dataSource = new MatTableDataSource<Profiledetails>(this.profile);
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
  updateProfileDetails(profileId: number){
    this.router.navigate(['update-profile-details', profileId]);
  }
}
