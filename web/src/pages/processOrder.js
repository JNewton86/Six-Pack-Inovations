import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";
import OrderTable from '../components/orderTable';
import DataStore from "../util/DataStore";




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
    }
  
    mount() {
      console.log("processOrder.js mounting...");
      this.orderTable.addTableToPage();

      // Create the button
      const updateButton = document.createElement("button");
      updateButton.type = "button";
      updateButton.className = "button-bordered";
      updateButton.id = "update";
      updateButton.innerText = "Update";

      // Add event listener to the button
      updateButton.addEventListener("click", this.processOrder);

      // Add the button to the form
      const formGroup = document.querySelector(".form-group.row");
      formGroup.appendChild(updateButton);
  }
  
    async processOrder(event) {
      event.preventDefault();
      console.log("hello from processOrders method");
      const form = document.getElementById("process-order-form");
      const orderId = form.elements["orderId"].value;
  
      try {
        const updateRequest = await this.client.processOrder(orderId, (error) => {
          beginOrderButton.innerText = origButtonText;
          errorMessageDisplay.innerText = `Error: ${error.message}`;
          errorMessageDisplay.classList.remove('hidden');
      });
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
  