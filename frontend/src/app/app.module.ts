import { MessageService } from 'primeng/components/common/messageservice';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { NotificationComponent } from './components/notification/notification.component';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import {CardModule} from "primeng/card";
import {
  MatButtonModule,
  MatCard,
  MatCardModule,
  MatCardTitle,
  MatCheckboxModule,
  MatFormFieldModule, MatInputModule
} from "@angular/material";
import {AuthGuardService} from "./services/auth-guard.service";


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    NavigationComponent,
    NotificationComponent
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
    MatButtonModule, MatCheckboxModule, MatCardModule, MatFormFieldModule, MatInputModule
  ],
  providers: [MessageService, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
