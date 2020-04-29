import { Component, OnInit } from '@angular/core';
import {MainService} from '../shared/MainService';
import {AuthenticationService} from '../shared/AuthenticationService';
import {Router} from '@angular/router';

declare var peg$parse: any;

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  dummyContent: string;

  constructor(private mainService: MainService,
              private authenticationService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    this.mainService.callBase().subscribe(res => {
      this.dummyContent = JSON.stringify(res, null, 2).replace(/\s/g, '');
      console.log(
        peg$parse(this.dummyContent));
    });
  }

  logout() {
    console.log('logout');
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
