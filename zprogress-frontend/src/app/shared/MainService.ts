import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {AuthenticationService} from './AuthenticationService';

@Injectable()
export class MainService {

  private url = 'http://localhost:8080/goals';

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) {
  }

  callBase() {
    return this.http.get(this.url, this.header());
  }

  get(url) {
    return this.http.get(url, this.header());
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
