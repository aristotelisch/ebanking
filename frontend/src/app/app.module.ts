import { MessageService } from 'primeng/components/common/messageservice';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { NavigationComponent } from './components/shared/navigation/navigation.component';
import { NotificationComponent } from './components/notification/notification.component';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';
import { AuthGuardService } from './services/auth/auth-guard.service';
import { ProfileComponent } from './components/profile/profile.component';
import { HelpComponent } from './components/help/help.component';
import { AccordionModule } from 'primeng/primeng';
import { ProfileService } from './services/profile/profile.service';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { AccountsListComponent } from './components/accounts-list/accounts-list.component';
import { AccountDetailsComponent } from './components/account-details/account-details.component';
import {
  MatButtonModule,
  MatCard,
  MatCardModule,
  MatCardTitle,
  MatCheckboxModule,
  MatFormFieldModule,
  MatInputModule,
  MatPaginatorModule,
  MatProgressSpinnerModule, MatRippleModule, MatSelectModule,
  MatSortModule,
  MatTableModule
} from '@angular/material';
import {TransactionsTableComponent} from './components/transactions-table/transactions-table.component';
import { PaymentsComponent } from './components/payments/payments.component';
import {CurrencyMaskModule} from 'ng2-currency-mask';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    NavigationComponent,
    NotificationComponent,
    ProfileComponent,
    HelpComponent,
    TransactionsComponent,
    AccountsListComponent,
    AccountDetailsComponent,
    TransactionsTableComponent,
    TransactionsTableComponent,
    PaymentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MessagesModule,
    MessageModule,
    ReactiveFormsModule,
    HttpClientModule,
    CardModule,
    AccordionModule,
    MatButtonModule,
    MatCheckboxModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSelectModule,
    CurrencyMaskModule,
  ],
  providers: [MessageService, AuthGuardService, ProfileService],
  bootstrap: [AppComponent]
})
export class AppModule {}
