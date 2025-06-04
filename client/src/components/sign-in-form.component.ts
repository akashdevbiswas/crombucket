import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
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
  template: `
    <h1 class="section-heading mx-auto">Sign In</h1>
    <form
      class="form-container"
      (ngSubmit)="onSubmit()"
      [formGroup]="signInForm"
    >
      <div class="input">
        <label class="input-label" for="username">Username</label>
        <input
          id="username"
          type="text"
          name="username"
          formControlName="username"
          class="input-field"
          placeholder="Username"
        />
      </div>
      <div class="input">
        <label class="input-label" for="password">Password</label>
        <div
          class="input-field flex items-center"
          [ngClass]="{ 'input-focus': isFocus }"
        >
          <input
            class="w-full outline-none font-medium placeholder:font-normal"
            [type]="isPasswordView ? 'text' : 'password'"
            name="password"
            id="password"
            formControlName="password"
            (focus)="onFucus()"
            (blur)="onBlur()"
            placeholder="Password"
          />
          <fa-icon
            class="cursor-pointer h-full"
            (click)="togglePasswordView()"
            [icon]="isPasswordView ? faEyeSlash : faEye"
          ></fa-icon>
        </div>
      </div>
      <input type="submit" name="Sign In" class="btn btn__primary w-[40%]" />
    </form>
  `,
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
            this.router.navigateByUrl('/');
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
