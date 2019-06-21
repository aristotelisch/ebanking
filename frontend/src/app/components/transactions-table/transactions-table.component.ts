import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationService} from '../../services/auth/authentication.service';
import {TransactionsService} from '../../services/transactions.service';

@Component({
  selector: 'app-transactions-table',
  templateUrl: './transactions-table.component.html',
  styleUrls: ['./transactions-table.component.scss']
})
export class TransactionsTableComponent implements OnInit {
  transactions: any = [];

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService,
              private transactionsService: TransactionsService) { }

  ngOnInit() {
    const currentUser = this.authenticationService.getUser();
    this.refreshTransactions(currentUser);

    setInterval(() => {
      this.refreshTransactions(currentUser);
    });
  }

  headers() {
    const headers = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${this.authenticationService.getToken()}`)
    };
    return headers;
  }

  refreshTransactions(currentUser) {
    this.transactionsService.getTransactionsByUser(currentUser).subscribe(transactions => {
      console.log('transactions', transactions);
      this.transactions = transactions.transactions;
    });
  }

}
