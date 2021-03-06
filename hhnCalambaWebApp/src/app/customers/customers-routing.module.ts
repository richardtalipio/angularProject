import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomersComponent } from './customers.component';
import { OrderDetailsComponent } from './order-details/order-details.component';



const customerRoutes: Routes = [
    {
        path: '', component: CustomersComponent, children: [
            { path: 'list', component: CustomerListComponent },
            { path: 'details', component: CustomerDetailsComponent },
            { path: 'order-details', component: OrderDetailsComponent },
            { path: '', redirectTo: 'list', pathMatch: 'full' },
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(customerRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class CustomersRoutingModule { }


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/