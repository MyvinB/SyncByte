
import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';

import {SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { ListAdminComponent } from './list-admin/list-admin.component';
import { ListComponent } from './list/list.component';
import { AuthGuard } from '../auth-guard';
import { UserPageComponent } from './user-page/user-page.component';

const authRouter: Routes = [
  {
    path:'',
    children: [
      {
        path: 'signup',
        component: SignupComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'signup/:employeeId',
        component: SignupComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'login',
        component: LoginComponent,


      },
      {
        path:'Adminlist',
        component:ListAdminComponent,
        canActivate: [AuthGuard]
      },
      {
        path:'userPage',
        component:UserPageComponent,
        canActivate: [AuthGuard]
      },
      {
        path:'list/:employeeId',
        component:ListComponent,
        canActivate: [AuthGuard]
      },

    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(authRouter)
  ],
  exports: [
    RouterModule,

  ]
})
export class AuthenticationRouterModule { }
