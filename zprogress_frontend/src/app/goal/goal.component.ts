import {Component, OnInit} from '@angular/core';
import {DatePipe} from '@angular/common';

@Component({
    selector: 'app-goal',
    templateUrl: './goal.component.html',
    styleUrls: ['./goal.component.scss'],
})
export class GoalComponent implements OnInit {

    constructor(private datePipe: DatePipe) {
    }

    ngOnInit() {
    }

    minDate() {
        console.log(this.datePipe.transform(new Date(), 'yyyy-MM-dd'));
        return this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    }

}
