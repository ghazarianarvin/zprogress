import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import {CanActivate} from '@angular/router';

@Injectable()
export class AuthenticationService implements CanActivate {

  private authenticateUrl =  'http://localhost:8080/authentication';

  constructor(private http: HttpClient) {
  }

  isUserAuthenticated() {
    return localStorage.getItem('jwt') != null;
  }

  canActivate(): boolean {
    return this.isUserAuthenticated();
  }

  getJwtToke() {
    return localStorage.getItem('jwt');
  }

  authenticate(username: string, password: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        username,
        password
      })
    };

    const authenticationObservable = this.http.post(this.authenticateUrl, {}, httpOptions)
      .pipe(
        map(res => {
          const key = 'entity';
          return res[key];
        }),
        catchError(err => throwError(err))
      );
    authenticationObservable.subscribe(res => localStorage.setItem('jwt', res));
    return authenticationObservable;
  }

  logout() {
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Bearer ' + this.getJwtToke()
      })
    };

    const authenticationObservable = this.http.post(this.authenticateUrl + '/logout', {}, httpOptions)
      .pipe(
        catchError(err => throwError(err))
      ).subscribe(res => {
        localStorage.removeItem('jwt');
      });
  }
}
