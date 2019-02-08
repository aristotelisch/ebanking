import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {AuthenticationService} from '../../services/auth/authentication.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ProfileService} from '../../services/profile/profile.service';
import {Profile} from '../../models/profile';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  profile: Profile;

  profileForm = this.fb.group({
    firstName: ['', [Validators.required]],
    lastName: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    userName: ['', [Validators.required]],
    phone: ['', [Validators.required]],
    mobile: ['', ],
    address: ['', [Validators.required]],
  });

  currentUser: string ;
  private sub: Subscription;

  constructor(
    private profileService: ProfileService,
    private authentication: AuthenticationService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.sub = this.authentication.currentUserSource$.subscribe(currentUser => {
      this.currentUser = currentUser;
      this.profile = this.profileService.getProfile();
      this.profileForm.controls['email'].setValue(this.profile.email);
      this.profileForm.controls['userName'].setValue(this.profile.userName);
      this.profileForm.controls['firstName'].setValue(this.profile.firstName);
      this.profileForm.controls['lastName'].setValue(this.profile.lastName);
      this.profileForm.controls['phone'].setValue(this.profile.phone);
      this.profileForm.controls['mobile'].setValue(this.profile.mobile);
      this.profileForm.controls['address'].setValue(this.profile.address);
    });
  }

  onSubmit() {
    console.log('profileForm.value:', this.profileForm.value);
    this.profileService.updateProfile(this.profileForm.value);
  }

}
