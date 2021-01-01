import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { ItemData } from '../models/item-data';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient ) { 
  }

  loadItems(): Observable<ItemData[]>{
    return  this.http.get<ItemData[]>('/api/item/loadItems');
  }

  addItem(item: ItemData): any{
    return  this.http.post<ItemData>('/api/item/addItem', item);
  }

  addorUpdateItem(item: ItemData, itemid: number): any{
    return  this.http.put<ItemData>('/api/item/updateItem/'+itemid, item);
  }

}
