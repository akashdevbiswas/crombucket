import { Component, effect, Inject, PLATFORM_ID, signal, WritableSignal } from '@angular/core';
import { Router } from '@angular/router';
import { ButtonComponent } from '../../components/button.component';
import { UserService } from '../../services/users.service';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'home-page',
  templateUrl: 'home.page.html',
  standalone: true,
  imports: [ButtonComponent]
})
export class HomePage  {
  
  isLoggedIn:WritableSignal<boolean> = signal(false);

  constructor(private userService: UserService) {
    effect(() => {
      this.isLoggedIn.set(this.userService.user() !== null);
    });
  }

}
