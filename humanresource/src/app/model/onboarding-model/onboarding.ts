export class Onboarding {

onboardingId!:number;
candidateUniqueNumber!:string;
 candidateFullName!:String;
 experienceLevel!:String;
 allMarkSheets:Boolean = false;
 residenceProof:Boolean = false;
 photoIdProof:Boolean = false;
 passportPhotograph:Boolean = false;
 relievingLetterCurrentOrg:Boolean = false;
 lastThreeMonthsPayslips:Boolean = false;
 earlierJobRelievingLetter:Boolean = false;
 canceledCheque:Boolean = false;
 isSubmittedDocument !:String

 

 // Document upload Dates
 allMarkSheetsDate !: Date;
 residenceProofDate !:Date;
 photoIdProofDate !:Date;
 passportPhotographDate !: Date;
 relievingLetterCurrentOrgDate !: Date;
 lastThreeMonthsPayslipsDate !:Date;
 earlierJobRelievingLetterDate !:Date;
 canceledChequeDate !: Date;

 [key: string]: number | String | Date | Boolean;
}
