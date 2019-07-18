import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    constructor(private toastr: ToastrService) {
    }

    isAuthenticated(): boolean {
        return sessionStorage.getItem('Authorization') != null;
    }

    logout(): void {
        sessionStorage.removeItem('Authorization');
        this.toastr.success('You have successfully logged out!', 'Success');
    }
}
