import { Routes } from '@angular/router';
import { HomePage } from '../pages/home/home.page';
import { AuthPage } from '../pages/auth/auth.page';
import { SignUpPage } from '../pages/sign-up/sign-up.page';
import { SignInPage } from '../pages/sign-in/sign-in.page';
import { StorageNodePage } from '../pages/storage-node/storage-node.page';
import { ClusterPage } from '../pages/clusters/clusters.page';
import { RegionPage } from '../pages/regions/regions.page';
import { DashboardPage } from '../pages/dashboard/dashboard.page';

export const routes: Routes = [
  {
    path: 'home',
    component: HomePage,
  },
  {
    path: 'auth',
    component: AuthPage,
    children: [
      {
        path: 'login',
        component: SignInPage,
      },
      {
        path: 'register',
        component: SignUpPage,
      },
      {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full',
      },
    ],
  },
  {
    path: 'dashboard',
    component: DashboardPage,
    children: [
      {
        path: 'storage-nodes',
        component: StorageNodePage,
      },
      {
        path: 'clusters',
        component: ClusterPage,
      },
      {
        path: 'regions',
        component: RegionPage,
      },
      {
        path: '',
        redirectTo: 'storage-nodes',
        pathMatch: 'full',
      },
    ],
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },
];
