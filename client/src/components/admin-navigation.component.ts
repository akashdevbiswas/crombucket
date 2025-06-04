import { Component } from '@angular/core';

@Component({
  standalone: true,
  selector: 'admin-navigation',
  template: `
    <div>
      <ul class="flex items-center justify-between">
        <li><a routerLink="storage-nodes" class="text-xl">Storage Nodes</a></li>
        <li><a routerLink="clusters" class="text-xl">Clusters</a></li>
        <li><a routerLink="regions" class="text-xl">Regions</a></li>
      </ul>
    </div>
  `,
})
export class AdminNavigationComponent {}
