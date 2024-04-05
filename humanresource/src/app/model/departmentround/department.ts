import { Roundmodule } from "../round-model/roundmodule";

export class Department {
   itemNo:number;
   departmentId:number;
   profileId:number;
   department:string;
   profile:string;
   roundNo:string;
   roundName:string;
   interviewLengthMins:string;
   active:string;
   roundNoList:number[]= [];
   round:Roundmodule[]=[];
}
