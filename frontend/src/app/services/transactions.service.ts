import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {AuthenticationService} from './auth/authentication.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  model = 'transactions'
  baseUrl = ''

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }


  getTransactionsByUser(userId: string): Observable<any> {
    return this.http.get(this.geturl() + '/' + `${userId}`, this.headers());
  }

  headers() {
    const headers = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${this.authenticationService.getToken()}`)
    };
    return headers;
  }

  geturl() {
    return `${environment.apiEndpoint}${this.model}`;
    // return 'https://demo2601692.mockable.io/auth/profile';
  }

}
