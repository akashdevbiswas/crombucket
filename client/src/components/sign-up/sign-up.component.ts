import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../../services/auth.service';
import { ErrorService } from '../../services/error.service';

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
  templateUrl: 'sign-up.component.html',
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
