import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../ 	authentication.service';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Employee } from '../employee.model';
import Swal from 'sweetalert2/dist/sweetalert2.js';

@Component({
  selector: 'app-list-admin',
  templateUrl: './list-admin.component.html',
  styleUrls: ['./list-admin.component.css']
})
export class ListAdminComponent implements OnInit {
  employeeId:String;
  public csvFileName = `test.csv`;
  employeeList: Employee[]=[];
  trueTime:[]
  errorMessage = '';


  constructor(public authService :AuthenticationService,private router:Router) { }

  ngOnInit() {
    this.authService.getAdminList().subscribe(
      (data:[])=>{
        console.log(data);
        this.authService.postsUpdated.next([...data]);

      }
    );

    this.authService.getPostUpdatedlistener().subscribe((EmployeeList:Employee[])=>{

      this.employeeList=EmployeeList;

    });


  }




  getCSVDownloadLink() {

    return this.authService.generateCSVDownloadLink({
      filename: this.csvFileName,
      data: this.employeeList,
      columns: [
        'employeeId',
        'empName',
        'dateOfBirth'
      ],
    });
  }








  delete(employeeId,index){

      this.authService.deleteEmployee(employeeId).subscribe(
        data=>{
          console.log(data);
          this.employeeList.splice(index,1);
          Swal.fire({
            icon: 'success',
            title: 'Successfully Deleted User',

          })
        },
        error=>{
          console.log(error);
            this.errorMessage=error.error;
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Could not Delete',
          })
        }
      )
  }

}
