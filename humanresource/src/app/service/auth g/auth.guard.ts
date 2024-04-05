import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';                          
import { AuthenticationService } from '../login/authentication.service';
import { CandidateserviceService } from '../candidateservice.service';
import { Candidate } from 'src/app/model/candidate';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
currentrole:any;
createdCandidate:Candidate[];
  constructor(private authenticationService:AuthenticationService,private candidateService:CandidateserviceService, private router: Router,private snack:MatSnackBar){
  }
  //Authorization to access the model
  canActivate()
     {
      if(this.authenticationService.isLoggedIn()){
        this.currentrole = this.authenticationService.getUserRole();
         if(this.currentrole=='Admin'){
          return true;
         }
         else{
          this.snack.open("you are not authorized to access this module",'Ok');
          return false;
         }
      }else{

        this.router.navigate(['']);
        return false;
     
      }
  }

  
  
}
