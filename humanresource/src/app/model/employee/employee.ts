import { Departmentdetails } from "../department-details/departmentdetails";
import { Profiledetails } from "../profile-details/profiledetails";

export class Employee {
    empId:number;
    fullName:String;
    email:String;
    empPhone:number;
    groupDescription:string;
    active:number;
    password:String;
    profileId:string;
    roleList: any;
    profileEmployee:number[]= [];
    profilemapping:Profiledetails[]=[];
}
