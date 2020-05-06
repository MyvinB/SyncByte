import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { LoginComponent } from './Authentication/login/login.component';

import { SignupComponent } from './Authentication/signup/signup.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
