import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Subscription} from 'rxjs';
import {ProfileService} from '../../services/profile/profile.service';
import {AuthenticationService} from '../../services/auth/authentication.service';
import {PaymentsService} from '../../services/payments.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.scss']
})
export class PaymentsComponent implements OnInit {
  currentUser: string ;
  private sub: Subscription;
  accounts = [];

  transferForm = this.fb.group({
    email: ['', [Validators.required]],
    amount: ['', [Validators.required, Validators.min(0.01), Validators.max(this.maxAmount())]],
    fromIban: ['', [Validators.required]],
    toIban: ['', [Validators.required]],
    note: [''],
  });
  selectedFromIban: any;

  constructor(
    private profileService: ProfileService,
    private authentication: AuthenticationService,
    private paymentsService: PaymentsService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.profileService.getProfile().subscribe(profile => {
      this.accounts = profile.accounts;
      console.log('email', profile.email);
      this.currentUser = profile.email;
      this.transferForm.controls['email'].setValue( this.currentUser);
    });
  }

  onSubmit() {
    console.log('profileForm.value!:', this.transferForm.value);
    this.paymentsService.createTransfer(this.transferForm.value);
  }

  maxAmount() {
    if (this.selectedFromIban) {
      const selectedAccount = this.accounts.find(account => account.iban === this.selectedFromIban);
      return selectedAccount.balance;
    }
  }

}
