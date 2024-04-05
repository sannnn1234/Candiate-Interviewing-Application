import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subject, catchError, throwError } from 'rxjs';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';
import { Login } from 'src/app/model/loginmodel/login';
import { Program } from 'src/app/model/program-model/program';


@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  //url
  baseUrl = this.gc.baseUrl;
  // isAutheticated: boolean=false;

  constructor(private httpClient: HttpClient, private router: Router, private gc:GlobalBaseUrl) {

  }

  private _updatemenu = new Subject<void>()
  get updatemenu() {
    return this._updatemenu;
  }
  
  //current user: which is loggedIn
  getCurrentUser() {
    return this.httpClient.get(`${this.baseUrl}/current-user`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error fetching current user details:', error.error);
        return throwError(error);
      })
    );
  }


  // generate token 
  generateToken(login: Login) {
    return this.httpClient.post(`${this.baseUrl}/generate-token`, login).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Error generating token:', error.error);
        return throwError(error);
      })
    );

  }

  //login user: set token in localstorage
  loginUser(token: any) {
    localStorage.setItem('token', token);
    return true;
  }

  //islogin: user is login or not
  isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    }
    else {
      return true;
    }
  }

  //logout: remove token from localstorage
  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('employee');
    this.router.navigate(['login']);
    return true;
  }

  //get token
  getToken() {
    return localStorage.getItem('token');
  }

  //set user
  setUser(employee: any) {
    localStorage.setItem('employee', JSON.stringify(employee));
  }

  //getUser
  getUser() {

    let userStr = localStorage.getItem('employee');
    if (userStr != null) {
      return JSON.parse(userStr);
    }
    else {
      this.logout();
      return null;
    }
  }


  //get user role
  getUserRole() {
    let user = this.getUser();
    if (user == null) {
      return '';
    } else {
      return user.groupDescription;
    }
  }


  getMenus() {
    return this.httpClient.get<Program[]>(`${this.baseUrl}/get-menus`);
  }
}
