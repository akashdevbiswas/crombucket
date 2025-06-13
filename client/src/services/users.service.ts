import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  baseUrl = '/server/user-service/api/v1/users';
  constructor(private httpClient: HttpClient) {}

  fetchUser():Observable<Users> {
    return this.httpClient.get<Users>(this.baseUrl);
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
