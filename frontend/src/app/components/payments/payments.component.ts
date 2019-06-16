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

  transferForm = this.fb.group({
    email: ['dev@example.com', [Validators.required, Validators.email]],
    amount: ['', [Validators.required]],
    fromIban: ['', [Validators.required]],
    toIban: ['', [Validators.required]],
    note: [''],
  });

  constructor(
    private profileService: ProfileService,
    private authentication: AuthenticationService,
    private paymentsService: PaymentsService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
  }

  onSubmit() {
    console.log('profileForm.value!:', this.transferForm.value);
    this.paymentsService.createTransfer(this.transferForm.value);
  }
}
