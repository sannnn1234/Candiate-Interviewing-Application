import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Profiledetails } from 'src/app/model/profile-details/profiledetails';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';

@Component({
  selector: 'app-profile-details',
  templateUrl: './profile-details.component.html',
  styleUrls: ['./profile-details.component.css']
})
export class ProfileDetailsComponent {

  profile:Profiledetails= new Profiledetails();
  status:Status[];
  
  createProfileDetails = new FormGroup({
    profile: new FormControl('', [Validators.required ]),
    active: new FormControl('', [Validators.required ]),
   });
  
   get Profile() {
    return this.createProfileDetails.get("profile") as FormControl;
   }
   
   get Active() {
    return this.createProfileDetails.get("active") as FormControl;
   }
   
   constructor(private profileService: ProfileServiceService, private gc:GlobalBaseUrl,private router: Router, private snack:MatSnackBar) { 
    this.status = gc.status;
   }
  ngOnInit(): void {
    
  }

  profileDetails() {
    if(this.createProfileDetails.valid){
      this.profileService.createProfiledetails(this.profile).subscribe(data=>{
        this.snack.open("Successfully  Created" ,"Ok" )
        this.router.navigate(['profile-master']);
        
      },
      error=> alert("Duplicate Profile"));
    }
  }
   
}
