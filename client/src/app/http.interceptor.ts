import { Injectable } from "@angular/core";
import { HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class HttpInterceptor implements HttpInterceptor {

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const authorization: string = sessionStorage.getItem('Authorization');

        if (authorization) {
            request = request.clone({ setHeaders: { 'Authorization' : authorization } });
        }
        
        return next.handle(request);
     }
}
