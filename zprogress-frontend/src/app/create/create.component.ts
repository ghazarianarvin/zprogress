import {AfterViewInit, Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {DataService} from '../shared/DataService';
import {MainService} from '../shared/MainService';
import {InputComponent} from '../ui/input/input.component';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  @ViewChildren(InputComponent) inputs: QueryList<InputComponent>
  affordances: any;
  requestBody = {};
  postDisabled = false;

  constructor(private dataService: DataService,
              private mainService: MainService) { }

  ngOnInit() {
    this.affordances = this.dataService.currentAffordances.affordances;
  }

  post() {
    console.log(this.requestBody);
    this.mainService.post(this.dataService.currentAffordances.url, this.requestBody).subscribe(res => {
      console.log(res);
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
