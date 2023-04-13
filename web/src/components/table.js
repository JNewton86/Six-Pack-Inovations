import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";

/**
 * The table component for the website.
 */
export default class Table extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addTableToPage'
        ];
        this.bindClassMethods(methodsToBind, this);

        this.client = new MusicPlaylistClient();
    }

    async displayTable() {
        const data = await this.client.getData(); // replace with the actual method call to get data from MusicPlaylistClient
        const table = this.addTableToPage(data);
        const container = document.getElementById('table-container'); // replace with the ID of the element you want to mount the table to
        container.appendChild(table);
    }

    addTableToPage(data) {
        const table = document.createElement('table');
        table.classList.add('beer-table'); // Add a class to style the table

        // Create the table header row
        const headerRow = table.insertRow();
        const headers = ['Beer ID', 'Packaging Type', 'Beer Type', 'Available Inventory', 'Reserved Inventory', 'Total Inventory'];
        headers.forEach(header => {
            const th = document.createElement('th');
            th.innerText = header;
            headerRow.appendChild(th);
        });

        // Create the table body rows
        data.forEach(item => {
            const row = table.insertRow();
            row.classList.add('playlist-row'); // Add a class to style the row
            const cells = [item.beerId, item.packagingType, item.beerType, item.availableInventory, item.reservedInventory, item.totalInventory];
            cells.forEach(cell => {
                const td = document.createElement('td');
                td.innerText = cell;
                row.appendChild(td);
            });
        });

        return table;
    }
}
