import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AuthGuardService as AuthGuard } from './auth/auth-guard.service';
import { AboutMeComponent } from './admin-panel/about-me/about-me.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'about-me', component: AboutMeComponent },

  {
    path: 'admin-panel', 
    component: AdminPanelComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'about-me',
        component: AboutMeComponent
      }
    ]
  },

  { path: '*', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
