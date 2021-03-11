import { OrderData } from "./order-data";

export class CustomerData{
    customerId: String;
    customerName: String;
    mobileNumber: number;
    remarks: String;
    orders: OrderData[]; 
    latestDeliveryDate: String;
}