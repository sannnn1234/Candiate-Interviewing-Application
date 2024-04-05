import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Forget } from 'src/app/model/forget';
import { VerifyotpService } from 'src/app/service/verify-otp/verifyotp.service';


@Component({
  selector: 'app-otp',
  templateUrl: './otp.component.html',
  styleUrls: ['./otp.component.css']
})
export class OtpComponent implements OnInit {

  forget: Forget = new Forget();

  verfifyPassOtp = new FormGroup({
    otp: new FormControl('', [Validators.required]),
  });
  get VerifyOtp() {
    return this.verfifyPassOtp.get("otp") as FormControl;
   }
  constructor(private verifyotpService: VerifyotpService, private router: Router, private snack:MatSnackBar) { }
 
  ngOnInit(): void {
  }
  //verfiy otp
  verifyOTP() {
    const fromDt = new FormData();
    fromDt.append('otp', this.forget.otp + '');
    this.verifyotpService.otpPass(fromDt).subscribe(res => {
      localStorage.setItem('otp',this.forget.otp);
      this.router.navigate(['change-password']);
    }, error =>
    this.snack.open("Otp is not valid",'',{
      duration:1000,})
    );
  }
}
