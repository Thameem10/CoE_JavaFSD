import { CanActivateFn } from '@angular/router';

export const enquiryGuard: CanActivateFn = (route, state) => {
  const username = localStorage.getItem('username');
  return username?true:false;
};
