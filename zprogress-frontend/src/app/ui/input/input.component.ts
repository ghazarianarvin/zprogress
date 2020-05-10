import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

  @Input() fieldMetadata: any;
  @Output() onValueChange = new EventEmitter<any>();
  value: any;

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

  isFutureDate() {
    return this.fieldMetadata.isFutureDate;
  }

  minFutureDate() {
    let today = new Date();
    today.setDate(today.getDate() + 10);
    return today;
  }

  valueChanged(isDate: boolean) {
    if (isDate) {
      this.onValueChange.emit(this.value.toJSON());
    } else {
      this.onValueChange.emit(this.value);
    }
  }
}
