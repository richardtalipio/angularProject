export class CustomerTableData{
    customerName: String;
    remarks: String;
    latestDelivery: String;

    constructor(customerName: String, remarks: String, latestDelivery: String){
        this.customerName = customerName;
        this.remarks = remarks;
        this.latestDelivery = latestDelivery;
    }
}