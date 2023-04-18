import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";

/**
 * The table component for the website.
 */
export default class Table extends BindingClass {
    constructor() {
        super();

        const methodsToBind = ['addTableToPage', 'buildTable'];
        this.bindClassMethods(methodsToBind, this);

        this.client = new MusicPlaylistClient();
    }

     async addTableToPage() {
            console.log('updateTable.js building...');
            const currentUser = await this.client.getIdentity();
            const data = await this.client.getData();
            const updateTable = this.buildTable(data);
            const container = document.getElementById('table-container');
            table.classList.add('updateTable-container'); // Add a class to style the table
            container.appendChild(table);
        }

    async updateTable() {
             console.log('Table.js building...');
             const currentUser = await this.client.getIdentity();
             const data2 = await this.client.updateInventoryItem;
             const container = document.getElementById('UpdateTable-container');
             table.classList.add('Table-container'); // Add a class to style the table
             container.appendChild(updateTable);
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
    const headers = ['Beer ID', 'Beer Type', 'Name', 'Packaging Type', 'Unit Price', 'Available Units', 'Reserved Units', 'Actions'];
    headers.forEach(header => {
        const th = document.createElement('th');
        th.innerText = header;
        headerRow.appendChild(th);
    });

    // Create the table body rows
    data.forEach(item => {
        const row = table.insertRow();
        row.classList.add('playlist-row'); // Add a class to style the row
        const cells = [
            item.beerId,
            item.beerType,
            item.name,
            item.packagingType,
            `<input type="number" class="form-control" value="${item.unitPrice}" data-field="unitPrice">`,
            `<input type="number" class="form-control" value="${item.availableUnits}" data-field="availableUnits">`,
            `<input type="number" class="form-control" value="${item.reservedUnits}" data-field="reservedUnits">`,
            `<button class="btn btn-primary" onclick="updateRow(this)">Save</button>`
        ];
        cells.forEach(cell => {
            const td = document.createElement('td');
            td.innerHTML = cell;
            row.appendChild(td);
        });
    });

    return table;
}

//    async function updateRow(button) {
//        const row = button.closest('tr');
//        const fields = row.querySelectorAll('[data-field]');
//        const beerId = row.querySelector('td:first-child').innerText;
//        const updateObj = { beerId };
//
//        for (const field of fields) {
//            const fieldName = field.dataset.field;
//            const fieldValue = field.value;
//
//            if (!isNaN(fieldValue)) {
//                updateObj[fieldName] = parseFloat(fieldValue);
//            } else {
//                updateObj[fieldName] = fieldValue;
//            }
//        }
//
//        try {
//            await MusicPlaylistClient.updateInventoryItem(updateObj);
//            alert('Item updated successfully!');
//        } catch (error) {
//            console.error(error);
//            alert('Error updating item. See console for details.');
//        }
//    }
}