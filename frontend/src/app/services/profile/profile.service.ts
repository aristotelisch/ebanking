import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Profile} from '../../models/profile';
import {mock_profile} from '../../models/mock_profile';
import {Observable} from 'rxjs';
import {of} from 'rxjs/internal/observable/of';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  model = 'auth/profile';
  // profile: Profile;

  constructor(private httpClient: HttpClient) {
    // this.profile = this.getProfile();
  }

  geturl() {
    // return `${environment.apiEndpoint}${this.model}`;
    return 'https://demo2601692.mockable.io/auth/profile';
  }

  getProfile(): any {
    return this.httpClient.get(this.geturl());
    // return of(mock_profile);
  }

  updateProfile(profile: Profile) {
    mock_profile.address = profile.address;
    mock_profile.firstName = profile.firstName;
    mock_profile.lastName = profile.lastName;
    mock_profile.mobile = profile.mobile;
    mock_profile.phone = profile.phone;
    // email and username are readonly
  }
}
