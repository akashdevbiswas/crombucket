import { isPlatformBrowser } from '@angular/common';
import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'auth-page',
  templateUrl: 'auth.page.html',
  imports: [RouterOutlet],
  standalone: true,
})
export class AuthPage {
  constructor(
    protected router: Router,
    authService: AuthService,
    @Inject(PLATFORM_ID) platformId: Object
  ) {
    if (isPlatformBrowser(platformId)) {
      if (authService.getAuthrization()) {
        this.router.navigateByUrl('/dashboard');
      }
    }
  }
}
