import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  model = 'auth/login';
  isAuthenticated$ = new BehaviorSubject(false);

  constructor(private http: HttpClient, private router: Router) {
    console.log('SAVED TOKEN: ', this.getToken());
    this.setToken(this.getToken());
  }

  getUrl() {
    return `${environment.apiEndpoint}${this.model}`;
  }

  login(usernameOrEmail, password) {
    console.log('usernameOrEmail: ', usernameOrEmail);
    console.log('password:', password);
    return this.http.post(this.getUrl(), {usernameOrEmail, password});
  }

  logout() {
    this.setToken('');
    this.isAuthenticated$.next(false);
  }

  isAuthenticatedV2(): Observable<boolean> {
    if (this.getToken() !== 'null') {
      return of(true);
    } else {
      this.router.navigate(['login']);
      return of(false);
    }
  }

  // TOKEN
  setToken(token: string) {
    localStorage.setItem('token', token);
    console.log('SET TOKEN: token', token);
    console.log('token !== null', token !== 'null');

    this.isAuthenticated$.next(token !== 'null'); // Could be more Robust
  }

  getToken() {
    return localStorage.getItem('token');
  }
}
