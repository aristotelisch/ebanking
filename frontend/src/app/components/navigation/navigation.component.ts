import {AuthenticationService} from 'src/app/services/authentication.service';
import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Subscription} from "rxjs";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit, OnDestroy {

  currentUser: string;
  private sub: Subscription;

  constructor(private authentication: AuthenticationService, private route: Router) {
  }

  ngOnInit() {
    this.sub = this.authentication.currentUserSource$.subscribe(currentUser => {
      this.currentUser = currentUser;
    })
  }


  logout() {
    this.authentication.logout();
    this.route.navigateByUrl('/login');
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
