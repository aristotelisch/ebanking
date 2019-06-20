import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { Subscription } from 'rxjs';
import {Profile} from '../../models/profile';
import {ProfileService} from '../../services/profile/profile.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, OnDestroy {

  profile: Profile;
  currentUser: string;
  private sub: Subscription;
  color = 'purple';

  constructor(private authenticationService: AuthenticationService,
              private profileService: ProfileService) {
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  ngOnInit() {
    this.sub = this.authenticationService.currentUserSource$.subscribe(currentUser => {
      this.profileService.getProfile().subscribe(profile => { this.profile = profile; });
      this.currentUser = currentUser;
    });
  }
}
