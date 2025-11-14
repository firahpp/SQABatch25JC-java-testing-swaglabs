# Test Case: Login Functionality - SauceDemo

## Objective
To verify the login functionality of the SauceDemo website (https://www.saucedemo.com/) with various valid and invalid credentials.

## Pre-conditions
- A web browser is installed and accessible.
- Internet connection is stable.
- The SauceDemo website is accessible at `https://www.saucedemo.com/`.

## Test Data

| Username       | Password     | Expected Result                               |
| :------------- | :----------- | :-------------------------------------------- |
| `standard_user`| `secret_sauce` | Successful login, redirected to products page |
| `locked_out_user`| `secret_sauce` | Error message: "Epic sadface: Sorry, this user has been locked out." |
| `problem_user` | `secret_sauce` | Successful login, redirected to products page (with visual issues) |
| `performance_glitch_user` | `secret_sauce` | Successful login, redirected to products page (with performance delay) |
| `invalid_user` | `secret_sauce` | Error message: "Epic sadface: Username and password do not match any user in this service" |
| `standard_user`| `invalid_password` | Error message: "Epic sadface: Username and password do not match any user in this service" |
| (empty)        | `secret_sauce` | Error message: "Epic sadface: Username is required" |
| `standard_user`| (empty)        | Error message: "Epic sadface: Password is required" |
| (empty)        | (empty)        | Error message: "Epic sadface: Username is required" |

## Test Scenarios

### Test Case 1: Successful Login with Valid Credentials

**Description:** Verify that a user can successfully log in with valid username and password.

**Steps:**
1. Navigate to `https://www.saucedemo.com/`.
2. Enter `standard_user` into the "Username" field.
3. Enter `secret_sauce` into the "Password" field.
4. Click the "Login" button.

**Expected Result:**
- The user is redirected to the "Products" page (`https://www.saucedemo.com/inventory.html`).
- The URL changes to `https://www.saucedemo.com/inventory.html`.
- The "Products" title is visible.

### Test Case 2: Login with Invalid Username

**Description:** Verify that an appropriate error message is displayed when attempting to log in with an invalid username.

**Steps:**
1. Navigate to `https://www.saucedemo.com/`.
2. Enter `invalid_user` into the "Username" field.
3. Enter `secret_sauce` into the "Password" field.
4. Click the "Login" button.

**Expected Result:**
- An error message "Epic sadface: Username and password do not match any user in this service" is displayed.
- The user remains on the login page.

### Test Case 3: Login with Invalid Password

**Description:** Verify that an appropriate error message is displayed when attempting to log in with an invalid password.

**Steps:**
1. Navigate to `https://www.saucedemo.com/`.
2. Enter `standard_user` into the "Username" field.
3. Enter `invalid_password` into the "Password" field.
4. Click the "Login" button.

**Expected Result:**
- An error message "Epic sadface: Username and password do not match any user in this service" is displayed.
- The user remains on the login page.

### Test Case 4: Login with Locked Out User

**Description:** Verify that a specific error message is displayed for a locked-out user.

**Steps:**
1. Navigate to `https://www.saucedemo.com/`.
2. Enter `locked_out_user` into the "Username" field.
3. Enter `secret_sauce` into the "Password" field.
4. Click the "Login" button.

**Expected Result:**
- An error message "Epic sadface: Sorry, this user has been locked out." is displayed.
- The user remains on the login page.

### Test Case 5: Login with Empty Username

**Description:** Verify that an error message is displayed when the username field is left empty.

**Steps:**
1. Navigate to `https://www.saucedemo.com/`.
2. Leave the "Username" field empty.
3. Enter `secret_sauce` into the "Password" field.
4. Click the "Login" button.

**Expected Result:**
- An error message "Epic sadface: Username is required" is displayed.
- The user remains on the login page.

### Test Case 6: Login with Empty Password

**Description:** Verify that an error message is displayed when the password field is left empty.

**Steps:**
1. Navigate to `https://www.saucedemo.com/`.
2. Enter `standard_user` into the "Username" field.
3. Leave the "Password" field empty.
4. Click the "Login" button.

**Expected Result:**
- An error message "Epic sadface: Password is required" is displayed.
- The user remains on the login page.

### Test Case 7: Login with Empty Username and Password

**Description:** Verify that an error message is displayed when both username and password fields are left empty.

**Steps:**
1. Navigate to `https://www.saucedemo.com/`.
2. Leave both "Username" and "Password" fields empty.
3. Click the "Login" button.

**Expected Result:**
- An error message "Epic sadface: Username is required" is displayed.
- The user remains on the login page.

---

# Test Case: Inventory Functionality - SauceDemo

## Objective
To verify the core functionality of the inventory/product page, including adding items to the cart, removing items, sorting, and state management. This is an integration test focusing on how these features interact.

## Pre-conditions
- User has successfully logged in as `standard_user`.
- User is on the inventory page (`https://www.saucedemo.com/inventory.html`).

## Test Scenarios (Positive)

### Test Case INV-01: Add a single item to cart

**Description:** Verify that a user can add a single item to the shopping cart from the inventory page.

**Steps:**
1. On the inventory page, locate the "Sauce Labs Backpack".
2. Click the "Add to cart" button for this item.

**Expected Result:**
- The button text for "Sauce Labs Backpack" changes from "Add to cart" to "Remove".
- The shopping cart icon in the top-right corner displays a badge with the number "1".

### Test Case INV-02: Add multiple items to cart

**Description:** Verify that the cart correctly reflects the addition of multiple items.

**Steps:**
1. Click "Add to cart" for "Sauce Labs Backpack".
2. Click "Add to cart" for "Sauce Labs Bike Light".
3. Click "Add to cart" for "Sauce Labs Bolt T-Shirt".

**Expected Result:**
- The buttons for all three items show "Remove".
- The shopping cart badge displays the number "3".

### Test Case INV-03: Remove an item from the cart via inventory page

**Description:** Verify that an item can be removed from the cart from the inventory page.

**Steps:**
1. Add "Sauce Labs Backpack" to the cart.
2. The cart badge should show "1".
3. Click the "Remove" button for "Sauce Labs Backpack".

**Expected Result:**
- The button text for "Sauce Labs Backpack" changes back to "Add to cart".
- The shopping cart badge disappears (or shows "0").

### Test Case INV-04: Sort products by Name (A to Z)

**Description:** Verify that the products can be sorted alphabetically by name.

**Steps:**
1. Click the sort dropdown menu.
2. Select "Name (A to Z)".

**Expected Result:**
- The product list is re-ordered.
- The first item displayed is "Sauce Labs Backpack".
- The last item displayed is "Test.allTheThings() T-Shirt (Red)".

### Test Case INV-05: Sort products by Price (low to high)

**Description:** Verify that the products can be sorted by price from lowest to highest.

**Steps:**
1. Click the sort dropdown menu.
2. Select "Price (low to high)".

**Expected Result:**
- The product list is re-ordered.
- The first item displayed is "Sauce Labs Onesie" ($7.99).
- The last item displayed is "Sauce Labs Fleece Jacket" ($49.99).

## Test Scenarios (Negative & Integration)

### Test Case INV-06: Cart state persists after sorting

**Description:** Verify that items added to the cart remain in the cart after the product list is sorted.

**Steps:**
1. Add "Sauce Labs Bolt T-Shirt" to the cart.
2. Verify the cart badge shows "1".
3. Click the sort dropdown and select "Price (high to low)".

**Expected Result:**
- The product list is re-ordered.
- The cart badge still shows "1".
- The button for "Sauce Labs Bolt T-Shirt" still shows "Remove".

### Test Case INV-07: Reset App State clears the cart

**Description:** Verify that using the "Reset App State" option from the sidebar menu correctly empties the cart.

**Steps:**
1. Add "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart.
2. Verify the cart badge shows "2".
3. Click the burger menu icon in the top-left corner.
4. Click the "Reset App State" link.
5. Close the sidebar by clicking the 'X' button.

**Expected Result:**
- The shopping cart badge disappears.
- The buttons for "Sauce Labs Backpack" and "Sauce Labs Bike Light" revert to "Add to cart".

### Test Case INV-08: Add all items and then remove all items

**Description:** Verify that the cart behaves correctly when all items are added and subsequently removed from the inventory page.

**Steps:**
1. Click "Add to cart" for all 6 items on the page.
2. Verify the cart badge shows "6".
3. Click "Remove" for all 6 items on the page.

**Expected Result:**
- The cart badge disappears.
- All 6 product buttons show "Add to cart".