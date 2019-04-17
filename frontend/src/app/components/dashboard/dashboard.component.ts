import { Component, OnInit, OnDestroy } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, OnDestroy {

  currentUser: string;
  private sub: Subscription;

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  ngOnInit() {
    this.sub = this.authenticationService.currentUserSource$.subscribe(currentUser => {
      this.currentUser = currentUser;
    })
  }
}
