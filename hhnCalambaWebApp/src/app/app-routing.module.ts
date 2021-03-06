import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomersComponent } from './customers/customers.component';
import { HomeComponent } from './home/home.component';
import { ItemsComponent } from './items/items.component';
import { ReportsComponent } from './reports/reports.component';
import { CustomersModule } from '../app/customers/customers.module';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component:  HomeComponent},
  { path: 'customers', loadChildren: () => import('../app/customers/customers.module').then(m => m.CustomersModule) },
  { path: 'items', component: ItemsComponent},
  { path: 'reports', component: ReportsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
