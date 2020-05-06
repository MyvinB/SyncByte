import { Component } from '@angular/core';
import { TokenstorageService } from './tokenstorage.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './Authentication/ 	authentication.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private roles: string[];
   authority: string;
   isLogged:boolean=true;
   userName: string

  constructor(public tokenStorage: TokenstorageService,private router: Router,private authService:AuthenticationService) {

  }

  ngOnInit() {

  }
  logout(){
    console.log("signed out")
    this.userName=this.tokenStorage.getUsername();

    console.log(this.userName)
    this.authService.logOut(this.userName).subscribe();
    this.authService.login=false;
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }

}
