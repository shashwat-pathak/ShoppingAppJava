/**
 * 
 */
package com.shashwat.mybasket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.shashwat.mybasket.DBOperations.DBUtilities;
import com.shashwat.mybasket.models.Cart;
import com.shashwat.mybasket.models.Order;
import com.shashwat.mybasket.models.User;
import com.shashwat.mybasket.services.CartService;
import com.shashwat.mybasket.services.LoginService;
import com.shashwat.mybasket.services.OrderService;
import com.shashwat.mybasket.services.ProductService;
import com.shashwat.mybasket.services.UserService;

/**
 * @author Shashwat Pathak
 *
 */
public class MyBasket {

	/**
	 * @param args
	 */
	static Scanner scan = new Scanner(System.in);
	static Cart cart = new Cart();
	static User user = new User();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startApp();
	}

	private static void startApp() {
		// TODO Auto-generated method stub
		int choice;
		System.out.println("########### Welcome to Mybasket App ############");
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("3. Exit App");
		System.out.println("Please select your choice (1-3):");
		try {
			choice = scan.nextInt();
			if (choice == 1) {
				displayLoginPrompt();
			} else if (choice == 2) {
				displaySignupPrompt();
			} else if (choice == 3) {
				System.exit(0);
			} else {
				System.out.println("Invalid Choice, Please Try again!\n");
				startApp();
			}
		} catch (InputMismatchException e) {
			System.out.println("Wrong Input - Please enter numeric values only!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void displayLoginPrompt() throws SQLException {
		// TODO Auto-generated method stub
		String username, password;
		System.out.println("Enter your username: ");
		username = scan.next();
		System.out.println("Enter your Password: ");
		password = scan.next();

		// set data in user pojo
		user.setUserName(username);
		user.setPassword(password);

		// check username and password from db
		LoginService loginService = new LoginService();
		ArrayList<Map<String, Object>> loggedInUser = loginService.loginUser(user);
		// System.out.println(loggedInUser);

		// if login successful then show the homepage
		if (loggedInUser.size() > 0) {
			// set the global user object
			user.setUserId(loggedInUser.get(0).get("USERID") + "");
			user.setAddress(loggedInUser.get(0).get("ADDRESS") + "");
			user.setPhoneNumber(loggedInUser.get(0).get("PHONE_NUMBER") + "");

			// Load the cart values from the db as well if present
			CartService cartService = new CartService();
			ArrayList<String> products = cartService.getCartFromDB(user.getUserId());
			if (products.size() > 0) {
				for (String product : products) {
					cart.setProductList(product);
				}
			}
			System.out.println("Login Successful! Welcome, " + user.getUserName() + "!");
			DBUtilities.insertData("Insert into loginhistory values ('" + user.getUserId() + "',sysdate)");
			displayHomePage();
		} else {
			System.out.println("Login Failed.. Please try again!");
			startApp();
		}
	}

	private static void displaySignupPrompt() throws SQLException {
		// TODO Auto-generated method stub
		String userName, password, address;
		String phonenumber;
		String userId = "U" + Math.round(Math.random() * 100000000);
		System.out.println("Enter your Username: ");
		userName = scan.next();
		System.out.println("Enter your Password: ");
		password = scan.next();
		System.out.println("Enter your Address: ");
		address = scan.next();
		System.out.println("Enter your Phone Number: ");
		phonenumber = scan.next();

		// set the information in user pojo
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPhoneNumber(phonenumber);
		user.setAddress(address);
		user.setPassword(password);

		// insert details into the table
		UserService userService = new UserService();
		Boolean status = userService.createUser(user);

		// if insert successful then show the homepage
		if (status) {
			System.out.println("*** Signup Successful ***");
			displayHomePage();
		} else {
			System.out.println("Signup failed, please try again!");
			startApp();
		}
	}

	private static void displayHomePage() throws SQLException {
		System.out.println("\n*********** HOMEPAGE *************");
		System.out.println("1. Start Shopping");
		System.out.println("2. View Cart");
		System.out.println("3. View Orders");
		System.out.println("4. My Details");
		System.out.println("5. Search and View User");
		System.out.println("6. Exit App");
		System.out.println("Please enter your choice: ");
		int choice = scan.nextInt();

		switch (choice) {
		case 1:
			displayCategoriesPage();
			break;
		case 2:
			displayCart();
			break;
		case 3:
			displayOrders();
			break;
		case 4:
			displayUserDetails();
			break;
		case 5:
			searchUser();
			break;
		case 6:
			System.exit(0);
			break;
		default:
			System.out.println("Please Enter a valid choice..");
			displayHomePage();
		}
	}

	private static void searchUser() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("******** Search User *********");
		String username;
		System.out.println("Enter the username you want to search: ");
		username = scan.next();
		UserService userService = new UserService();
		userService.searchUser(username);
	}

	private static void displayUserDetails() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("*** DETAILS ***");
		System.out.println("Username: " + user.getUserName());
		System.out.println("Address: " + user.getAddress());
		displayHomePage();
	}

	private static void displayOrders() throws SQLException {
		// TODO Auto-generated method stub
		OrderService orderService = new OrderService();
		orderService.displayOrders(user.getUserId());
		displayHomePage();
	}

	private static void displayCart() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("******** CART **********");
		ArrayList<String> cartItems = cart.getProductList();
		if (cartItems.size() <= 0) {
			System.out.println("No Items in cart yet!");
			displayHomePage();
		}
		for (String item : cartItems) {
			System.out.println(item);
		}

		CartService cartService = new CartService();
		int total = cartService.getCartTotalAmount(cartItems);
		System.out.println("Total: ₹" + total);
		System.out.println("Press 1 to checkout, press 0 to go to homepage: ");
		int choice = scan.nextInt();

		switch (choice) {
		case 0:
			displayHomePage();
			break;
		case 1:
			checkOutCart(total);
			break;
		default:
			System.out.println("Invalid Choice, Please try again!");
			displayCart();
		}
	}

	private static void checkOutCart(int total) throws SQLException {
		// TODO Auto-generated method stub
		String orderID = "ORD" + Math.round(Math.random() * 100000000);
		Order order = new Order();
		order.setOrderId(orderID);
		order.setUserId(user.getUserId());
		order.setTotal(total);

		CartService cartService = new CartService();
		Boolean status = cartService.checkout(order);
		if (status) {
			System.out.println("Check Out Successful!");
			cart.clearProductList();
			displayHomePage();
		} else {
			System.out.println("Check Out Failed..");
			displayHomePage();
		}
	}

	private static void displayCategoriesPage() throws SQLException {

		// TODO Auto-generated method stub
		int choice;
		System.out.println("******** CATEGORIES **********");
		// fetch categories from the db
		ProductService productService = new ProductService();
		ArrayList<String> categories = productService.getCategories();

		// Display the categories
		for (int i = 0; i < categories.size(); i++) {
			System.out.println((i + 1) + ". " + categories.get(i));
		}

		System.out.println("Please enter the category from above: ");
		choice = scan.nextInt();

		// based on choice show products of that category
		if (categories.get(choice - 1) != null) {
			displayCategoryProducts(categories.get(choice - 1));
		} else {
			System.out.println("Please enter valid choice!");
			displayHomePage();
		}

	}

	private static void displayCategoryProducts(String category) throws SQLException {
		// TODO Auto-generated method stub
		// get the products from the selected category
		ProductService productService = new ProductService();
		CartService cartService = new CartService();

		ArrayList<Map<String, Object>> products = productService.getCategoryProducts(category);
		if (products.size() > 0) {
			String choices;
			System.out.println("***** " + category.toUpperCase() + " *****");
			for (int i = 0; i < products.size(); i++) {
				System.out.println((i + 1) + ". " + products.get(i).get("PRODUCTNAME") + "  ₹"
						+ products.get(i).get("PRICE") + " - " + products.get(i).get("DESCRIPTION"));
			}
			System.out.println("Select the product from " + category
					+ " you wish to add to the cart \n(Please separate the choices with comma if needed): ");
			choices = scan.next();
			String[] productIndex = choices.split(",");
			System.out.println(productIndex[0]);

			for (int j = 0; j < productIndex.length; j++) {
				String product = (String) products.get(Integer.valueOf(productIndex[j]) - 1).get("PRODUCTNAME");
				if (cart.getProductList().contains(product)) {
					System.out.println(product + " already added in the cart");
				} else {
					cart.setProductList(product);
					cart.setUserId(user.getUserId());
				}
			}

			Boolean status = cartService.updateCart(cart);
			if (status) {
				System.out.println("Products added to Cart..");
			} else {
				System.out.println("Error adding to cart.. Please try again!");
			}
			displayHomePage();
		}
	}

}
