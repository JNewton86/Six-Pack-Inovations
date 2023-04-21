import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import Table from '../components/table';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class CreateOrder extends BindingClass{
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewOrder'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewOrder);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new MusicPlaylistClient();
    }

    /**
     * Method to run when the create playlist submit button is pressed. Call the MusicPlaylistService to create the
     * playlist.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const beginOrderButton = document.getElementById('begin');
        const origButtonText = beginOrderButton.innerText;
        beginOrderButton.innerText = 'Loading...';

        const customerName = document.getElementById('customer-name').value;

        const order = await this.client.createOrder(customerName, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('order', order);
    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    redirectToViewOrder() {
        const order = this.dataStore.get('order');
        if (order != null) {
            window.location.href = `/order.html?id=${order.id}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createOrder = new CreateOrder();
    createOrder.mount();
};

window.addEventListener('DOMContentLoaded', main);








