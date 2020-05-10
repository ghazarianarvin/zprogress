import { Component, OnInit } from '@angular/core';
import {DataService} from '../shared/DataService';
import {MainService} from '../shared/MainService';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  affordances: any;
  fieldValue = {};

  constructor(private dataService: DataService,
              private mainService: MainService) { }

  ngOnInit() {
    this.affordances = this.dataService.currentAffordances.affordances;
  }

  post() {
    console.log(this.fieldValue);
    this.mainService.post(this.dataService.currentAffordances.url, this.fieldValue).subscribe(res => {
      console.log(res);
    });
  }
}
