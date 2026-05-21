import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtResponse } from '../models/jwt-response.model';
import { ApiService } from './api.service';
import { Observable, tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenKey = 'cod_token';

  constructor(private api: ApiService, private router: Router) {}

  login(email: string, password: string): Observable<JwtResponse> {
    return this.api.post<JwtResponse>('/auth/login', { email, password }).pipe(
      tap(response => {
        localStorage.setItem(this.tokenKey, response.token);
        localStorage.setItem('cod_role', response.role);
      })
    );
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem('cod_role');
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem(this.tokenKey);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getRole(): string | null {
    return localStorage.getItem('cod_role');
  }
}
