import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Login } from 'src/app/model/loginmodel/login';
import { AuthenticationService } from 'src/app/service/login/authentication.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: Login = new Login();
  passwordShown = false;
  passwordType: string = 'password';
  loginUser = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),

  });
  responsedata: Object;

  get Username() {
    return this.loginUser.get("username") as FormControl;
  }
  get Password() {
    return this.loginUser.get("password") as FormControl;
  }

  constructor(private authenticationService: AuthenticationService, private router: Router, private snack: MatSnackBar) { }

  ngOnInit(): void {

  }

  //[Password Visible]
  passwordVisibility() {
    if (this.passwordShown) {
      this.passwordShown = false;
      this.passwordType = 'password';

    } else {

      this.passwordShown = true;
      this.passwordType = 'text';

    }
  }

  //login user check credential
  userLogin() {
    if (this.login.empId.trim() == '' || this.login.empId == null) {
      this.snack.open('Username is required', '', {
        duration: 1000,
      });
      return;
    }
    if (this.login.password.trim() == '' || this.login.password == null) {
      this.snack.open('Password is required', '', {
        duration: 1000,
      });
      return;
    }

    console.log('Requesting the server to generate a token...');
    // Request the server to generate a token
    this.authenticationService.generateToken(this.login).subscribe(
      (data: any) => {
        console.log('Token generated successfully:', data.token);
  
        console.log('Logging in user...');
        this.authenticationService.loginUser(data.token);
  
        console.log('Fetching current user details...');
        this.authenticationService.getCurrentUser().subscribe(
          (employee: any) => {
            console.log('Current user details received:', employee);
  
            console.log('Setting user and updating menu...');
            this.authenticationService.setUser(employee);
            this.authenticationService.updatemenu.next();
  
            console.log('Login Successful!');
            this.snack.open('Login Successfully', '', {
              duration: 2000,
            });
            this.router.navigate(['home']);
          },
          (error) => {
            console.error('Error getting user details:', error);
          }
        );
      },
      (error) => {
          if (error.error == 'User is disabled') {
            console.log('User In-Active');
            this.snack.open('User In-Active', '', {
              duration: 3000,
            });
          } else if (error.error =='Invalid credentials') {
            console.log('Invalid Credentials');
            this.snack.open('Invalid Credentials', '', {
              duration: 3000,
            });
          }
         else {
          console.error('Login error:', error);
        }
      }
    );
  }


}
