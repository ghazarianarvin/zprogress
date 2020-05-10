import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

  @Input() fieldMetadata: any;

  constructor() {
  }

  ngOnInit() {
  }

  renderInput() {
    return this.fieldMetadata.name !== 'id';
  }

  isFieldRequired() {
    return this.fieldMetadata.required === 'true';
  }

  getName() {
    return this.fieldMetadata.getTrimmedFieldName();
  }

  isDate() {
    return this.fieldMetadata.isDate;
  }
}
