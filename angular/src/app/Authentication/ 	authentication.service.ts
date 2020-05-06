import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Signupinfo } from './signupinfo';
import { text } from '@angular/core/src/render3';
import { Subject } from 'rxjs';
import { Employee } from './employee.model';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import * as json2csv from 'json2csv';


@Injectable()
export class AuthenticationService {

  login:boolean
  authServiceEndpoint:string = "http://localhost:8081/api/auth";
  adminEndpoint:string="http://localhost:8081/api/admin";
  token:string;
  postsUpdated =new Subject<Employee[]>();

  getPostUpdatedlistener(){
    return this.postsUpdated.asObservable();
  }

  constructor(private http: HttpClient,private domSanitizer:DomSanitizer) { }

  RegisterUser(signupinfo :Signupinfo) {

    const url = `${this.adminEndpoint}/signup`;
    return this.http.post(url, signupinfo, {responseType: 'text'});
  }

  loginUser(user){

    const url = `${this.authServiceEndpoint}/signin`;
    return this.http.post(url, user);
  }

  logOut(employeeId){
    console.log("checking auth out")

    const url = `${this.authServiceEndpoint}/signout/${employeeId}`;
    return this.http.get(url);
  }

  getEmp(employeeId:String){
    const url = `${this.adminEndpoint}/getEmp/${employeeId}`;
    return this.http.get(url,{responseType: 'text'});
  }

  updateEmp(UpdateEmp,employeeId){
    const url = `${this.adminEndpoint}/updateEmp/${employeeId}`;
    return this.http.put(url,UpdateEmp);
  }

  getList(employeeId){
    const url = `${this.authServiceEndpoint}/getTrueTime/${employeeId}`;
    return this.http.get(url);
  }

  getAdminList(){
    const url = `${this.adminEndpoint}/getList`;
    return this.http.get(url);
  }

  deleteEmployee(employeeId){
    const url = `${this.adminEndpoint}/deleteEmp/${employeeId}`;
    return this.http.delete(url);
  }

  getTrueTime(employeeId){
    const url = `${this.adminEndpoint}/getTrueTime/${employeeId}`;
    return this.http.get(url);
  }

  public generateCSVDownloadLink(options: { filename: string, data: any[], columns: string[] }): SafeUrl {
    const fields = options.columns;
    const opts = { fields, output: options.filename };
    const csv = json2csv.parse(options.data, opts);

    return this.domSanitizer.bypassSecurityTrustUrl('data:text/csv,' + encodeURIComponent(csv));
  }



}
