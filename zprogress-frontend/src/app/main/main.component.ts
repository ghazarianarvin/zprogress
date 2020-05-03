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

  content: any;
  selectedContent: any;

  constructor(private mainService: MainService,
              private authenticationService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    this.mainService.callBase().subscribe(res => {
      this.content = peg$parse(JSON.stringify(res, null).replace(/\n/g, ''));
      console.log(this.content);
    });
  }

  selectSingleResource(url: string) {
    this.mainService.get(url).subscribe(res => {
      this.selectedContent = peg$parse(JSON.stringify(res, null).replace(/\n/g, ''));
      console.log(this.selectedContent);
    });
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
