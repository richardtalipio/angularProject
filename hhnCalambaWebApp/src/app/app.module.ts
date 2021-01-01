import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTabsModule} from '@angular/material/tabs';
import { MatButtonModule} from '@angular/material/button'; 
import { InventoryComponent } from './inventory/inventory.component';
import { OrdersComponent } from './orders/orders.component';
import { ReportsComponent } from './reports/reports.component';
import { HomeComponent } from './home/home.component'; 
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CdkTableModule } from '@angular/cdk/table'; 
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule  } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table' 
import { MatSelectModule } from '@angular/material/select'; 
import { MatDialogModule } from '@angular/material/dialog';
import { MatAutocompleteModule } from '@angular/material/autocomplete'; 
import { MatIconModule } from '@angular/material/icon'; 
import { MatSortModule } from '@angular/material/sort';
import { HttpClientModule } from '@angular/common/http';
import { ItemPopupComponent } from './popups/item-popup/item-popup.component';
import { OrderPopupComponent } from './popups/order-popup/order-popup.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { DeletePopupComponent } from './popups/delete-popup/delete-popup.component'; 



@NgModule({
  declarations: [
    AppComponent,
    InventoryComponent,
    OrdersComponent,
    ReportsComponent,  
    HomeComponent, ItemPopupComponent, OrderPopupComponent, DeletePopupComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatButtonModule,
    MatIconModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatFormFieldModule, MatInputModule, MatPaginatorModule, MatTableModule, MatSelectModule, MatDialogModule, MatAutocompleteModule,
    FormsModule, ReactiveFormsModule, CdkTableModule,
    HttpClientModule
  ],
  exports: [
    MatSortModule,
    MatPaginatorModule,
    MatProgressSpinnerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
