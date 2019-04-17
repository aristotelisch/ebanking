import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {ProfileComponent} from './components/profile/profile.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {AuthGuardService} from './services/auth/auth-guard.service';
import {HelpComponent} from './components/help/help.component';

const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuardService], pathMatch: 'full'},
  {path: 'login', component: LoginComponent, pathMatch: 'full'},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuardService], pathMatch: 'full'},
  {path: 'help', component: HelpComponent, canActivate: [AuthGuardService], pathMatch: 'full'},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})

export class AppRoutingModule {
}
