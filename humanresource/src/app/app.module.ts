import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent} from './app.component';
import { HeaderComponent } from './Component/header/header.component';
import { HomeComponent } from './Component/home/home.component';
import { CandidateProfileComponent } from './Component/candidate-profile/candidate-profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule} from '@angular/material/button';
import { MatCardModule} from '@angular/material/card';
import { MatExpansionModule} from '@angular/material/expansion';
import { MatIconModule} from '@angular/material/icon';
import { FooterComponent } from './Component/footer/footer.component';
import {MatSelectModule} from '@angular/material/select';
import { DepartmentRoundInformationComponent } from './Component/department-round-information/department-round-information.component';
import { VacancyDetailsComponent } from './Component/vacancy-details/vacancy-details.component';
import { InterviewInfoComponent } from './Component/interview-info/interview-info/interview-info.component';
import { VacancymasterComponent } from './Component/vacancy-master/vacancymaster/vacancymaster.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import { UpdatevacancydetailsComponent } from './Component/update-vacancy-details/updatevacancydetails/updatevacancydetails.component';
import { CandidatemasterComponent } from './Component/candidate-master/candidatemaster/candidatemaster.component';
import { UpdatecandidatedetailsComponent } from './Component/update-candidate-details/updatecandidatedetails/updatecandidatedetails.component';
import {MatSnackBar,MatSnackBarModule} from '@angular/material/snack-bar';
import { DepartmentroundmasterComponent } from './Component/department-master/departmentroundmaster/departmentroundmaster.component';
import { UpdatedepartmentroundComponent } from './Component/update-department-round-details/updatedepartmentround/updatedepartmentround.component';
import { InterviewmasterComponent } from './Component/interview-master-information/interviewmaster/interviewmaster.component';
import { UpdateinterviewinformationComponent } from './Component/update-interview-information/updateinterviewinformation/updateinterviewinformation.component';
import {MatDialogModule} from '@angular/material/dialog';
import { MatSortModule } from '@angular/material/sort';
import { LoginComponent } from './Component/Login/login/login.component';
import { MatdialogComponent } from './Component/matdialog/matdialog.component';
import { ForgetpasswordComponent } from './Component/forget-password/forgetpassword/forgetpassword.component';
import { OtpComponent } from './Component/otp-forgetpassword/otp/otp.component';
import { ChangepasswordComponent } from './Component/change-password/changepassword/changepassword.component';
import { AdminComponent } from './Component/admin/admin.component';
import { GroupdetailsComponent } from './Component/group-details/groupdetails/groupdetails.component';
import { GroupmasterinfoComponent } from './Component/group-master/groupmasterinfo/groupmasterinfo.component';
import { UpdategroupmasterComponent } from './Component/update-groupmaster/updategroupmaster/updategroupmaster.component';
import { EmployeemasterComponent } from './Component/employee-admin/employeemaster/employeemaster.component';
import { UpdateemployeedetailsComponent } from './Component/update-employee/updateemployeedetails/updateemployeedetails.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { authInterceptorProviders } from './service/auth-interceptor.service';
import {MatMenuModule} from '@angular/material/menu';
import { ScheduledinterviewComponent } from './Component/scheduled candidate interview  status/scheduledinterview/scheduledinterview.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule } from '@angular/material/core';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { UpdatescheduleinterviewComponent } from './Component/updatescheduleinterview/updatescheduleinterview/updatescheduleinterview.component';
import { DepartmentprofileComponent } from './Component/departmentprofile/departmentprofile.component';
import { DepartmentprofilemasterComponent } from './Component/departmentprofilemaster/departmentprofilemaster.component';
import { UpdatedepartmentprofileComponent } from './Component/update-department-profile/updatedepartmentprofile/updatedepartmentprofile.component';
import { MatdialogrejectionComponent } from './Component/matdialogrejection/matdialogrejection.component';
import { DxSchedulerModule } from 'devextreme-angular';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatdialogselectionComponent } from './Component/matdialogselection/matdialogselection.component';
import { UpdatecandidateinterviewComponent } from './Component/update-next-round-candidate-interview-status/updatecandidateinterview/updatecandidateinterview.component';
import { DepartmentdetailsMasterComponent } from './Component/department-details-matster/departmentdetails-master/departmentdetails-master.component';
import { DepartmentDetailsComponent } from './Component/department-details/department-details/department-details.component';
import { UpdateDepartmentDetailsComponent } from './Component/update-department-details-master/update-department-details/update-department-details.component';
import { ProfileDetailsMasterComponent } from './Component/profile-master/profile-details-master/profile-details-master.component';
import { ProfileDetailsComponent } from './Component/profile-details/profile-details/profile-details.component';
import { UpdateProfileDetailsComponent } from './Component/update-profile-details/update-profile-details/update-profile-details.component';
import { MatfinalselectionComponent } from './Component/matdialog-final-selection/matfinalselection/matfinalselection.component';
import { OnboardingmasterComponent } from './Component/onboarding-master/onboardingmaster/onboardingmaster.component';
import { OnboardingcandidateComponent } from './Component/onboarding-candidate/onboardingcandidate/onboardingcandidate.component';
import { UpdateOnboardingComponent } from './Component/update-onboarding/update-onboarding/update-onboarding.component';
import {MatToolbarModule}  from '@angular/material/toolbar';
import {MatTooltipModule}  from '@angular/material/tooltip';
import { MatSidenavModule}  from '@angular/material/sidenav';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { MatdialogfeedbackComponent } from './Component/mat-dialog-feedback/matdialogfeedback/matdialogfeedback.component';
import { FeedbackmasterComponent } from './Component/feedback-master/feedbackmaster/feedbackmaster.component';
import { MatupadtejoiningdateComponentComponent } from './Component/mat-joining-date/matupadtejoiningdate-component/matupadtejoiningdate-component.component';
import { HrReportComponent } from './Component/hr-report/hr-report/hr-report.component';
import { NgApexchartsModule } from "ng-apexcharts";
import { MonthlyHrRoundstatusReportComponent } from './Component/monthlywise-hr-roundstatus-report/monthly-hr-roundstatus-report/monthly-hr-roundstatus-report.component';
import { MonthList } from './model/month/month-list';
import { YearlyHRReportComponent } from './Component/yearly-wise-hr-report/yearly-hrreport/yearly-hrreport.component';
import { GlobalBaseUrl } from './model/global-baseUrl/global-base-url';
import { OnholdCandidateComponent } from './Component/matdialog-on-hold/onhold-candidate/onhold-candidate.component';
import { MatfeedbacklistComponent } from './Component/mat-feedback-list/matfeedbacklist/matfeedbacklist.component';
import { AgreementComponent } from './Component/agreement-create/agreement/agreement.component';
import { AgreementmasterComponent } from './Component/agreement-master/agreementmaster/agreementmaster.component';
import { UpdateagreementlistComponent } from './Component/agreementlist-update/updateagreementlist/updateagreementlist.component';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    CandidateProfileComponent,
    FooterComponent,
    DepartmentRoundInformationComponent,
    VacancyDetailsComponent,
    InterviewInfoComponent,
    VacancymasterComponent,
    UpdatevacancydetailsComponent,
    CandidatemasterComponent,
    UpdatecandidatedetailsComponent,
    DepartmentroundmasterComponent,
    UpdatedepartmentroundComponent,
    InterviewmasterComponent,
    UpdateinterviewinformationComponent,
    LoginComponent,
    MatdialogComponent,
    ForgetpasswordComponent,
    OtpComponent,
    ChangepasswordComponent,
    AdminComponent,
    GroupdetailsComponent,
    GroupmasterinfoComponent,
    UpdategroupmasterComponent,
    EmployeemasterComponent,
    UpdateemployeedetailsComponent,
    ScheduledinterviewComponent,
    UpdatescheduleinterviewComponent,
    DepartmentprofileComponent,
    DepartmentprofilemasterComponent,
    UpdatedepartmentprofileComponent,
    MatdialogrejectionComponent,
    MatdialogselectionComponent,
    UpdatecandidateinterviewComponent,
    DepartmentdetailsMasterComponent,
    DepartmentDetailsComponent,
    UpdateDepartmentDetailsComponent,
    ProfileDetailsMasterComponent,
    ProfileDetailsComponent,
    UpdateProfileDetailsComponent,
    MatfinalselectionComponent,
    OnboardingmasterComponent,
    OnboardingcandidateComponent,
    UpdateOnboardingComponent,
    MatdialogfeedbackComponent,
    FeedbackmasterComponent,
    MatupadtejoiningdateComponentComponent,
    HrReportComponent,
    MonthlyHrRoundstatusReportComponent,
    YearlyHRReportComponent,
    OnholdCandidateComponent,
    MatfeedbacklistComponent,
    AgreementComponent,
    AgreementmasterComponent,
    UpdateagreementlistComponent,
    
  
   

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatSelectModule,
    MatCardModule,
    MatExpansionModule,
    MatIconModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatMenuModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatAutocompleteModule,
    MatToolbarModule,
    MatTooltipModule,
    MatSidenavModule,
    NgbModule,
    DxSchedulerModule,
    NgApexchartsModule,
    NgxMaterialTimepickerModule
   

    
  
    

  ],
  providers: [authInterceptorProviders,MatSnackBar,MonthList, GlobalBaseUrl,MatPaginatorModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
