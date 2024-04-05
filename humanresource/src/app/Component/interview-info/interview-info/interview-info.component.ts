import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Interview } from 'src/app/model/interview-info/interview';
import { InterviewInfoService } from 'src/app/service/interview-info/interview-info.service';

@Component({
  selector: 'app-interview-info',
  templateUrl: './interview-info.component.html',
  styleUrls: ['./interview-info.component.css']
})
export class InterviewInfoComponent implements OnInit {

  interview: Interview = new Interview();
  displayVideolink = true;

  constructor(private interviewInfoService: InterviewInfoService, private router: Router, private snack: MatSnackBar) {


  }
  //validation
  interviewInfomation = new FormGroup({
    modeno: new FormControl('', [Validators.required]),
    videolinkdetails: new FormControl('', [Validators.required]),

  });

  get Mode() {
    return this.interviewInfomation.get("modeno") as FormControl;
  }
  get VideoLink() {
    return this.interviewInfomation.get("videolinkdetails") as FormControl;
  }



  ngOnInit(): void {
  }

  videoLinkDisplay() {

    if ((this.interview.modeNo == 'Video')) {
      this.displayVideolink = true;
    }
    else {
      this.displayVideolink = false;
      // this.interview.videoLinkDetails='';
      // this.interviewInfomation.get('videolinkdetails')?.clearValidators();
    }
  }
  //create the mode information
  saveInterview() {
    this.interviewInfoService.interviewInfo(this.interview).subscribe(data => {
      this.snack.open("Successfully  Created" ,"Ok" )
      this.router.navigate(['interview-master-information']);
    }, error => alert(error));
  }


}
