import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Program } from 'src/app/model/program-model/program';
import { AuthenticationService } from 'src/app/service/login/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, DoCheck{
displaymenu=false;
employeeName:any;
displayhr=true;
currentrole:any;
displayadmin=false;
programList: Program[] = [];


  
  constructor(private router: Router, private authenticationService: AuthenticationService) { 
   
  }


  ngOnInit(): void {
    this.loadJsFile("src/assets/assets/js/main.js");  
    this.authenticationService.updatemenu.subscribe(res=>{
      this.MenuDisplay();
      this.loadMenu();
    })
    this.MenuDisplay();
    this.loadMenu();
   

  }
  //check for login, forget password, verify-otp, change password
  ngDoCheck(): void {
    if((this.router.url=='/login') || (this.router.url=='/forget-password') || (this.router.url=='/otp') || (this.router.url =='/change-password')){
      this.displaymenu=false;
    }
    else{
      this.displaymenu=true;
    }
   
  }
  
  loadMenu() {
    this.authenticationService.getMenus().subscribe(d => {
      this.programList = d;
    }, e => { })
  }

  //display menu according to roles
  MenuDisplay(){
      this.currentrole = this.authenticationService.getUserRole();
      this.displayhr=this.currentrole=='HR';
      this.displayadmin= (this.currentrole=='Admin'  || this.currentrole=='SuperAdmin');
  }



//logout
 logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('employee');
    this.router.navigate(['login']);
    return true;
   }
 
  loadJsFile(url:any) {  
    let node = document.createElement('script');  
    node.src = url;  
    node.type = 'text/javascript';  
    document.getElementsByTagName('head')[0].appendChild(node);  
  }  
}
