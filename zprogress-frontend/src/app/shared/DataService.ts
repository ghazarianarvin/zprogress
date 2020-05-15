import {EventEmitter, Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable()
export class DataService {
  currentAffordances: any;
  newResourceEvent = new EventEmitter();
}
