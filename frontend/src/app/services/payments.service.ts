import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationService} from './auth/authentication.service';
import {Transfer} from '../models/transfer';
import {Router} from '@angular/router';
import {MessageService} from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {
  model = 'transactions';
  transfer: Transfer;

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService,
              private router: Router,
              private messageService: MessageService) {}

  transfersUrl() {
    return `${environment.apiEndpoint}${this.model}/`;
  }

  headers() {
    const headers = {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${this.authenticationService.getToken()}`)
    };
    return headers;
  }

  createTransfer(transfer: Transfer) {
    return this.http.post<Transfer>(this.transfersUrl(), transfer, this.headers()).subscribe(
      res => {
        this.transfer = res;
        this.messageService.add({
          severity: 'success', summary: 'Successful transfer', detail: ''
        });

        setTimeout(() => {
          this.messageService.clear();
        }, 3000);

        this.router.navigate(['']);
        console.log('received ok response from post request');
      },
      error => {
        console.error('There was an error during the request');
        console.log(error);
        this.messageService.add({
          severity: 'error', summary: error.error.message, detail: ''
        });

        setTimeout(() => {
          this.messageService.clear();
        }, 3000);
      });

    console.log('request sent. Waiting for response...');
  }
}

