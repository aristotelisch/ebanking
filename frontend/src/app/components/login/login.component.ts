import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { first } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/components/common/messageservice';
import { AuthenticationService } from 'src/app/services/authentication.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [AuthenticationService],
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  userLogin = { email: '', password: ''};

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    })
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.loginForm.controls[controlName].hasError(errorName);
  };

  login(loginFormValues) {
    console.log('ON SUBMIT');
    this.submitted = true;

     // stop here if form is invalid
     if (this.loginForm.invalid) {
       console.log('Form validation', this.loginForm.invalid);
       console.log('Form errors', this.loginForm.controls);
       return;
     }

    this.loading = true;
    this.authenticationService.login(loginFormValues.email, loginFormValues.password)
      .subscribe(result => {
          // Store the token
          console.log('RESULT OF LOGIN: ', result);
          this.authenticationService.setToken(result['accessToken']);
          // Redirect to home
          this.router.navigate(['']);
        },
        error => {
          this.messageService.add({
            severity: 'error', summary: error.error.message, detail: ''
          });

          setTimeout(() => {
            this.messageService.clear();
          }, 3000);
          this.loading = false;
        });
  }
}
