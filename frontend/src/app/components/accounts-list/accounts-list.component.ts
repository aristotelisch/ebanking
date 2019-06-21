import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { HttpClient } from '@angular/common/http';
import {Component, OnDestroy, OnInit} from '@angular/core';
import { AccountServiceService } from 'src/app/services/account/account-service.service';
import {ProfileService} from '../../services/profile/profile.service';
import {Observable, Subscription, timer} from 'rxjs';

@Component({
  selector: 'app-accounts-list',
  templateUrl: './accounts-list.component.html',
  styleUrls: ['./accounts-list.component.scss']
})
export class AccountsListComponent implements OnInit, OnDestroy {
  subscription: Subscription;
  accountList: [];
  userId: number;
  every3Seconds: Observable<number> = timer(0, 3000);

  constructor(
    private http: HttpClient,
    private accountsService: AccountServiceService,
    private profileService: ProfileService,
  ) {}

  ngOnInit() {
    this.subscription = this.every3Seconds.subscribe((seconds) => {
      this.refreshData();
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  refreshData() {
    this.profileService.getProfile().subscribe(profile => {
      this.accountList = profile.accounts;
    });
  }
}
