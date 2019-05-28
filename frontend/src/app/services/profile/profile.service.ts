import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Profile} from '../../models/profile';
import {mock_profile} from '../../models/mock_profile';
import {Observable} from 'rxjs';
import {of} from 'rxjs/internal/observable/of';
import {AuthenticationService} from '../auth/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  model = 'profiles';
  // profile: Profile;

  constructor(private httpClient: HttpClient, private authenticationService: AuthenticationService) {
    // this.profile = this.getProfile();
  }

  geturl() {
    return `${environment.apiEndpoint}${this.model}`;
    // return 'https://demo2601692.mockable.io/auth/profile';
  }

  updateUrl() {
    return `${environment.apiEndpoint}${this.model}/`;
   }
  headers() {
    const headers = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${this.authenticationService.getToken()}`)
    };
    return headers;
  }
  getProfile(): Observable<any> {
    return this.httpClient.get(this.geturl() + '/' + this.authenticationService.getUser(), this.headers());
    // return of(mock_profile);
  }

  updateProfile(profile: Profile) {
    return this.httpClient.post<Profile>(this.updateUrl(), profile, this.headers()).subscribe(
        res => {
          console.log('received ok response from patch request');
        },
        error => {
          console.error('There was an error during the request');
          console.log(error);
        });

    console.log('request sent. Waiting for response...');
  }
}
