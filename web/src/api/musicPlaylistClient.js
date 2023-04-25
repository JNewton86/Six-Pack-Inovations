import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class MusicPlaylistClient extends BindingClass {

    constructor(props = {}) {
        super();
        //Methods found in this class
        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getPlaylist', 'getPlaylistSongs', 'createPlaylist', 'updateInventoryItem', 'getAllOrderData'];
        this.bindClassMethods(methodsToBind, this);

        //this is the login
        this.authenticator = new Authenticator();;
        this.props = props;

        //axios handles the request and response data
        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Gets the playlist for the given ID.
     * @param id Unique identifier for a playlist
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The playlist's metadata.
     */
    async getPlaylist(id, errorCallback) {
        try {
            const response = await this.axiosClient.get(`playlists/${id}`);
            return response.data.playlist;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Get the songs on a given playlist by the playlist's identifier.
     * @param id Unique identifier for a playlist
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of songs on a playlist.
     */
    async getPlaylistSongs(id, errorCallback) {
        try {
            const response = await this.axiosClient.get(`playlists/${id}/songs`);
            return response.data.songList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Create a new playlist owned by the current user.
     * @param name The name of the playlist to create.
     * @param tags Metadata tags to associate with a playlist.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The playlist that has been created.
     */
    async createPlaylist(name, tags, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create playlists.");
            const response = await this.axiosClient.post(`playlists`, {
                name: name,
                tags: tags
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.playlist;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    /**
     * Creates the order, what are we doing for customer ID?.
     * @param id Unique identifier for a order
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The order's metadata.
     */
  async createOrder(clientId, orderItems, errorCallback) {
    try {
      const response = await this.axiosClient.post(`orders`, {
        orderId: '',
        clientId: clientId,
        orderItems: orderItems,
        orderProcessed: false
      });
      return response.data;
    } catch (error) {
      this.handleError(error, errorCallback);
    }
  }

  /**
   * Add a song to a playlist.
   * @param id The id of the playlist to add a song to.
   * @param asin The asin that uniquely identifies the album.
   * @param trackNumber The track number of the song on the album.
   * @returns The list of songs on a playlist.
   */
  async addItemToOrder(beerId, beerName, packagingType, quantity, errorCallback) {
    try {
      const response = await this.axiosClient.post(`orders/${id}/items`, {
        beerId: beerId,
        beerName: beerName,
        packagingType: packagingType,
        quantity: quantity,
      });
      return response.data.itemList;
    } catch (error) {
      this.handleError(error, errorCallback)
    }
  }

    /**
     * Add a song to a playlist.
     * @param id The id of the playlist to add a song to.
     * @param asin The asin that uniquely identifies the album.
     * @param trackNumber The track number of the song on the album.
     * @returns The list of songs on a playlist.
     */
    async addSongToPlaylist(id, asin, trackNumber, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can add a song to a playlist.");
            const response = await this.axiosClient.post(`playlists/${id}/songs`, {
                id: id,
                asin: asin,
                trackNumber: trackNumber
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.songList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Search for a soong.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The playlists that match the search criteria.
     */
    async search(criteria, errorCallback) {
        try {
            const queryParams = new URLSearchParams({ q: criteria })
            const queryString = queryParams.toString();

            const response = await this.axiosClient.get(`playlists/search?${queryString}`);

            return response.data.playlists;
        } catch (error) {
            this.handleError(error, errorCallback)
        }

    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
/**
get all order data for order table
  */
   async getAllOrderData() {
     try {
         const response = await this.axiosClient.get(`/orders/`);
         const orders = response.data.orders.map(order => {
             const { id: orderId, clientId, orderItems, orderProcessed: orderProcessed } = order;
             return {
                 orderId,
                 clientId,
                 orderItems,
                 orderProcessed
             };
         });
         return orders;
     } catch (error) {
         this.handleError(error, errorCallback);
     }
   }

    /**
     * Update Inventory item
     * @param beerId, packagingType, availableUnits, reservedUnits, and unitPrice.
     * @returns The list of songs on a playlist.
     */
//TODO
    async updateInventoryItem(beerId, packagingType, availableUnits, reserveUnits, unitPrice, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update inventory.");
            console.log("**this is my token** " + token)
            const response = await this.axiosClient.put(`inventory/${beerId}/${packagingType}`, {
                beerId: beerId,
                packagingType: packagingType,
                availableUnits: availableUnits,
                reservedUnits: reserveUnits,
                unitPrice: unitPrice,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    async getData() {
        const [ipaData, stoutData, porterData, lagerData] = await Promise.all([this.getStoutData(),this.getIPAData(), this.getPorterData(), this.getLagerData()]);

        return [...ipaData, ...stoutData, ...porterData, ...lagerData];
    }

    async getIPAData() {
      try {
        const response = await axios.get('/inventory/IPA');
        return response.data.beerModelList.map((beer) => {
          return {
            beerId: beer.beerId,
            beerType: beer.beerType,
            name: beer.name,
            packagingType: beer.packagingType,
            unitPrice: beer.unitPrice,
            availableUnits: beer.availableUnits,
            reservedUnits: beer.reservedUnits,
          };
        });
      } catch (error) {
        console.error(`Error fetching data: ${error}`);
        // Return some sample data
        return [
          {
            beerId: 1,
            beerType: 'IPA',
            name: 'Hoppy IPA',
            packagingType: 'Case',
            unitPrice: 20,
            availableUnits: 100,
            reservedUnits: 10,
          }
        ];
      }
    }



    async getStoutData() {
          try {
            const response = await axios.get('/inventory/Stout');
            return response.data.beerModelList.map((beer) => {
              return {
                beerId: beer.beerId,
                beerType: beer.beerType,
                name: beer.name,
                packagingType: beer.packagingType,
                unitPrice: beer.unitPrice,
                availableUnits: beer.availableUnits,
                reservedUnits: beer.reservedUnits,
              };
            });
          } catch (error) {
            console.error(`Error fetching data: ${error}`);
            // Return some sample data
            return [
              {
                beerId: 1,
                beerType: 'STOUT',
                name: 'Hoppy STOUT',
                packagingType: 'Case',
                unitPrice: 20,
                availableUnits: 100,
                reservedUnits: 10,
              }
            ];
          }
        }

    async getPorterData() {
          try {
            const response = await axios.get('/inventory/Porter');
            return response.data.beerModelList.map((beer) => {
              return {
                beerId: beer.beerId,
                beerType: beer.beerType,
                name: beer.name,
                packagingType: beer.packagingType,
                unitPrice: beer.unitPrice,
                availableUnits: beer.availableUnits,
                reservedUnits: beer.reservedUnits,
              };
            });
          } catch (error) {
            console.error(`Error fetching data: ${error}`);
            // Return some sample data
            return [
              {
                beerId: 1,
                beerType: 'PORTER',
                name: 'Hoppy PORTER',
                packagingType: 'Case',
                unitPrice: 20,
                availableUnits: 100,
                reservedUnits: 10,
              }
            ];
          }
        }

    async getLagerData() {
          try {
            const response = await axios.get('/inventory/Lager');
            return response.data.beerModelList.map((beer) => {
              return {
                beerId: beer.beerId,
                beerType: beer.beerType,
                name: beer.name,
                packagingType: beer.packagingType,
                unitPrice: beer.unitPrice,
                availableUnits: beer.availableUnits,
                reservedUnits: beer.reservedUnits,
              };
            });
          } catch (error) {
            console.error(`Error fetching data: ${error}`);
            // Return some sample data
            return [
              {
                beerId: 1,
                beerType: 'LAGER',
                name: 'Hoppy LAGER',
                packagingType: 'Case',
                unitPrice: 20,
                availableUnits: 100,
                reservedUnits: 10,
              }
            ];
          }
        }
        
        async processOrder(orderId) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can process orders.");
                console.log("**this is my token** " + token)
                const response = await this.axiosClient.put(`orders/${orderId}`, {
                    orderId: orderId,
                    orderProcessed: true,
                }, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'Content-Type': 'application/json'
                    }
                });
                console.log(response);
                window.location.reload();
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }
        
  
}

