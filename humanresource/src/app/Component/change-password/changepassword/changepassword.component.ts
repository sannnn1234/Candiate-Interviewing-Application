import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Login } from 'src/app/model/loginmodel/login';
import { ChangepasswordService } from 'src/app/service/change-password/changepassword.service';


@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent implements OnInit {

  login: Login = new Login();
  constructor(private changepasswordService: ChangepasswordService, private router: Router, private snack:MatSnackBar) { }
  
  changePass = new FormGroup({
    password: new FormControl('', [Validators.required]),
    
   });
   get Password() {
    return this.changePass.get("password") as FormControl;
   }

  ngOnInit(): void {
  }
 
  //change password 
  changePassword() {
    const formDt = new FormData();
    formDt.append('password', this.login.password + '');
    formDt.append('otp', localStorage.getItem('otp')+'');
    this.changepasswordService.changePassword(formDt).subscribe(data => {
      this.snack.open("Password Change Successfully", '', {
        duration: 1000,
      });
      this.router.navigate(['login']);
    }, error =>
      console.log(error)
    );
    }
}
