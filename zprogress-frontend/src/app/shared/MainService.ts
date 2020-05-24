import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationService} from './AuthenticationService';

@Injectable()
export class MainService {

  url = 'http://localhost:8080/goals';

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) {
  }

  getBaseUrl() {
    return this.url;
  }

  callBase() {
    return this.http.get(this.url, this.header());
  }

  get(url) {
    return this.http.get(url, this.header());
  }

  post(url, body) {
    return this.http.post(url, body,
      {
        observe: 'response',
        headers: new HttpHeaders({
          'Content-Type': 'application/prs.hal-forms+json',
          Authorization: 'Bearer ' + this.authenticationService.getJwtToke()
        })
    });
  }

  private header() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/prs.hal-forms+json',
        Authorization: 'Bearer ' + this.authenticationService.getJwtToke()
      })
    };
    return httpOptions;
  }
}
