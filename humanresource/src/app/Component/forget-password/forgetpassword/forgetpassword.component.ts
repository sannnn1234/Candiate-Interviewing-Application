import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Employee } from 'src/app/model/employee/employee';
import { ForgetpasswordService } from 'src/app/service/forget-password/forgetpassword.service';

@Component({
  selector: 'app-forgetpassword',
  templateUrl: './forgetpassword.component.html',
  styleUrls: ['./forgetpassword.component.css']
})
export class ForgetpasswordComponent implements OnInit {
  employee: Employee = new Employee();
  constructor(private forgetpasswordService: ForgetpasswordService, private snack:MatSnackBar, private router: Router) { }

  ngOnInit(): void {
  }

  //forget padssword and send otp
  forgetpassOtp() {
    const fromDt = new FormData();
    fromDt.append('email', this.employee.email + '');
      this.forgetpasswordService.forgetPass(fromDt).subscribe(res => {
      this.router.navigate(['otp']);
    }, error =>{
      if (error.status == 500) {
        if (error.error == 1) {
          this.snack.open('Multiple users found for the given email', '', {
            duration: 3000,
          });
        } else if (error.error == 2) {
          this.snack.open('An unexpected error occurred.', '', {
            duration: 3000,
          });
        }
    }
    }
    );
  }
}
