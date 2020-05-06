import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AuthenticationModule} from './Authentication/ 	authentication.module'
import {CommonModule, DatePipe,  } from '@angular/common';
import { ListAdminComponent } from './Authentication/list-admin/list-admin.component';
import { httpInterceptorProviders } from './auth-interceptor';
import { SignupComponent } from './Authentication/signup/signup.component';

import {LocationStrategy, HashLocationStrategy} from '@angular/common';




@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthenticationModule,


  ],
  providers: [DatePipe,httpInterceptorProviders],
  exports: [


  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
