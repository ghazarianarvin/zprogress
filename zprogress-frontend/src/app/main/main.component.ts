import {Component, OnInit} from '@angular/core';
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
              private router: Router) {
  }

  ngOnInit() {
    this.mainService.callBase().subscribe(res => {
      this.content = peg$parse(JSON.stringify(res, null).replace(/\n/g, ''));
      console.log(this.content);
    });
  }

  selectSingleResourceAndFetchSubElements(element: any) {
    this.mainService.get(element.getLinkByRel('self')).subscribe(function (res) {
      let parsedContent = peg$parse(JSON.stringify(res, null).replace(/\n/g, ''));
      let links = parsedContent.links.getNonSelfLinks();
      this.selectedElement = parsedContent;
      for (let link of links) {
        this.mainService.get(link).subscribe(subElements => {
          element.subElements = peg$parse(JSON.stringify(subElements, null).replace(/\n/g, '')).elements;
        });
      }
    }.bind(this));
    console.log(this.content);
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
