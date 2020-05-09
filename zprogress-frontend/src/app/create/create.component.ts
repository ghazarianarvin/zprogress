import { Component, OnInit } from '@angular/core';
import {DataService} from '../shared/DataService';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  affordances: any;

  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.affordances = this.dataService.currentAffordances;
  }

}
