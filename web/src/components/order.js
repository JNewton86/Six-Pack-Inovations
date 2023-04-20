import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";

/**
 * The table component for the website.
 */
export default class Order extends BindingClass {
    constructor() {
        super();

        const methodsToBind = ['addTableToPage', 'buildTable'];
        this.bindClassMethods(methodsToBind, this);

        this.client = new MusicPlaylistClient();
    }

    async addTableToPage() {
        console.log('Order.js building...');
        const currentUser = await this.client.getIdentity();
        const data = await this.client.getData();
        const table = this.buildTable(data);
        const container = document.getElementById('table-container');
        table.classList.add('table-container'); // Add a class to style the table
        container.appendChild(table);
    }

    buildTable(data) {
        if (!Array.isArray(data)) {
            console.error('Error: data is not an array!');
            return;
        }
        const table = document.createElement('table');
        table.classList.add('table-container'); // Add a class to style the table

        // Create the table header row
        const headerRow = table.insertRow();
        const headers = ['OrderID', 'Order Items', 'Total', 'Order Processed'];
        headers.forEach(header => {
            const th = document.createElement('th');
            th.innerText = header;
            headerRow.appendChild(th);
        });

        // Create the table body rows
        data.forEach(order => {
            const row = table.insertRow();
            row.classList.add('order-row'); // Add a class to style the row
            const cells = [order.orderId, order.orderItems, order.total, order.status];
            cells.forEach(cell => {
                const td = document.createElement('td');
                td.innerText = cell;
                row.appendChild(td);
            });
        });

        return table;
    }

}