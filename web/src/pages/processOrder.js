import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";
import OrderTable from '../components/orderTable';
import DataStore from "../util/DataStore";
import Header from '../components/header';




/**
 * Logic needed for the view table page of the website.
 */
class ProcessOrder extends BindingClass {
    constructor() {
      super();
  
      this.bindClassMethods(["mount", "processOrder"], this);
  
      this.dataStore = new DataStore();
      this.orderTable = new OrderTable(this.dataStore);
      this.client = new MusicPlaylistClient();
      this.header = new Header(this.dataStore);
    }
  
    mount() {
      console.log("processOrder.js mounting...");
      this.orderTable.addTableToPage();
      const updateButton = document.getElementById("update");
      updateButton.addEventListener("click", this.processOrder); // updated line
      this.header.addHeaderToPage();
    }
  
    async processOrder(event) {
      event.preventDefault();
      console.log("hello from processOrders method");
      const form = document.getElementById("process-order-form");
      const orderId = form.elements["orderId"].value;
  
      try {
        const updateRequest = await this.client.processOrder(orderId);
        alert("Order processed successfully!");
      } catch (error) {
        console.error(error);
        alert("Error processing order. See console for details.");
      }
    }
  }
  
  const main = async () => {
    const processOrder = new ProcessOrder();
    processOrder.mount();
  };
  
  window.addEventListener("DOMContentLoaded", main);
  