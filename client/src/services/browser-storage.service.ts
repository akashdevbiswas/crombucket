import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';



@Injectable({
  providedIn: 'root'
})
export class BrowserStorageService {

  get(key: string) {
    return localStorage.getItem(key);
  }
  set(key: string, value: string) {    
    localStorage.setItem(key, value);
  }
  remove(key: string) {
    localStorage.removeItem(key);
  }
}