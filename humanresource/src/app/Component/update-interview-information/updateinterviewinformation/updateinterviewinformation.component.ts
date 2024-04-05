import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Interview } from 'src/app/model/interview-info/interview';
import { InterviewInfoService } from 'src/app/service/interview-info/interview-info.service';

@Component({
  selector: 'app-updateinterviewinformation',
  templateUrl: './updateinterviewinformation.component.html',
  styleUrls: ['./updateinterviewinformation.component.css']
})
export class UpdateinterviewinformationComponent implements OnInit {

  interviewId:number;

  interview: Interview = new Interview();
  constructor(private interviewInfoService: InterviewInfoService, private route: ActivatedRoute,private router: Router){}
  //validation
  updateinterviewInfomation = new FormGroup({
    modeno: new FormControl('', [Validators.required ]),
    videolinkdetails: new FormControl('',[Validators.required]),
    
   });

   get Mode() {
    return this.updateinterviewInfomation.get("modeno") as FormControl;
   }
   get VideoLink() {
    return this.updateinterviewInfomation.get("videolinkdetails") as FormControl;
   }

  ngOnInit(): void {
    this.interviewId = this.route.snapshot.params['interviewId'];
    this.interviewInfoService.getInterviewInformationById(this.interviewId).subscribe(data => {
      this.interview = data;
    }, error => console.log(error));
  }
//update Interview Information
  onSubmit(){
    this.interviewInfoService.updateInterviewInformation(this.interviewId, this.interview).subscribe( data =>{
      this.router.navigate(['interview-master-information']);
    }
    , (error: any) => console.log(error));
  }
}
