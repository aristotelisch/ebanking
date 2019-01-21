import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import {AuthenticationService} from "./authentication.service";
import {Observable} from "rxjs";

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(public auth: AuthenticationService, public router: Router) { }

  canActivate(): Observable<boolean> {
    console.log('ON AUTH GUARD');
    console.log('isAthenticatedV2()', this.auth.isAuthenticatedV2());
    return this.auth.isAuthenticatedV2();
  }
}
