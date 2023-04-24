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
    
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('submit').addEventListener('click', this.submit);

        this.client = new MusicPlaylistClient();
    }

    /**
     * Method to run when the create order submit button is pressed. Call the MusicPlaylistService to create the
     * order.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const beginOrderButton = document.getElementById('submit');
        beginOrderButton.innerText = 'Loading...';
        const origButtonText = beginOrderButton.innerText;

        const clientId = document.getElementById('customer-name').value;

        const order = await this.client.createOrder(clientId, (error) => {
            beginOrderButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('order', order);
    }

    /**
     * When the order is updated in the datastore, redirect to the view playlist page.
     */
    redirectToViewOrder() {
        const order = this.dataStore.get('order');
        if (order != null) {
            window.location.href = `/yay.html`;
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








