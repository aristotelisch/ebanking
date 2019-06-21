import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AccountServiceService } from 'src/app/services/account/account-service.service';
import {ProfileService} from '../../services/profile/profile.service';

@Component({
  selector: 'app-accounts-list',
  templateUrl: './accounts-list.component.html',
  styleUrls: ['./accounts-list.component.scss']
})
export class AccountsListComponent implements OnInit {
  accountList: [];
  userId: number;

  constructor(
    private http: HttpClient,
    private accountsService: AccountServiceService,
    private profileService: ProfileService,
  ) {}

  ngOnInit() {
    this.refreshData();
    setInterval(() => {
      this.refreshData();
    }, 2000);
  }

  refreshData() {
    this.profileService.getProfile().subscribe(profile => {
      this.accountList = profile.accounts;
    });
  }
}
