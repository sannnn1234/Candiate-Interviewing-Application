import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Agreementlist } from 'src/app/model/agreement-list/agreementlist';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { AgreementService } from 'src/app/service/agreement-service/agreement.service';

@Component({
  selector: 'app-agreement',
  templateUrl: './agreement.component.html',
  styleUrls: ['./agreement.component.css']
})
export class AgreementComponent {

  agreementlist: Agreementlist = new Agreementlist();
  status:Status[];

  constructor(private agreementService: AgreementService,private gc:GlobalBaseUrl, private router: Router, private snack: MatSnackBar) {
  this.status=gc.status;
  }
  //validation
  agreementInformation = new FormGroup({
  agreement: new FormControl('', [Validators.required]),
  active: new FormControl('', [Validators.required]),
  });

  get Agreement() {
    return this.agreementInformation.get("agreement") as FormControl;
  }
  
  get Active() {
    return this.agreementInformation.get("active") as FormControl;
  }
  
  ngOnInit(): void {
  }

  //create the agreement list
  createAgreement() {
    if(this.agreementInformation.valid){
      this.agreementService.createAgreement(this.agreementlist).subscribe(data => {
        this.snack.open("Successfully  Created", '', {
          duration: 1000,
        });
        this.router.navigate(['agreement-master']);
      }, error => alert("Duplicate Agreement Details"));
    }
  }

}
