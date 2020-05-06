import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AuthenticationService } from './ 	authentication.service';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthenticationRouterModule } from './authentication-routing.module';
import { ListAdminComponent } from './list-admin/list-admin.component';
import { ListComponent } from './list/list.component';
import { AuthGuard } from '../auth-guard';
import { UserPageComponent } from './user-page/user-page.component';




@NgModule({
  imports: [
   FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BsDatepickerModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    AuthenticationRouterModule,

  ],
  declarations: [
    SignupComponent,
    LoginComponent,
    ListAdminComponent,
    ListComponent,
    UserPageComponent


  ],
  providers: [AuthenticationService,AuthGuard],
  exports: []
})
export class AuthenticationModule { }
