import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {GoalComponent} from './goal/goal.component';

const routes: Routes = [
    {
        path: 'goal', component: GoalComponent
    },
    {path: '', redirectTo: 'goal', pathMatch: 'full'}
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
