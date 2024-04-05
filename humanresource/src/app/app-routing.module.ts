import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './Component/admin/admin.component';
import { CandidatemasterComponent } from './Component/candidate-master/candidatemaster/candidatemaster.component';
import { CandidateProfileComponent } from './Component/candidate-profile/candidate-profile.component';
import { ChangepasswordComponent } from './Component/change-password/changepassword/changepassword.component';
import { DepartmentroundmasterComponent } from './Component/department-master/departmentroundmaster/departmentroundmaster.component';
import { DepartmentRoundInformationComponent } from './Component/department-round-information/department-round-information.component';
import { DepartmentprofileComponent } from './Component/departmentprofile/departmentprofile.component';
import { DepartmentprofilemasterComponent } from './Component/departmentprofilemaster/departmentprofilemaster.component';
import { EmployeemasterComponent } from './Component/employee-admin/employeemaster/employeemaster.component';
import { ForgetpasswordComponent } from './Component/forget-password/forgetpassword/forgetpassword.component';
import { GroupdetailsComponent } from './Component/group-details/groupdetails/groupdetails.component';
import { GroupmasterinfoComponent } from './Component/group-master/groupmasterinfo/groupmasterinfo.component';
import { HomeComponent } from './Component/home/home.component';
import { InterviewInfoComponent } from './Component/interview-info/interview-info/interview-info.component';
import { InterviewmasterComponent } from './Component/interview-master-information/interviewmaster/interviewmaster.component';
import { LoginComponent } from './Component/Login/login/login.component';
import { OtpComponent } from './Component/otp-forgetpassword/otp/otp.component';
import { ScheduledinterviewComponent } from './Component/scheduled candidate interview  status/scheduledinterview/scheduledinterview.component';
import { UpdatecandidatedetailsComponent } from './Component/update-candidate-details/updatecandidatedetails/updatecandidatedetails.component';
import { UpdatedepartmentprofileComponent } from './Component/update-department-profile/updatedepartmentprofile/updatedepartmentprofile.component';
import { UpdatedepartmentroundComponent } from './Component/update-department-round-details/updatedepartmentround/updatedepartmentround.component';
import { UpdateemployeedetailsComponent } from './Component/update-employee/updateemployeedetails/updateemployeedetails.component';
import { UpdategroupmasterComponent } from './Component/update-groupmaster/updategroupmaster/updategroupmaster.component';
import { UpdateinterviewinformationComponent } from './Component/update-interview-information/updateinterviewinformation/updateinterviewinformation.component';
import { UpdatevacancydetailsComponent } from './Component/update-vacancy-details/updatevacancydetails/updatevacancydetails.component';
import { UpdatescheduleinterviewComponent } from './Component/updatescheduleinterview/updatescheduleinterview/updatescheduleinterview.component';
import { VacancyDetailsComponent } from './Component/vacancy-details/vacancy-details.component';
import { VacancymasterComponent } from './Component/vacancy-master/vacancymaster/vacancymaster.component';
import { AuthGuard } from './service/auth g/auth.guard';
import { UpdatecandidateinterviewComponent } from './Component/update-next-round-candidate-interview-status/updatecandidateinterview/updatecandidateinterview.component';
import { UpdateProfileDetailsComponent } from './Component/update-profile-details/update-profile-details/update-profile-details.component';
import { ProfileDetailsMasterComponent } from './Component/profile-master/profile-details-master/profile-details-master.component';
import { ProfileDetailsComponent } from './Component/profile-details/profile-details/profile-details.component';
import { UpdateDepartmentDetailsComponent } from './Component/update-department-details-master/update-department-details/update-department-details.component';
import { DepartmentdetailsMasterComponent } from './Component/department-details-matster/departmentdetails-master/departmentdetails-master.component';
import { DepartmentDetailsComponent } from './Component/department-details/department-details/department-details.component';
import { OnboardingcandidateComponent } from './Component/onboarding-candidate/onboardingcandidate/onboardingcandidate.component';
import { OnboardingmasterComponent } from './Component/onboarding-master/onboardingmaster/onboardingmaster.component';
import { UpdateOnboardingComponent } from './Component/update-onboarding/update-onboarding/update-onboarding.component';
import { FeedbackmasterComponent } from './Component/feedback-master/feedbackmaster/feedbackmaster.component';
import { HrReportComponent } from './Component/hr-report/hr-report/hr-report.component';
import { MonthlyHrRoundstatusReportComponent } from './Component/monthlywise-hr-roundstatus-report/monthly-hr-roundstatus-report/monthly-hr-roundstatus-report.component';
import { YearlyHRReportComponent } from './Component/yearly-wise-hr-report/yearly-hrreport/yearly-hrreport.component';
import { AgreementComponent } from './Component/agreement-create/agreement/agreement.component';
import { AgreementmasterComponent } from './Component/agreement-master/agreementmaster/agreementmaster.component';
import { UpdateagreementlistComponent } from './Component/agreementlist-update/updateagreementlist/updateagreementlist.component';



const routes: Routes = [
{path:"home",component:HomeComponent},
{path:"login",component:LoginComponent},
{path:"",redirectTo:'login', pathMatch:'full'},
{path:"forget-password",component:ForgetpasswordComponent},
{path:"otp",component:OtpComponent},
{path:"change-password",component:ChangepasswordComponent},
{path:"candidate-profile",component:CandidateProfileComponent},
{path:"candidate-master",component:CandidatemasterComponent},
{path:"update-candidate-details/:candidateNo", component:UpdatecandidatedetailsComponent},
{path:"interview-info",component:InterviewInfoComponent,canActivate:[AuthGuard]},
{path:"interview-master-information",component:InterviewmasterComponent},
{path:"update-interview-information/:interviewId",component:UpdateinterviewinformationComponent,canActivate:[AuthGuard]},
{path:"department-master",component:DepartmentroundmasterComponent},
{path:"update-department-round-details/:itemNo",component:UpdatedepartmentroundComponent},
{path:"department-round-information",component:DepartmentRoundInformationComponent},
{path:"vacancy-master",component:VacancymasterComponent},
{path:"vacancy-details",component:VacancyDetailsComponent,canActivate:[AuthGuard]},
{path:"update-vacancy-details/:vacancyId", component:UpdatevacancydetailsComponent,canActivate:[AuthGuard]},
{path:"group-details",component:GroupdetailsComponent, canActivate:[AuthGuard]},
{path:"group-master",component:GroupmasterinfoComponent, canActivate:[AuthGuard]},
{path:"update-group-details/:groupCode",component:UpdategroupmasterComponent,canActivate:[AuthGuard]},
{path:"employee-details",component:AdminComponent,canActivate:[AuthGuard]},
{path:"employee-master",component:EmployeemasterComponent,canActivate:[AuthGuard]},
{path:"update-employee-details/:empId",component:UpdateemployeedetailsComponent,canActivate:[AuthGuard]},
{path:"scheduled-interview",component:ScheduledinterviewComponent},
{path:"update-schedule-interview/:scheduledInterviewId",component:UpdatescheduleinterviewComponent},
{path:"update-next-round-schedule-interview/:scheduledInterviewId",component:UpdatecandidateinterviewComponent},
{path:"department-profile-details",component:DepartmentprofileComponent, canActivate:[AuthGuard]},
{path:"department-profile-master",component:DepartmentprofilemasterComponent},
{path:"update-department-profile/:departmentProfileId",component:UpdatedepartmentprofileComponent,canActivate:[AuthGuard]},
{path:"department-details",component:DepartmentDetailsComponent, canActivate:[AuthGuard]},
{path:"department-details-master",component:DepartmentdetailsMasterComponent},
{path:"update-department-details/:departmentId",component:UpdateDepartmentDetailsComponent,canActivate:[AuthGuard]},
{path:"profile-details",component:ProfileDetailsComponent, canActivate:[AuthGuard]},
{path:"profile-master",component:ProfileDetailsMasterComponent},
{path:"update-profile-details/:profileId",component:UpdateProfileDetailsComponent,canActivate:[AuthGuard]},
{path:"onboarding",component:OnboardingcandidateComponent},
{path:"onboarding-master",component:OnboardingmasterComponent},
{path:"update-onboarding/:onboardingId",component:UpdateOnboardingComponent},
{path:"feedback-master",component:FeedbackmasterComponent},
{path:"hr-report",component:HrReportComponent},
{path:"monthly-hr-roundstatus-report",component:MonthlyHrRoundstatusReportComponent},
{path:"yearly-hr-report",component:YearlyHRReportComponent},
{path:"agreement",component:AgreementComponent,canActivate:[AuthGuard]},
{path:"agreement-master",component:AgreementmasterComponent},
{path:"update-agreement-list/:agreementId",component:UpdateagreementlistComponent,canActivate:[AuthGuard]},




];

@NgModule({
  // imports: [RouterModule.forRoot(routes, { useHash: true })],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
