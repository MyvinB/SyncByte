import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { Signupinfo } from '../signupinfo';
import Swal from 'sweetalert2/dist/sweetalert2.js';


import { DatePipe } from '@angular/common';
import { AuthenticationService } from '../ 	authentication.service';
import { Router, ActivatedRoute } from '@angular/router';


// const httpOptions = {
//   headers: new HttpHeaders({ 'Content-Type': 'application/json' })
// };
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})




export class SignupComponent implements OnInit {




  submitted:boolean = false;
  registerForm: FormGroup;
  signupInfo: Signupinfo;
  isSignedUp:boolean = false;
  isSignUpFailed:boolean = false;
  errorMessage:string = '';
  role = new Array<String>();
  empName:String;
  employeeId:String
  employeeIdToSend:String
  private signupUrl = 'http://localhost:8081/api/auth/signup';
  constructor(private formBuilder: FormBuilder,
   public datepipe: DatePipe,public authService :AuthenticationService,private router:Router,private route: ActivatedRoute) { }

  ngOnInit() {

    this.route.params.subscribe(
      response => this.employeeIdToSend = response['employeeId']

    );
    console.log(this.employeeIdToSend);

    if(this.employeeIdToSend){
        this.authService.getEmp(this.employeeIdToSend).subscribe(
          data=>{
             this.empName=data;
             this.employeeId=this.employeeIdToSend
          }
        );

    }


    this.registerForm = this.formBuilder.group({

      empCode: ['', [Validators.required, Validators.minLength(2)]],
      name: ['', [Validators.required]],
      date:['',[Validators.required]],
      password: ['', [Validators.required]],
    })
  }
  get f()
  {
    return this.registerForm.controls;
  }

  onSubmit(){




    console.log(this.registerForm.get('date').value);
    this.submitted=true;
    if(this.registerForm.invalid){
      return;
    }
    this.signupInfo=new Signupinfo(
    this.registerForm.get('empCode').value,
    this.registerForm.get('name').value,
    this.registerForm.get('password').value,
    this.datepipe.transform(this.registerForm.get('date').value, 'yyyy-MM-dd')
    );

    console.log(this.signupInfo);
    console.log(this.employeeIdToSend)
      if(this.employeeIdToSend){

        this.authService.updateEmp(this.signupInfo,this.employeeIdToSend).subscribe(
          data=>{
            console.log(data);
            this.authService.postsUpdated.next([this.signupInfo]);
            this.router.navigateByUrl('/Adminlist');
            Swal.fire({
              icon: 'success',
              title: 'User has been Updated',

            })
          },
          error => {
            this.errorMessage = error.error.message;
            console.log(error);

            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: this.errorMessage,

            })



          }
        )






      }else{

        this.authService.RegisterUser(this.signupInfo).subscribe(
          data=>{
              this.isSignedUp=true;
              console.log(data);
              this.authService.postsUpdated.next([this.signupInfo]);
              Swal.fire({

                icon: 'success',
                title: 'User has been Registered',

              })
              this.router.navigateByUrl('/Adminlist');
          },
          error => {
            console.log("hiii");
            this.errorMessage = error.error;
            console.log(error);
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: this.errorMessage,

            })



          }
        );


      }







}

}
