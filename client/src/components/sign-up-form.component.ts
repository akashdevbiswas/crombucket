import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../services/auth.service';
import { ErrorService } from '../services/error.service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgClass } from '@angular/common';

@Component({
  selector: 'sign-up-page',
  styles: `
  :host{
    display: flex;
    flex-direction: column;
    align-items: center;
    gap:28px;
  }
  `,
  template: `
    <h1 class="section-heading">Sign Up</h1>
    <form
      class="form-container"
      [formGroup]="signUpForm"
      (ngSubmit)="onSubmit()"
    >
      <div class="input-group">
        <div class="input">
          <label class="input-label" for="username">Username</label>
          <input
            class="input-field"
            type="text"
            name="username"
            id="username"
            formControlName="username"
            placeholder="akashbiswas"
          />
        </div>
        <div class="input">
          <label class="input-label" for="email">Email</label>
          <input
            class="input-field"
            type="email"
            name="email"
            id="email"
            formControlName="email"
            placeholder="abc@xyz.com"
          />
        </div>
      </div>

      <div class="input-group">
        <div class="input">
          <label class="input-label" for="password">Password</label>
          <input
            class="input-field"
            type="password"
            name="password"
            id="password"
            formControlName="password"
            placeholder="Password"
          />
        </div>
        <!-- This is manageable password field -->
        <div class="input">
          <label class="input-label" for="confirmPassword"
            >Confirm Password</label
          >
          <div
            class="input-field flex items-center"
            [ngClass]="{ 'input-focus': isFocus }"
          >
            <input
              class="w-full outline-none font-medium placeholder:font-normal"
              [type]="isPasswordView ? 'text' : 'password'"
              name="confirmPassword"
              id="confirmPassword"
              formControlName="confirmPassword"
              (focus)="onFucus()"
              (blur)="onBlur()"
              placeholder="Confirm Password"
            />
            <fa-icon
              class="cursor-pointer h-full"
              (click)="togglePasswordView()"
              [icon]="isPasswordView ? faEyeSlash : faEye"
            ></fa-icon>
          </div>
        </div>
        <!-- Till here. -->
      </div>
      <div class="input-group">
        <div class="input">
          <label class="input-label" for="firstName">FirstName</label>
          <input
            class="input-field"
            type="firstName"
            name="firstName"
            id="firstName"
            formControlName="firstName"
            placeholder="Jhon"
          />
        </div>
        <div class="input">
          <label class="input-label" for="lastName">Last Name</label>
          <input
            class="input-field"
            type="password"
            name="lastName"
            id="lastName"
            formControlName="lastName"
            placeholder="Doe"
          />
        </div>
      </div>

      <div class="input-group">
        <div class="input">
          <label class="input-label" for="dateOfBirth">Date Of Birth</label>
          <input
            class="input-field"
            type="date"
            name="dateOfBirth"
            id="dateOfBirth"
            formControlName="dateOfBirth"
          />
        </div>
        <div class="input">
          <label class="input-label" for="gender">Gender</label>
          <select
            class="input-field"
            name="gender"
            id="gender"
            formControlName="gender"
          >
            <option disabled [selected]="true">Select Gender</option>
            @for(eachOption of genderOptions; track $index){
            <option>{{ eachOption }}</option>
            }
          </select>
        </div>
      </div>

      <input
        type="submit"
        name="Sign Up"
        id="sign-up"
        class="btn btn__primary w-[40%] mx-auto mt-4"
      />
    </form>
  `,
  standalone: true,
  imports: [ReactiveFormsModule, FontAwesomeModule, NgClass],
})
export class SignUpPage {
  genderOptions: string[] = ['MALE', 'FEMALE', 'OTHERS'];

  faEye = faEye;
  faEyeSlash = faEyeSlash;

  constructor(
    private authService: AuthService,
    private router: Router,
    protected errorService: ErrorService
  ) {}

  signUpForm = new FormGroup({
    username: new FormControl('', {
      validators: [Validators.required, Validators.minLength(5)],
    }),
    email: new FormControl('', {
      validators: [Validators.email, Validators.required],
    }),
    password: new FormControl('', {
      validators: [Validators.required, Validators.minLength(8)],
    }),
    confirmPassword: new FormControl('', {
      validators: [Validators.required, Validators.minLength(8)],
    }),
    firstName: new FormControl('', {
      validators: [Validators.required],
    }),
    lastName: new FormControl('', {
      validators: [Validators.required],
    }),
    dateOfBirth: new FormControl('1999-11-27', {
      validators: [Validators.required],
    }),
    gender: new FormControl('', {
      validators: [Validators.nullValidator],
    }),
  });

  onSubmit() {
    if (!this.signUpForm.valid) {
      return;
    }

    const {
      username,
      email,
      password,
      confirmPassword,
      firstName,
      lastName,
      dateOfBirth,
      gender,
    } = this.signUpForm.value;

    // if (
    //   this.username.value &&
    //   this.email.value &&
    //   this.password.value &&
    //   this.firstName.value &&
    //   this.lastName.value &&
    //   this.gender.value &&
    //   this.dateOfBirth.value
    // ) {
    // }
    // this.authService
    //     .register({
    //       username: this.username.value,
    //       password: this.password.value,
    //       firstName: this.firstName.value,
    //       lastName: this.lastName.value,
    //       email: this.email.value,
    //       gender: this.gender.value,
    //       dateOfBirth: this.dateOfBirth.value,
    //     })
    //     .subscribe({
    //       next: (res) => {
    //         const { status } = res;
    //         if (status === 204) {
    //           this.router.navigateByUrl('/auth/login');
    //         }
    //       },
    //       error: (err) => {
    //         console.error(err);
    //         this.errorService.setError(
    //           'Some error occurred while saving the user'
    //         );
    //       },
    //       complete: () => {
    //         console.log('User saved successfully');
    //       },
    //     });
  }

  isPasswordView: boolean = false;
  togglePasswordView() {
    this.isPasswordView = !this.isPasswordView;
  }

  isFocus: boolean = false;
  onFucus() {
    this.isFocus = true;
  }
  onBlur() {
    this.isFocus = false;
  }
}
