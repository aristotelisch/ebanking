import { AuthenticationService } from 'src/app/services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  currentUserValue = null;

  constructor(private authentication: AuthenticationService, private route: Router) { }

  ngOnInit() {

  }

  logout() {
    this.authentication.logout();
    this.currentUserValue = null;
    this.route.navigateByUrl('/login');
  }

}
