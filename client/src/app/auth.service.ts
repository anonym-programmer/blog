import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    isAuthenticated(): boolean {
        return sessionStorage.getItem('Authorization') != null;
    }

    logout(): void {
        sessionStorage.removeItem('Authorization');
    }
}
