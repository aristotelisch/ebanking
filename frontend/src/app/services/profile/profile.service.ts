import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Profile} from '../../models/profile';
import {Observable} from 'rxjs';
import {AuthenticationService} from '../auth/authentication.service';
import {Router} from '@angular/router';
import {MessageService} from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  model = 'profiles';
  // profile: Profile;

  constructor(private httpClient: HttpClient,
              private authenticationService: AuthenticationService,
              private router: Router, private messageService: MessageService) {
  }

  geturl() {
    return `${environment.apiEndpoint}${this.model}`;
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
  }

  updateProfile(profile: Profile) {
    return this.httpClient.post<Profile>(this.updateUrl(), profile, this.headers()).subscribe(
        res => {

          this.messageService.add({
            severity: 'success', summary: 'Successful update', detail: ''
          });

          setTimeout(() => {
            this.messageService.clear();
          }, 3000);

          this.router.navigate(['/']);
          console.log('received ok response from patch request');
        },
        error => {
          this.messageService.add({
            severity: 'error', summary: 'Update unsuccessful', detail: ''
          });
          console.error('There was an error during the request');
          console.log(error);
        });

    console.log('request sent. Waiting for response...');
  }
}
