import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CustomerData } from '../models/customer-data';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient ) { 
  }

  loadCustomers(): Observable<CustomerData[]>{
    return  this.http.get<CustomerData[]>('/api/customer/loadCustomers');
  }

}
