import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  model = 'auth/login';
  private _currentUser: string;

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
    this.currentUser = '';
  }

  isAuthenticated(fallback): boolean {
    if (this.getToken() !== 'null' && this.getToken().length != 0) {
      return true;
    } else {
      return false;
    }
  }

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  setUser(currentUser: string) {
    localStorage.setItem('currentUser', currentUser);
    this.currentUser = currentUser;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getUser() {
    return localStorage.getItem('currentUser');
  }

  get currentUser(): string {
    return this._currentUser;
  }

  set currentUser(user) {
    this._currentUser = user;
  }
}
