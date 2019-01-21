import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, OnDestroy {
  public currentUser;
  currentUserSubscription: Subscription;

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    // this.currentUserSubscription.unsubscribe();
  }

  ngOnInit() {
    // this.currentUser = this.authenticationService.currentUser;
  }
}
