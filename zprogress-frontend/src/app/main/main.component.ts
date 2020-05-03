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
  selectedElement: any;

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
    this.selectedElement = null;
    this.doSelectSingleResource(url);
    console.log(this.selectedElement);
  }

  private doSelectSingleResource(url: string) {
    this.mainService.get(url).subscribe(function(res) {
      var parsedContent = peg$parse(JSON.stringify(res, null).replace(/\n/g, ''));
      if (this.selectedElement != null) {
        this.selectedElement.subElements = parsedContent.elements;
        console.log(this.selectedElement.subElements);
      } else {
        this.selectedElement = parsedContent;
        console.log(this.selectedElement);
        let links = this.selectedElement.links.getNonSelfLinks();
        for (let i = 0; i < links.length; i++) {
          this.doSelectSingleResource(links[i]);
        }
      }
    }.bind(this));
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
