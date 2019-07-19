import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    constructor(private toastr: ToastrService, private router: Router) {
    }

    isAuthenticated(): boolean {
        return sessionStorage.getItem('Authorization') != null;
    }

    logout(): void {
        this.router.navigate(['']);
        sessionStorage.removeItem('Authorization');
        this.toastr.success('You have successfully logged out!', 'Success');
    }
}
