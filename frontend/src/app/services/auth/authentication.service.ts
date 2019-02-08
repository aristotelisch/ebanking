import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  model = 'auth/login';
  private currentUserSource = new BehaviorSubject(null);
  public currentUserSource$ = this.currentUserSource.asObservable();

  public onCurrentUserChange(user) {
    this.currentUserSource.next(user);
  }

  constructor(private http: HttpClient, private router: Router) {
    this.setToken(this.getToken());
    this.setUser(this.getUser());
  }

  getUrl() {
    return `${environment.apiEndpoint}${this.model}`;
  }

  login(usernameOrEmail, password) {
    return this.http.post(this.getUrl(), {usernameOrEmail, password});
  }

  logout() {
    this.setToken('');
    this.setUser('');
    this.onCurrentUserChange('');
  }

  isAuthenticated(fallback?: Function): boolean {
    if (this.getToken() !== 'null' && this.getToken().length !== 0) {
      return true;
    } else {
      if (fallback) {
        fallback();
      }
      return false;
    }
  }

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  setUser(currentUser: string) {
    localStorage.setItem('currentUser', currentUser);
    this.onCurrentUserChange(currentUser);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getUser() {
    return localStorage.getItem('currentUser');
  }
}
