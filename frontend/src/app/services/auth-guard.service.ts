import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import {AuthenticationService} from "./authentication.service";

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(public auth: AuthenticationService, public router: Router) { }

  canActivate(): boolean {
    return this.auth.isAuthenticated(() => {
      return this.router.navigate(['/login']);
    });
  }
}
