import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) {}

  private fetchUser() {
    return this.httpClient.get<Users>('/user-service/api/v1/users');
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
