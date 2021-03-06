import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { ApiEndpoints } from '../constants/api-endpoints'

@Injectable({
    providedIn: 'root'
})
export class TmdbProxyServiceService {
    constructor(private http: HttpClient) { }

    getHomeData(): Observable<any> {
        var url = ApiEndpoints['HomeUrl'];
        return this.http.get(url);
    }

    getItemDetail(id: string, category: string): Observable<any> {
        var url = ApiEndpoints['ItemUrl'] + '/' + category + '/' + id;
        return this.http
            .get(url)
            .pipe(
                catchError(error => {
                    console.log('Response errors');
                    return of([]);
                })
            );
    }

    getCastDetail(id: string): Observable<any> {
        var url = ApiEndpoints['CastUrl'] + '/' + id;
        return this.http
            .get(url)
            .pipe(
                catchError(error => {
                    console.log('Response errors');
                    return of({});
                })
            );
    }

    search(keyword: string): Observable<any> {
        keyword = escape(keyword.trim());
        if (keyword === '') {
            return of([]);
        }
        var url = ApiEndpoints['SearchUrl'] + '?keyword=' + keyword;
        return this.http
            .get(url)
            .pipe(
                catchError(error => {
                    console.log('Response errors');
                    return of({});
                })
            );
    }
}
