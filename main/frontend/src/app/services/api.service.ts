import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiService {
  constructor(private http: HttpClient) {}

  get<T>(path: string) {
    return this.http.get<T>(`${environment.apiUrl}${path}`);
  }

  post<T>(path: string, payload: unknown) {
    return this.http.post<T>(`${environment.apiUrl}${path}`, payload);
  }

  put<T>(path: string, payload: unknown) {
    return this.http.put<T>(`${environment.apiUrl}${path}`, payload);
  }

  delete<T>(path: string) {
    return this.http.delete<T>(`${environment.apiUrl}${path}`);
  }
}
