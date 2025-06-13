import { AsyncPipe } from '@angular/common';
import { afterNextRender, Component, inject, OnInit, signal, WritableSignal } from '@angular/core';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { filter, Observable } from 'rxjs';
import { Users, UserService } from '../../services/users.service';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'navbar-compoenent',
  templateUrl: './navbar.component.html',
  standalone: true,
  imports: [AsyncPipe, RouterLink],
})
export class NavbarComponent implements OnInit{
  isUserAdmin: WritableSignal<boolean> = signal(false);

  user$: Observable<Users> | null = null;


  url:WritableSignal<string> = signal('');


  isHovered = false;


  constructor(userService:UserService,private router:Router, private authService:AuthService) {
    afterNextRender(() => {
      this.user$ = userService.fetchUser();
    })

  }
  ngOnInit(): void {
    this.router.events.pipe(filter(event=>event instanceof NavigationEnd)).subscribe((event:NavigationEnd)=>{
      this.url.set(event.urlAfterRedirects);
    })
  }

  onMouseOver() {
    this.isHovered = true;
  }

  onMouseOut() {
    this.isHovered = false;
  }

  onSignOut() {
    this.isHovered
    this.authService.removeAuthorization();
    this.router.navigate(['/auth/sign-in'], { replaceUrl: true });
  }
}
