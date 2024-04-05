import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Candidate } from 'src/app/model/candidate';
import { CandidateserviceService } from 'src/app/service/candidateservice.service';

@Component({
  selector: 'app-matupadtejoiningdate-component',
  templateUrl: './matupadtejoiningdate-component.component.html',
  styleUrls: ['./matupadtejoiningdate-component.component.css']
})
export class MatupadtejoiningdateComponentComponent {

  candidate: Candidate = new Candidate();
  scheduledInterviewId: number;
  candidateFullName: string;
  candidateUniqueNumber: string;
  joiningFeedback:string;
  displayfeedback= false;


  constructor(private candidateService: CandidateserviceService, private router: Router, private snack: MatSnackBar, @Inject(MAT_DIALOG_DATA) data: any) {
    this.scheduledInterviewId = data["id"];
    this.candidateFullName = data["name"];
    this.candidateUniqueNumber = data["candidateId"];
    this.candidate.joiningDate = data["joiningdate"];
    this.candidate.joiningFeedback = data["joiningfeedback"];
 
    
  


  }
  ngOnInit(): void {
  this.displayFeedback();

  }
  displayFeedback(){ 
    if(this.candidate.joiningFeedback == null){
       this.displayfeedback=true;
    }
    else{
      this.displayfeedback = false;
    }
  }

 
  updateJoiningDate() {
    // console.log(this.candidateUniqueNumber, this.candidate);
    this.candidateService.updateJoiningDate(this.candidateUniqueNumber, this.candidate).subscribe(data => {  
      this.snack.open("Successfully Joining data updated", "Ok");
    });
    location.reload();
  }

}
