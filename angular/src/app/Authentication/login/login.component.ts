import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';

import { TokenstorageService } from '../../tokenstorage.service';
import Swal from 'sweetalert2/dist/sweetalert2.js';

import { Router } from '@angular/router';

import { AuthenticationService } from '../ 	authentication.service';
import { LoginServiceService } from 'src/app/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  submitted:boolean=false ;
  signInForm: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  constructor(private fb:FormBuilder, private tokenStorage: TokenstorageService,private router:Router
    ,private authService:AuthenticationService
    ,private LoginService:LoginServiceService) { }

  ngOnInit() {
    this.signInForm=this.fb.group({
  employeeId:['',[Validators.required,Validators.minLength(2)]],
  password:['',[Validators.required,Validators.minLength(0)]]
  })


}

onSubmit(){
  this.submitted=true;
  console.log(this.signInForm.value);
if(this.signInForm.invalid)
    return;


this.authService.loginUser(this.signInForm.value).subscribe(
  data=>{
    this.LoginService.isLoggedIn=true;

    this.tokenStorage.saveUsername(this.signInForm.value['employeeId']);
    this.tokenStorage.saveToken(data['accessToken']);
    this.tokenStorage.saveAuthorities(data['authorities']);

    this.roles = this.tokenStorage.getAuthorities();


    if(this.roles[0]=="ROLE_ADMIN"){
      console.log(this.roles[0]);
      this.router.navigateByUrl('/Adminlist');

    }else{
      console.log(this.roles[0]);
      Swal.fire({
        icon: 'success',
        title: 'User has been logged in',

      })
      this.router.navigateByUrl('/userPage');

    }





  },
  error => {
        console.log(error);
        this.errorMessage = error.error.message;
        if(error.status == 400)
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Bad Request',

        })

        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: this.errorMessage,

        })


      }
);


}


reloadPage() {
 this.router.navigateByUrl('/news');
}

get f()
{
  return this.signInForm.controls;
}

// signup1(){
//   console.log("signup clicked");
//   this.router.navigate(['signup']);
// }
  }
