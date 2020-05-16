import {Component, OnInit} from '@angular/core';
import {MainService} from '../shared/MainService';
import {AuthenticationService} from '../shared/AuthenticationService';
import {Router} from '@angular/router';
import {DataService} from '../shared/DataService';

declare var peg$parse: any;

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  rootResource: any;
  selectedElement: any;

  constructor(private mainService: MainService,
              private authenticationService: AuthenticationService,
              private dataService: DataService,
              private router: Router) {
  }

  ngOnInit() {
    this.refreshBase();
    this.dataService.newResourceEvent.subscribe(newRes => this.rootResource.elements.push(newRes));
  }


  private refreshBase() {
    this.mainService.callBase().subscribe(res => {
      this.rootResource = peg$parse(JSON.stringify(res, null).replace(/\n/g, ''));
      this.dataService.currentAffordances = {
        url: this.mainService.getBaseUrl(),
        affordances: this.rootResource.affordances
      };
      console.log(this.rootResource);
    });
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  selectElement(element: any) {
    this.mainService.get(element.getLinkByRel('self')).subscribe(function(res) {
      const self = peg$parse(JSON.stringify(res, null).replace(/\n/g, ''));
      element.links = self.links;
      element.affordance = self.affordance;

      const links = self.links.getNonSelfLinks();
      for (const link of links) {
        this.mainService.get(link).subscribe(subElements => {
          element.subElements = peg$parse(JSON.stringify(subElements, null).replace(/\n/g, '')).elements;
        });
      }

      this.selectedElement = element;
      console.log(this.selectedElement);
    }.bind(this));
  }

}
