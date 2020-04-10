import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import {CanActivate} from '@angular/router';

@Injectable()
export class AuthenticationService implements CanActivate {

  private url =  'http://localhost:8080/authenticate';

  constructor(private http: HttpClient) {
  }

  isUserAuthenticated() {
    return localStorage.getItem('jwt') != null;
  }

  canActivate(): boolean {
    return this.isUserAuthenticated();
  }

  authenticate(username: string, password: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/prs.hal-forms+json',
        username,
        password
      })
    };

    const authenticationObservable = this.http.post(this.url, {}, httpOptions)
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

}
