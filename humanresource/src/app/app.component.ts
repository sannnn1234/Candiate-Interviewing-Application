import { Component, DoCheck } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './service/login/authentication.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  title = 'humanresource';
  rating: number = 3;
  starCount: number = 5;

  
 constructor(public authenticationService:AuthenticationService, private route:Router){

 }
   
 ngOnInit() {
}
onRatingChanged(rating: number) {
  this.rating = rating;

}

}
