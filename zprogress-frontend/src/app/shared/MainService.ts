import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {AuthenticationService} from './AuthenticationService';

@Injectable()
export class MainService  {

  private url =  'http://localhost:8080/base';

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) {
  }

  callBase() {
    this.http.get(this.url, this.header())
      .pipe(
        map(resp => this.parseKeys(resp))
      ).subscribe(res => console.log(res));
  }

  private parseKeys(data) {
    Object.keys(data.message).forEach(key => {
      console.log(data.message[key] + ' <<<<<<<<<<<');
    });
    return data;
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
