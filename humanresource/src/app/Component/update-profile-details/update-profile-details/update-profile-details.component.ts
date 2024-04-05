import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Profiledetails } from 'src/app/model/profile-details/profiledetails';
import { ProfileServiceService } from 'src/app/service/profile-details-service/profile-service.service';

@Component({
  selector: 'app-update-profile-details',
  templateUrl: './update-profile-details.component.html',
  styleUrls: ['./update-profile-details.component.css']
})
export class UpdateProfileDetailsComponent {

  profile:Profiledetails= new Profiledetails();
  profileId:number;
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

   constructor(private profileService: ProfileServiceService,private gc:GlobalBaseUrl,private router: Router, private route: ActivatedRoute,private snack:MatSnackBar) {
    this.status=gc.status;
    }
    //get all the list of profile details by  id
  ngOnInit(): void {

    this.profileId = this.route.snapshot.params['profileId'];
    this.profileService.getProfileDeatilById(this.profileId).subscribe(data => {
      this.profile = data;
    }, error => console.log(error));

    
  }

   //update profile details 
   profileDetails(){
    this.profileService.updateProfileDeatil(this.profileId, this.profile).subscribe( data =>{
      this.router.navigate(['profile-master']);
    }
    , (error: any) => alert("Duplicate Profile"));
  }

}
