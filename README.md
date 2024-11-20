# POTS
FUCKCKCKCKCKCKCKC
## Outline
POTS - main file that runs <br>
DataAbstractions - all Data Related Classes and Files <br>
views - all Classes that use Swing (Calls Domain, data, etc that help create view) <br>

PO - Purchase Order (Related To a List of PR)
- POID - ID of Item
- UserID - ID of the User who Made the PO (Only the Purchase Manager and Admin can make)
- Status - Status of The PO (Pending / Approved / Paid)
- Date - Date The PO is made

POToPayment - List of POs related to the payment
- PaymentID - ID of Payment
- POID - ID of POs

PR - Purchase Requisition
- PRID - ID of PR
- SupplierID - ID of the supplier of the products in the PR
- UserID - ID of the user who made the PR (only Purchase Manager, Sales Manager, and Admin)
- Status - Status of PR (Pending / Approved)

PRToPO - List of PRs related to a PO
- POID - ID of PO
- PRID - ID of PR

Payment - All Payments Made to the Supplier
- PaymentID - ID of Payment
- BankReferenceID - ID to the Bank Transfer
- SupplierID - ID of Supplier Paid
- Date - Date of Payment
- Status - Status of the delivery (Pending, On The Way, Late, Arrived)

Product - All Products owned
- ProductID - ID of Product
- Name - Name of Product
- SupplierID - ID of Supplier
- Brand - Brand of Product
- Quantity - Quantity of Product
- Threshold - Number of products that must always be available
- PurchasePrice - Price given by Supplier
- SellingPrice - Price sold by our company
- Category - Category of Product

ProductToPR - List of Products related to a PR
- PRID - ID of PR
- ProductID - ID of Product
- Quantity - Quantity of Item Bought

ProductToSale - List of Products related to a Sale
- SalesID - ID of Sales
- ProductID - ID of Product
- Quantity - Quantity of Item Bought

Supplier - All Suppliers
- SupplierID - ID of Supplier
- Name - Name of Supplier
- Address - Address of Supplier

User - List of All User Accounts In The Company
- Username - username of the account
- FirstName - first name of the user
- LastName - last name of user
- Password- password of the user
- Role - role of user (Inventory Manager, Sales Manager, Purchase Manager, Finance Manager, Admin)
