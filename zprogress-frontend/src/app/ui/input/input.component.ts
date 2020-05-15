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

  isDateValid = true;
  isNotEmpty = true;
  isInputValid = true;

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
    const today = new Date();
    today.setDate(today.getDate() + 10);
    return today;
  }

  validateThis() {
    this.resetValidationFields();
    if (this.isDate()) {
      if ((this.value == null || this.value == undefined) || (this.isFutureDate() && this.value <= this.minFutureDate())) {
        this.isDateValid = false;
      }
    } else if (this.isFieldRequired() && (!this.value || this.value.length == 0)) {
      this.isNotEmpty = false;
    }

    this.isInputValid = this.isDateValid && this.isNotEmpty;
  }

  private resetValidationFields() {
    this.isDateValid = true;
    this.isInputValid = true;
    this.isNotEmpty = true;
  }

  valueChanged(isDate: boolean) {
    this.validateThis();
    if (this.value != null) {
      if (isDate) {
        this.onValueChange.emit(this.value.toJSON());
      } else {
        this.onValueChange.emit(this.value);
      }
    }
  }
}
