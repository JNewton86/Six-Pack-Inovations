import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";

/**
 * The table component for the website.
 */
export default class updateTable extends BindingClass {
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
             const container = document.getElementById('updateTable-container');
             updateTable.classList.add('Table-container'); // Add a class to style the table
             container.appendChild(table);
        }


    buildTable(data) {
        if (!Array.isArray(data)) {
            console.error('Error: data is not an array!');
            return;
        }
        const updateTable = document.createElement('table');
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

//    function saveRow(button) {
//
//      var row = button.parentNode.parentNode;
//      var beerId = row.cells[0].innerHTML;
//      var beerType = row.cells[1].innerHTML;
//      var name = row.cells[2].innerHTML;
//      var packagingType = row.cells[3].innerHTML;
//      var availableUnits = row.cells[4].getElementsByTagName("input")[0].value;
//      var reservedUnits = row.cells[5].getElementsByTagName("input")[0].value;
//      var unitPrice = row.cells[6].getElementsByTagName("input")[0].value;
//
//
//      // Make a REST API call to update the inventory
//
//      fetch(fetch('/api/inventory/' + beerType + '/' + packagingType)
//
//      fetch('/api/inventory/' + beerId + '/' + packagingType, {
//        method: 'PUT',
//        body: JSON.stringify({
//          beerId: beerId,
//          packagingType:packagingType,
//          availableUnits: availableUnits,
//          reservedUnits: reservedUnits,
//          unitPrice: unitPrice
//        }),
//        headers: {
//          'Content-Type': 'application/json'
//        }
//      })
//        .then(response => response.json())
//        .then(data => {
//          // Update the row with the new data
//          row.cells
//          row.cells[4].innerHTML = availableUnits;
//          row.cells[5].innerHTML = reservedUnits;
//          row.cells[6].innerHTML = unitPrice;
//
//          // Remove the input fields and save button
//          row.cells[4].innerHTML = availableUnits;
//          row.cells[5].innerHTML = reservedUnits;
//          row.cells[6].innerHTML = unitPrice;
//          row.cells[7].innerHTML = '<button onclick="editRow(this)">Edit</button>';
//        });
//    }
//
//    function editRow(button) {
//      var row = button.parentNode.parentNode;
//      var availableUnits = row.cells[4].innerHTML;
//      var reservedUnits = row.cells[5].innerHTML;
//      var unitPrice = row.cells[6].innerHTML;
//
//      row.cells[4].innerHTML = '<input type="number" value="' + availableUnits + '" />';
//      row.cells[5].innerHTML = '<input type="number" value="' + reservedUnits + '" />';
//      row.cells[6].innerHTML = '<input type="number" value="' + unitPrice + '" />';
//
//    }


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
}

