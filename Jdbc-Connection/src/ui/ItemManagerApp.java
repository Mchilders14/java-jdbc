package ui;

import com.util.Console;

import business.Item;
import db.itemDb;
import interfaces.DAO;
import text.ItemTextFile;

public class ItemManagerApp {
	private static final String ITEM_NOT_FOUND = "No item found for id: ";

	public static void main(String[] args) {
		System.out.println("Welcome to the Item Manager - Text File Version");
		System.out.println();
		
		// creating instance of ItemTextFile should create our file
		// on the first run of this app.
		DAO<Item> itemsDAO = new itemDb();
		
		int command = 0;
		while (command != 9) {
			displayMenu();
			command = Console.getInt("Command: ");
			System.out.println();
			
			switch (command) {
				case 1:
					// get all
					System.out.println("Get all Items");
					System.out.println("=============================");
					for (Item item: itemsDAO.getAll()) {
						System.out.println(item);
					}
					break;
				case 2:
					// get by id
					System.out.println("Get Item by ID");
					System.out.println("=============================");
					int id = Console.getInt("ID: ");
					Item item = itemsDAO.get(id);
					if (item != null) {
						System.out.println("Item Found: "+item);
					}
					else {
						System.out.println(ITEM_NOT_FOUND + id);
					}
					break;
				case 3:
					// add
					System.out.println("Add Item");
					System.out.println("=============================");
					//id = Console.getInt("ID: ");
					String desc = Console.getString("Description: ");
					if (itemsDAO.add(new Item(desc))) {
						System.out.println("Item added!");
					}
					else {
						System.out.println("Error adding item.");
					}
					break;
				case 4:
					// update
					System.out.println("Update Item");
					System.out.println("=============================");
					id = Console.getInt("ID: ");
					item = itemsDAO.get(id);
					if (item != null) {
						String newDesc = Console.getString("New description: ");
						item.setDescription(newDesc);
						itemsDAO.update(item);
						System.out.println("Item updated!");
					}
					else {
						System.out.println(ITEM_NOT_FOUND + id);
					}					
					break;
				case 5:
					// delete
					System.out.println("Delete an Item");
					System.out.println("=============================");
					id = Console.getInt("ID: ");
					item = itemsDAO.get(id);
					if (item != null) {
						itemsDAO.delete(item);
						System.out.println("Item Deleted: "+item);
					}
					else {
						System.out.println(ITEM_NOT_FOUND + id);
					}
					break;
				case 9:
					// exit
					break;
				default:
					System.out.println("Invalid command!  Try again.");
					break;
			}
		}		
		
		
		
		
		
		
		System.out.println("Bye");

	}
	
	private static void displayMenu() {
		System.out.println("COMMAND MENU:");
		System.out.println("====================");
		System.out.println("1 - Get all items");
		System.out.println("2 - Get an item");
		System.out.println("3 - Add an item");
		System.out.println("4 - Update an item");
		System.out.println("5 - Delete an item");
		System.out.println("9 - Exit");
		System.out.println();
	}
	

}