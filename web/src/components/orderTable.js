import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";

/**
 * The order table component for the website.
 */
export default class OrderTable extends BindingClass {
    constructor() {
        super();

        const methodsToBind = ['addTableToPage', 'buildTable'];
        this.bindClassMethods(methodsToBind, this);

        this.client = new MusicPlaylistClient();
    }

    async addTableToPage() {
        console.log('OrderTable.js building...');
        const currentUser = await this.client.getIdentity();
        const data = await this.client.getAllOrderData();
        const table = this.buildTable(data);
        const container = document.getElementById('order-table-container');
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
        const headers = ['Order Id', 'Client Id', 'Order Items', 'Total Cost'];
        headers.forEach(header => {
            const th = document.createElement('th');
            th.innerText = header;
            headerRow.appendChild(th);
        });

        // Create the table body rows
        data.forEach(item => {
            const row = table.insertRow();
            row.classList.add('order-row'); // Add a class to style the row
            const cells = [item.orderId, item.clientId, item.orderItems, item.totalCost];
            cells.forEach(cell => {
                const td = document.createElement('td');
                td.innerText = cell;
                row.appendChild(td);
            });
        });

        return table;
    }

}