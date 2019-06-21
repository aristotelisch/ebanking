import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationService} from '../../services/auth/authentication.service';
import {TransactionsService} from '../../services/transactions.service';
import {Observable, Subscription, timer} from 'rxjs';

@Component({
  selector: 'app-transactions-table',
  templateUrl: './transactions-table.component.html',
  styleUrls: ['./transactions-table.component.scss']
})
export class TransactionsTableComponent implements OnInit, OnDestroy {
  private subscription: Subscription;
  transactions: any = [];
  currentUser;
  every3Second: Observable<number> = timer(0, 3000);

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService,
              private transactionsService: TransactionsService) { }

  ngOnInit() {
    this.currentUser = this.authenticationService.getUser();
    this.subscription = this.every3Second.subscribe((seconds) => {
      this.refreshTransactions(this.currentUser);
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
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
