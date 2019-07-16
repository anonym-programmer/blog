import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
  })
  export class AuthService {
  
    constructor() {
    }
  
    isAuthenticated() {
        return sessionStorage.getItem('Authorization') != null;
    }

    logout() {
        console.log('logout called!');
        sessionStorage.removeItem('Authorization');
    }
}
