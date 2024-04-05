import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Agreementlist } from 'src/app/model/agreement-list/agreementlist';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { AgreementService } from 'src/app/service/agreement-service/agreement.service';

@Component({
  selector: 'app-updateagreementlist',
  templateUrl: './updateagreementlist.component.html',
  styleUrls: ['./updateagreementlist.component.css']
})
export class UpdateagreementlistComponent {

  agreementId:number;
  status:Status[];

  agreement: Agreementlist = new Agreementlist();
  constructor(private agreementService: AgreementService,private gc:GlobalBaseUrl, private route: ActivatedRoute,private router: Router){
    this.status=gc.status;
  }
  //validation
  updateAgreementList = new FormGroup({
    agreement: new FormControl('', [Validators.required ]), 
    active: new FormControl('', [Validators.required ]), 
   });

   get Agreement() {
    return this.updateAgreementList.get("agreement") as FormControl;
   }

   get Active() {
    return this.updateAgreementList.get("active") as FormControl;
   }
   

  ngOnInit(): void {
    this.agreementId = this.route.snapshot.params['agreementId'];
    this.agreementService.getAgreementById(this.agreementId).subscribe(data => {
      this.agreement = data;
    }, error => console.log(error));
  }
//update Interview Information
  onSubmit(){
    if(this.updateAgreementList.valid){
    this.agreementService.updateAgreementList(this.agreementId, this.agreement).subscribe( data =>{
      this.router.navigate(['agreement-master']);
    }
    , (error: any) => alert("Duplicate Agreement Details"));
  }
  }
}
