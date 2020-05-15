import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthenticationService, AuthenticationService as AuthGuard} from './shared/AuthenticationService';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './main/main.component';
import {MainService} from './shared/MainService';
import {CreateComponent} from './create/create.component';
import {DataService} from './shared/DataService';
import {InputComponent} from './ui/input/input.component';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatDatepickerModule, MatFormFieldModule, MatNativeDateModule} from '@angular/material';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'main', component: MainComponent, canActivate: [AuthGuard],
    children: [{path: 'create', component: CreateComponent, outlet: 'mainContentPageOutlet'}]},
  { path: '**', redirectTo: '' }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainComponent,
    CreateComponent,
    InputComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule, FormsModule, HttpClientModule,
    RouterModule.forRoot(routes),
    NoopAnimationsModule, MatDatepickerModule, MatNativeDateModule, MatFormFieldModule
  ],
  providers: [AuthenticationService, MainService, DataService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
