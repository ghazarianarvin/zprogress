import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {DataService} from '../shared/DataService';
import {MainService} from '../shared/MainService';
import {InputComponent} from '../ui/input/input.component';

declare var peg$parse: any;

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  @ViewChildren(InputComponent) inputs: QueryList<InputComponent>;
  affordances: any;
  requestBody = {};
  postDisabled = false;
  successMessage: string;

  constructor(private dataService: DataService,
              private mainService: MainService) { }

  ngOnInit() {
    this.affordances = this.dataService.currentAffordances.affordances;
  }

  post() {
    return this.mainService.post(this.dataService.currentAffordances.url, this.requestBody).subscribe(res => {
      this.successMessage = 'resource successfully created.';
      this.mainService.get(res.headers.get('Location')).subscribe(resource => {
        this.dataService.newResourceEvent.emit(
          peg$parse(JSON.stringify(resource, null).replace(/\n/g, ''))
        );
      });
    });
  }

  checkInputValidity() {
    let foundOneInvalid = false;
    this.inputs.forEach(input => {
      input.validateThis();
      if (!input.isInputValid) {
        foundOneInvalid = true;
        return;
      }
    });
    this.postDisabled = foundOneInvalid;
  }

  resetPostButton() {
    this.postDisabled = false;
  }
}
