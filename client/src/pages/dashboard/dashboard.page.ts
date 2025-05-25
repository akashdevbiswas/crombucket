import {
  Component,
  effect,
  Inject,
  OnInit,
  PLATFORM_ID,
  signal,
  WritableSignal,
} from '@angular/core';
import { Role, UserService } from '../../services/users.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'dashboard-page',
  templateUrl: './dashboard.page.html',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
})
export class DashboardPage {
  constructor(
    protected userService: UserService,
    protected router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    if (isPlatformBrowser(this.platformId)) {
      const user = this.userService.user();
      if (!user) {
        this.router.navigateByUrl('/auth');
      }
    }
  }
}
