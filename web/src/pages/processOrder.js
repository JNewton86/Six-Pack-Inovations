import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";
import OrderTable from '../components/orderTable';
import DataStore from "../util/DataStore";
import Header from '../components/header';


/**
 * Logic needed for the view table page of the website.
 */
class Inventory extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'updateInventory'], this);

        // Create a new datastore with an initial "empty" state.
        this.dataStore = new DataStore();
        this.orderTable = new OrderTable(this.dataStore);
        this.client = new MusicPlaylistClient();
    }

    /**
     * Add the table to the page and load the MusicPlaylistClient.
     */
    mount() {
        console.log('Inventory.js mounting...');
        this.orderTable.addTableToPage();
        var updateButton = document.getElementById("update");
        updateButton.addEventListener("click", this.updateInventory);
        this.header.addHeaderToPage();

    }

    /**
     * Method to run when the update button is pressed. Call the MusicPlaylistService to update the inventory.
     */
    async updateInventory(event) {
        event.preventDefault();
        console.log("hello from updateInventory method")
        const form = document.getElementById("update-inventory-form");
        const beerId = form.elements["beerId"].value;
        const packagingType = form.elements["packagingType"].value;
        const availableUnits = form.elements["availableUnits"].value;
        const reservedUnits = form.elements["reserveUnits"].value;
        const unitPrice = form.elements["unitPrice"].value;

        try {
            const updateRequest = await this.client.updateInventoryItem(beerId, packagingType, availableUnits, reservedUnits, unitPrice);
            alert('Item updated successfully!');
        } catch (error) {
            console.error(error);
            alert('Error updating item. See console for details.');
        }
    }
}

const main = async () => {
    const inventory = new Inventory();
    inventory.mount();
};

window.addEventListener('DOMContentLoaded', main);