import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewOrder extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addOrderToPage', 'addItemsToPage', 'addItem'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addOrderToPage);
        this.dataStore.addChangeListener(this.addItemsToPage);
        this.header = new Header(this.dataStore);
        console.log("viewOrder constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const orderId = urlParams.get('id');
        document.getElementById('customer-name').innerText = "Loading Order ...";
        const order = await this.client.getOrder(orderId);
        this.dataStore.set('order', order);
        document.getElementById('items').innerText = "(loading items...)";
        const items = await this.client.getOrderItems(orderId);
        this.dataStore.set('items', items);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('add-item').addEventListener('click', this.addItem);
        this.client = new MusicPlaylistClient();
        this.clientLoaded();
    }

    /**
     * When the playlist is updated in the datastore, update the playlist metadata on the page.
     */
    addOrderToPage() {
        const order = this.dataStore.get('order');
        if (order == null) {
            return;
        }

        document.getElementById('order-id').innerText = order.id;
        document.getElementById('customer-name').innerText = order.customerName;

    }

    /**
     * When the songs are updated in the datastore, update the list of songs on the page.
     */
    addItemsToPage() {
        const items = this.dataStore.get('items')

        if (items == null) {
            return;
        }

        let itemHtml = '';
        let item;
        for (item of items) {
            itemHtml += `
                <li class="item">
                    <span class="beerName">${item.beerName}</span>
                    <span class="beerId">${item.beerId}</span>
                    <span class="quantity">${item.quantity}</span>
                    <span class="packagingType">${item.packagingType}</span>
                </li>
            `;
        }
        document.getElementById('items').innerHTML = itemHtml;
    }

    /**
     * Method to run when the add song playlist submit button is pressed. Call the MusicPlaylistService to add a song to the
     * playlist.
     */
    async addItem() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const order = this.dataStore.get('order');
        if (order == null) {
            return;
        }

        document.getElementById('add-item').innerText = 'Adding...';
        const beerName = document.getElementById('beerName').value;
        const beerId = document.getElementById('beerId').value;
        const quantity = document.getElementById('quantity').value;
        const packagingType = document.getElementById('packagingType').value;
        const orderId = order.id;

        const itemList = await this.client.addSongToPlaylist(orderId, beerName, beerId, quantity, packagingType, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('items', itemList);

        document.getElementById('add-item').innerText = 'Add Item';
        document.getElementById("add-item-form").reset();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewOrder = new ViewOrder();
    viewOrder.mount();
};

window.addEventListener('DOMContentLoaded', main);
