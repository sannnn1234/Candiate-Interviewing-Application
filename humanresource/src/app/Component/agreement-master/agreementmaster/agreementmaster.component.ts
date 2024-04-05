import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Agreementlist } from 'src/app/model/agreement-list/agreementlist';
import { AgreementService } from 'src/app/service/agreement-service/agreement.service';

@Component({
  selector: 'app-agreementmaster',
  templateUrl: './agreementmaster.component.html',
  styleUrls: ['./agreementmaster.component.css']
})
export class AgreementmasterComponent {


  agreementList:Agreementlist[];
  agreement:Agreementlist = new Agreementlist();
  pageIndex:1;
 
  displayedColumns: string[] = ['agreement', 'action'];
  dataSource = new MatTableDataSource<Agreementlist>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
 
  
  constructor(private agreementService: AgreementService, private dialog: MatDialog, private router: Router) { 

   this.getAgreementList();
  }
  //get all the list of Agreement
  private getAgreementList(){
    this.agreementService.getAgreemnetList().subscribe(data => {
      this.agreementList = data;
      this.dataSource = new MatTableDataSource<Agreementlist>(this.agreementList);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  ngOnInit() {
    setTimeout(() =>this.dataSource.paginator = this.paginator);
  }

  //search
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
 
 
  //update the agreement list through Id
  updateAgreementList(agreementId: number){
    this.router.navigate(['update-agreement-list', agreementId]);
  }
  
}
