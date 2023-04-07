# Design Document

## Instructions

_Replace italicized text (including this text!) with details of the design you are proposing for your team project. (Your replacement text shouldn't be in italics)._

_You should take a look at the [example design document](example-design-document.md) in the same folder as this template for more guidance on the types of information to capture, and the level of detail to aim for._

## _Project Title_ Design

## 1. Problem Statement

-Managing the inventory of a brewery.
-Managing the orders that a brewery receives for their product.
-Tracking sales/profits of orders from customers over a period of time.
-Managing ingredients malt, barley, hops, water, etc.

## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that you might like help working through._

1. Which project structure do we want to steal?
2. What is javascript? Frontend, how do we do that?
3. What is the scope of our project? Do we go full into marketplace/payment systems? Or do we just keep it at an inventory level?

## 3. Use Cases

_This is where we work backwards from the customer and define what our customers would like to do (and why). You may also include use cases for yourselves (as developers), or for the organization providing the product to customers._

U1. _As a [product] customer, I want to `be able to view a table to check current total inventory level by beer type` when I `open the inventory menu`_

U2. _As a [product] customer, I want to `be able to view a table to check current available inventory level by beer type` when I `open the inventory menu`_

U3. _As a [product] customer, I want to `be able to view a table to check current reserved inventory level by beer type` when I `open the inventory menu`_

U4. _As a [product] customer, I want to `be able to view a specific page for a beer type' when 'I click on the beer name from the inventory menu'_

U5. _As a [product] customer, I want to `add inventory to either kegs or cases` when I `open the specific beer type's page`_

U6. _As a [product] customer, I want my clients to be able to place an order for a specific beer type by kegs, cases or both_

U7. _As a product customer, I want the available inventory and reserved inventory to update when a client places an order from us.

U8. _As a customer, I would like orders to be manually processed by an employee after a client places an order.

U9. _As a customer, I would like clients to be automatically informed when their orders are processed.

U10. _As a customer, I would like clients to be able to view the process of their orders on a client page.

U11. _As a customer, I would like to be able to view all pending orders for our product.

U12. _As a customer, I would like to be able to view order history by time.

U13. _As a customer, I would like to be able to press a button on the pending orders page to indicate an order has been processed.

## 4. Project Scope

_Clarify which parts of the problem you intend to solve. It helps reviewers know what questions to ask to make sure you are solving for what you say and stops discussions from getting sidetracked by aspects you do not intend to handle in your design._

Our project solves 3 main problems:
1. It tracks inventory for our product.
2. It allows our customers to place orders (and updates inventory with it).
3. It allows us to manually process orders from our customers.

### 4.1. In Scope

_Which parts of the problem defined in Sections 1 and 2 will you solve with this design? This should include the base functionality of your product. What pieces are required for your product to work?_
Pieces are:
  Inventory page
    Available inventory table
    Reserved inventory table
    Total inventory table made in our app
  Client page
    Orders placed 
    Current order status
  Orders page
    Pending orders
    Processed button
    Order history
  We also need separate clients for the brewery and the customer

_The functionality described above should be what your design is focused on. You do not need to include the design for any out of scope features or expansions._

### 4.2. Out of Scope

_Based on your problem description in Sections 1 and 2, are there any aspects you are not planning to solve? Do potential expansions or related problems occur to you that you want to explicitly say you are not worrying about now? Feel free to put anything here that you think your team can't accomplish in the unit, but would love to do with more time._

  Ingredient tracking
  Processing the brewing side of things
  Automatically updated inventory with brewing
  Packaging tracking
  Payment processor implementation

_The functionality here does not need to be accounted for in your design._

# 5. Proposed Architecture Overview

_Describe broadly how you are proposing to solve for the requirements you described in Section 2. This may include class diagram(s) showing what components you are planning to build. You should argue why this architecture (organization of components) is reasonable. That is, why it represents a good data flow and a good separation of concerns. Where applicable, argue why this architecture satisfies the stated requirements._

https://github.com/nss-se-cohort-02/u5-project-team-6-six-pack-innovations/blob/main/resources/SixPackInnovationsCD.puml


# 6. API

## 6.1. Public Models

_Define the data models your service will expose in its responses via your *`-Model`* package. These will be equivalent to the *`PlaylistModel`* and *`SongModel`* from the Unit 3 project._
OrderModel
OrderItemModel
BeerModel

## 6.2. _Get Available Inventory_
Accepts GET requests to /availableinventory:id
Accpet an id to check the inventory
if Id is not found, will throw an BeerNotFoundException


## 6.3 _Update Available Inventory_
Accepts PUT requests to /availableinventory:beer object
-returns the updated available inventory
Accepts a beer object
if Id is not found, will throw BeerNotFoundException
if quantity invalid will throw InvalidAttributeException

## 6.4 _Search Available Inventory_
**Drop down menu to solve this endpoint??
Accepts a GET request to /searchavailableinventory : String 
(This will be a beer type ENUM) 
If String is not found, will throw a BeerNotFoundException
If String is null, will throw a InvalidAttributeException

## 6.5 _Get Reserved Inventory_
Accepts GET requests to /reservedinventory:id
Accpet an id to check the inventory
if Id is not found, will throw an BeerNotFoundException

## 6.6 _Update Reserved Inventory_
Accepts PUT requests to /reservedinventory:beer object
-returns the updated reserved inventory
Accepts a beer object
if Id is not found, will throw BeerNotFoundException
if quantity invalid will throw InvalidAttributeException

## 6.7 _Create Order Lambda_
Accepts a POST request to /orders : order Object
- returns a unique order Id implemented by the service
Accepts an order object

## 6.8 _Get Total Inventory_
Accepts a GET request to /inventory : ??
- returns all beer items from the list from both available and reserved inventory tables
If no beers are in table will throw OutOfStockException

## 6.9 _Update Order_
Accepts a PUT request to /orders : order object
- returns order 
**Notifies client that the order status has changed

## 6.10 _Get Order_
Accepts a GET request to /orders : string (orderId)
- returns order
If order is not found returns OrderNotFoundException


# 7. Tables

_Define the DynamoDB tables you will need for the data your service will use. It may be helpful to first think of what objects your service will need, then translate that to a table structure, like with the *`Playlist` POJO* versus the `playlists` table in the Unit 3 project._

TABLES:
available_inventory
id //partition key, string
type // sort key, string
name // string
units // number

reserved_inventory
id // partition key, string
type // sort key, string
name // string
units // number

orders
id // partition key, string
customer_Id // string
order_items // string list
total_price // number

INDEX:
orders
customer_Id // partition key, string
id // string
order_items // string list
total_price // number

# 8. Pages

_Include mock-ups of the web pages you expect to build. These can be as sophisticated as mockups/wireframes using drawing software, or as simple as hand-drawn pictures that represent the key customer-facing components of the pages. It should be clear what the interactions will be on the page, especially where customers enter and submit data. You may want to accompany the mockups with some description of behaviors of the page (e.g. “When customer submits the submit-dog-photo button, the customer is sent to the doggie detail page”)_
