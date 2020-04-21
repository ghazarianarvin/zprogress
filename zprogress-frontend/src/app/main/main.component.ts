import { Component, OnInit } from '@angular/core';
import {MainService} from '../shared/MainService';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private mainService: MainService) { }

  ngOnInit() {
    this.mainService.callBase();
  }

}
