import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Groupmaster } from 'src/app/model/groupmaster/groupmaster';
import { GroupmasterService } from 'src/app/service/group-master/groupmaster.service';

@Component({
  selector: 'app-groupdetails',
  templateUrl: './groupdetails.component.html',
  styleUrls: ['./groupdetails.component.css']
})
export class GroupdetailsComponent implements OnInit {

  groupmaster:Groupmaster=new Groupmaster();
  status:Status[];
  
  //validation group detail fields
  createGoupmasterDetails = new FormGroup({
    groupDescription: new FormControl('', [Validators.required ]),
    active:new FormControl('',[Validators.required]),
   });

   get GroupDescripion() {
    return this.createGoupmasterDetails.get("groupDescription") as FormControl;
   }

   get Active() {
    return this.createGoupmasterDetails.get("active") as FormControl;
  }

  constructor(private groupmasterService:GroupmasterService,private gc:GlobalBaseUrl, private router: Router, private snack:MatSnackBar) { 
    this.status= gc.status;
  }

  ngOnInit(): void {
  }

  //create group details
  groupDetails(){
    if(this.createGoupmasterDetails.valid){
      this.groupmasterService.registerGroupmasterdetails(this.groupmaster).subscribe(data=>{
        this.snack.open("Successfully  Created", '', {
          duration: 1000,
        });
        this.router.navigate(['group-master']);
        
      },
      error=> alert("Duplicate Group Description"));
    }
  
  } 
}
