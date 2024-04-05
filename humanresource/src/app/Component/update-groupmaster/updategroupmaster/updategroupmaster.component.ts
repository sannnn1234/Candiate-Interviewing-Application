import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GlobalBaseUrl, Status } from 'src/app/model/global-baseUrl/global-base-url';
import { Groupmaster } from 'src/app/model/groupmaster/groupmaster';
import { GroupmasterService } from 'src/app/service/group-master/groupmaster.service';

@Component({
  selector: 'app-updategroupmaster',
  templateUrl: './updategroupmaster.component.html',
  styleUrls: ['./updategroupmaster.component.css']
})
export class UpdategroupmasterComponent implements OnInit {
  groupCode:number;
  status:Status[];
  groupmaster:Groupmaster = new Groupmaster();
  createGoupmasterDetails = new FormGroup({
    groupDescription: new FormControl('', [Validators.required ]),
    active:new FormControl('',[Validators.required]),
   
   });

   get GroupDescripion() {
    return this.createGoupmasterDetails.get("groupDescription") as FormControl;
   }

   get Active() {
    return this.createGoupmasterDetails.get("active") as FormControl;
  }

  constructor(private groupmasterService:GroupmasterService, private gc:GlobalBaseUrl,private route: ActivatedRoute,private router: Router) { 
    this.status= gc.status;
  }

  ngOnInit(): void {
    this.groupCode = this.route.snapshot.params['groupCode'];

    this.groupmasterService.getGroupmasterDeatilById(this.groupCode).subscribe(data => {
      this.groupmaster = data;
    }, error => console.log(error));
  }

  //update vacancy details form
  onSubmit(){
    this.groupmasterService.updateGroupmasterDeatil(this.groupCode, this.groupmaster).subscribe( data =>{
      this.router.navigate(['group-master']);
    }
    , (error: any) => alert("Duplicate Group Description"));
  }

  

}
