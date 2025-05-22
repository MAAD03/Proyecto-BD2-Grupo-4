import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<string> {
    return this.http.post(`${this.apiUrl}/login`, null, {
      params: { username, password },
      withCredentials: true,
      responseType: 'text' as const
    });
  }

  executeScript(script: string): Observable<string> {
    return this.http.post(`${this.apiUrl}/execute-script`, script, {
      headers: { 'Content-Type': 'application/json' },
      withCredentials: true,
      responseType: 'text' as const
    });
  }
}
