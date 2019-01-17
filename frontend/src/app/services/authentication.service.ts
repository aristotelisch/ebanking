import { AccordionModule } from 'primeng/primeng';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8080';
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(usernameOrEmail: string, password: string) {
    return this.http.post<any>(this.apiUrl + '/api/auth/login', { usernameOrEmail, password })
      .pipe(map(data => {
        // login successful if there's a jwt token in the response
        if (data && data.accessToken) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          const user = new User('', '', usernameOrEmail, usernameOrEmail);
          user.token = data.accessToken;
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);

          return user;
        } else {
          console.log('Could not authenticate', data);
          return data;
        }
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
