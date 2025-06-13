import { Routes } from '@angular/router';
import { HomePage } from '../pages/home/home.page';
import { AuthPage } from '../pages/auth/auth.page';
import { SignUpPage } from '../components/sign-up/sign-up.component';
import { SignInPage } from '../components/sign-in/sign-in.component';
import { StorageNodeComponent } from '../components/storage-node.component';
import { ClusterPage } from '../components/clusters.component';
import { RegionComponent } from '../components/regions.component';
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
        path: 'sign-in',
        component: SignInPage,
      },
      {
        path: 'sign-up',
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
        component: StorageNodeComponent,
      },
      {
        path: 'clusters',
        component: ClusterPage,
      },
      {
        path: 'regions',
        component: RegionComponent,
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
