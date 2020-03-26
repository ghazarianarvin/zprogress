import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Goal} from './Goal';

@Injectable({
    providedIn: 'root'
})
class ApiService {
    private apiUrl = 'https://localhost:8080/goal';

    const;
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
            // TODO add jwt token here for authentication
        })
    };

    constructor(private http: HttpClient) {
    }

    createGoal(goal: Goal): Observable<Goal> {
        return this.http
            .post<Goal>(this.apiUrl, goal, this.httpOptions)
            .pipe(
                catchError(this.handleError('createGoal', goal))
            );
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);

            return of(result as T);
        };
    }


}
