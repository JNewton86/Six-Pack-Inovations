import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import Table from '../components/table';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class CreateOrder extends BindingClass{
    constructor(){
        super();
        
        this.bindClassMethods(['mount', 'submit', 'redirectToViewPlaylist'], this);

        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);

        this.table = new Table(this.dataStore);

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





        // const orderItem = document.getElementById('tags').value;

        // let tags;
        // if (tagsText.length < 1) {
        //     tags = null;
        // } else {
        //     tags = tagsText.split(/\s*,\s*/);
        // }

        const ordering = await this.client.CreateOrder(newOrder, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('ordering', ordering);
    }












}