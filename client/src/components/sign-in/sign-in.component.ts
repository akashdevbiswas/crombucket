import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { NgClass } from '@angular/common';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
@Component({
  selector: 'sign-in-page',
  styles: `
  :host{
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  `,
  templateUrl: 'sign-in.component.html',
  standalone: true,
  imports: [ReactiveFormsModule, NgClass, FontAwesomeModule],
})
export class SignInPage {
  constructor(private authService: AuthService, private router: Router) {}

  signInForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  onSubmit() {
    if (!this.signInForm.valid) {
      return;
    }
    const { username, password } = this.signInForm.value;

    if (!username || !password) {
      return;
    }

    this.authService
      .login({
        emailOrUsername: username,
        password: password,
      })
      .subscribe({
        next: (authToken) => {
          const { token } = authToken;
          if (token) {
            this.authService.setAuthorization(token);
            this.router.navigate(['/'], {replaceUrl:true});
          }
        },
      });
  }

  isPasswordView = false;
  isFocus = false;
  faEye = faEye;
  faEyeSlash = faEyeSlash;

  togglePasswordView() {
    this.isPasswordView = !this.isPasswordView;
  }
  onFucus() {
    this.isFocus = true;
  }
  onBlur() {
    this.isFocus = false;
  }
}
