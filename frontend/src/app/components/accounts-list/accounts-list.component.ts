import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AccountServiceService } from 'src/app/services/account/account-service.service';

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
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    this.accountsService.getAccountsByUser(2).subscribe(accountList => {
      console.log('accounts: ', accountList.accounts);
      this.accountList = accountList.accounts;
    });
  }
}
