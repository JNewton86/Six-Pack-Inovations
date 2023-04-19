import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import Table from '../components/table';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class CreateOrder extends BindingClass{
    constructor(){
        super();
        
        this.bindClassMethods(['mount', 'submit', 'redirectToViewPlaylist'], this);

        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewOrder);
        this.table = new Table(this.dataStore);

        this.client = new MusicPlaylistClient();

    }

    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('Submit Order');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Processing Order...';

        const newOrder = document.getElementById('new-Order').value;
        const ordering = await this.client.createOrder(newOrder, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('ordering', ordering);
    }

    redirectToViewOrder() {
        const order = this.dataStore.get('order');
        if (order != null){
            // view order page needs to go here
            window.location.href = `/viewOrder.html?id=${order.id}`
        }
    }

    reset(){
         document.getElementById("clear").value = "";
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







