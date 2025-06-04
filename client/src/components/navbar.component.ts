import { Component, effect, signal, WritableSignal } from '@angular/core';
import { Router } from '@angular/router';
import { Users, UserService } from '../services/users.service';
import { ButtonComponent } from './button.component';
import { AdminNavigationComponent } from './admin-navigation.component';

@Component({
  selector: 'profile-component',
  styles: [
    `
      :host {
        display: flex;
        flex-direction: row;
        align-items: center;
        gap: 8px;
      }
    `,
  ],
  template: `
    @if(userData()!== null){
    <button-component
      classes="font-medium"
      [buttonName]="'Logout'"
      (onClick)="logout()"
    >
    </button-component>
    <p class="uppercase cursor-pointer">
      {{ userData()?.username }}
    </p>
    <div class="h-12 w-12 bg-blue-300 p-2 rounded-full relative cursor-pointer">
      <span class="text-xl absolute top-1/2 left-1/2 -translate-1/2">
        {{ userData()?.initials }}
      </span>
    </div>
    }@else { @if(router.url.includes('/auth')) { @if(router.url ===
    '/auth/login') {
    <button-component
      [buttonName]="'Sign Up'"
      [navigationUrl]="'/auth/register'"
      [buttonType]="'navigation'"
    ></button-component>
    }@else{
    <button-component
      [buttonName]="'Sign In'"
      [navigationUrl]="'/auth/login'"
      [buttonType]="'navigation'"
    ></button-component>
    } }@else {
    <button-component
      [buttonName]="'Sign In'"
      [navigationUrl]="'/auth'"
      [buttonType]="'navigation'"
    ></button-component>
    } }
  `,
  imports: [ButtonComponent],
  standalone: true,
})
export class ProfileComponent {
  userData: WritableSignal<{
    initials: string;
    username: string;
  } | null> = signal(null);

  constructor(private userService: UserService, protected router: Router) {
    effect(() => {
      const user = this.userService.user();
      if (user === null) {
        this.userData.set(null);
        return;
      }
      this.userData.set({
        initials: user.firstName[0] + user.lastName[0],
        username: user.username,
      });
    });
  }

  logout() {
    this.userService.logout();
    this.router.navigateByUrl('/auth');
  }
}

@Component({
  selector: 'navbar-compoenent',
  template: `
    <nav class="flex justify-between items-center font-bold px-2 py-2">
      <p
        (click)="router.navigateByUrl('/home')"
        class="uppercase text-2xl md:text-3xl lg:text-4xl cursor-pointer"
      >
        Cromxt
      </p>
      @if(isUserAdmin()){
        <admin-navigation></admin-navigation>
      }
      <profile-component></profile-component>
    </nav>
  `,
  standalone: true,
  imports: [ProfileComponent, AdminNavigationComponent],
})
export class NavbarComponent {
  isUserAdmin: WritableSignal<boolean> = signal(false);
  constructor(protected router: Router, private userService: UserService) {
    effect(() => {
      const user = this.userService.user();
      if (user === null) {
        this.isUserAdmin.set(false);
        return;
      }
      this.isUserAdmin.set(user.role === 'ADMIN');
      
    })
  }
}
