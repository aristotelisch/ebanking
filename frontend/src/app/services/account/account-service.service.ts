import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from '../auth/authentication.service';
import { of, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {
  model = 'accounts';

  constructor(
    private httpClient: HttpClient,
    private authenticationService: AuthenticationService
  ) {}

  getAccountsByUser(userId: number): Observable<AccountList> {
    return of( mockAccountList );
  }
}

export class AccountList {
  accounts: [];

  constructor(accounts) {
    this.accounts = accounts;
  }
}

export const mockAccountList = new AccountList([
  {
    'id': 2,
    'balance': 79,
    'iban': 'GR1232133123123312312312321312',
    'description': 'My new savings account',
    'note': '',
    'type': 'SAVINGS',
    'created_at': '2019-06-16T08:19:15.257Z',
    'updated_at': '2019-06-16T08:19:15.257Z',
    'initialBalance': 200
  },
  {
    'id': 3,
    'balance': 777,
    'iban': 'GR1232133123123312312312321312',
    'description': 'My new savings account',
    'note': '',
    'type': 'Checking',
    'created_at': '2019-06-16T08:19:15.257Z',
    'updated_at': '2019-06-16T08:19:15.257Z',
    'initialBalance': 200
  },
]);


export class Account {
  id: number;
  balance: number;
  iban: string;
  description: string;
  note: string;
  type: string;
  created_at: string;
  updated_at: string;
  initialBalance: number;

  constructor(
    id,
    balance,
    iban,
    description,
    note,
    type,
    created_at,
    updated_at,
    initialBalance
  ) {
    this.id = id;
    this.balance = balance;
    this.iban = iban;
    this.description = description;
    this.note = note;
    this.type = type;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.initialBalance = initialBalance;
  }
}
