import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Forget } from 'src/app/model/forget';
import { GlobalBaseUrl } from 'src/app/model/global-baseUrl/global-base-url';


@Injectable({
  providedIn: 'root'
})
export class VerifyotpService {

  baseUrl = this.gc.baseUrl;
  constructor(private httpClient: HttpClient, private gc:GlobalBaseUrl) { }

  otpPass(fd:FormData): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/verify-otp`, fd);
  }
}
