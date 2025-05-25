import {
  computed,
  effect,
  Inject,
  Injectable,
  PLATFORM_ID,
  signal,
  Signal,
  WritableSignal,
} from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  user: WritableSignal<Users | null> = signal<Users | null>(null);

  constructor(
    private authService: AuthService,
    private httpClient: HttpClient,
    router: Router,
    @Inject(PLATFORM_ID) platformId: Object
  ) {
    effect(() => {
      if (!isPlatformBrowser(platformId)) {
        return;
      }
      if (!authService.getAuthrization()) {
        this.user.set(null);
      } else {
        this.fetchUser().subscribe({
          next: (user: Users) => {
            user && this.user.set(user);
          },
          error: (err) => {
            console.error(err);
          },
        });
      }
    });
  }

  private fetchUser() {
    return this.httpClient.get<Users>('/user-service/api/v1/users', {
      headers: {
        Authorization: `Bearer ${this.authService.getAuthrization()}`,
      },
    });
  }


  logout() {
    this.authService.removeAuthorization();
    this.user.set(null);
  }
}

export interface Users {
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  role: Role;
}

export type Role = 'ADMIN' | 'USER';
