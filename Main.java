import java.util.*;

/* Inventory application                   
   Added feature:                         
   The ability to create warehouses and   
   store inventory in specific warehouses 
   By Nicholas Rachfal                    */
 
class Item 
{
	private String itemName; 
	
  /* Features to consider adding in the future:
    private string itemDescription  
    private int itemSerialNumber 
    private string itemCategory     */
                 

	Item(String itemName) 
  {
		this.itemName = itemName;
	}
	
  public void setItem(String itemName)
  {
    this.itemName = itemName; 
  }

	public String getItem() 
  {
		return this.itemName; 
	}
}

class Warehouse 
{
  private String warehouseName;

  private List<Item> warehouseItems = new ArrayList<>(); 

  /* Features to consider adding in the future:
    private string warehouseLocation  
    private int buildingID 
    private int warehouseTelephone   */

  Warehouse(String warehouseName) 
  {
    this.warehouseName = warehouseName; 
  }

  public String getName() 
  {
    return this.warehouseName; 
  }

  public void addItem(Item item) 
  {
    warehouseItems.add(item); 
  }

  public List<Item> getItems() 
  {
    return this.warehouseItems; 
  }
}

class Utilities 
{

// function that prints user commands
public void displayMenu() 
{
		
		System.out.println(
           "\nMENU\n"
         + "---------------------------\n"
         + "Enter \"c  itemName\" to add item\n"
				 + "Enter 'r' to view inventory\n"
				 + "Enter \"u itemName\" to edit item\n"
				 + "Enter \"d itemName\" to delete item\n"
         + "Enter \"w warehouseName\" to create warehouse\n"
         + "Enter \"i warehouseNumber itemName\" to add item to warehouse\n"
         + "Enter 'v' to view all warehouses\n"
         + "Enter \"s warehouseNumber\" to view inventory at specific warehouse\n"
				 + "Enter 'x' to exit application\n");
	}

// function that prints the general inventory
public void displayInventory(List<Item> items)
 {
    System.out.println("Current Inventory:"); 
  	for(int i = 0; i < items.size(); i++) 
    {
				System.out.println( i + ". " + items.get(i).getItem());
		}
}

// function that overwrites an item obect in the general inventory
// prints an error message if the item doesn't exist
public void editItem(List<Item> items, Scanner scan, String input) 
{
    try 
    {
       int itemNumber = Integer.parseInt(input.substring(2));
       Item oldItem = items.get(itemNumber); 
       System.out.println("Please enter the new item:"); 
       String newItem = scan.nextLine();
       oldItem.setItem(newItem);  
       System.out.println("Item successfully edited!"); 
    } 
    catch (Exception e) 
    {
       System.out.println("Please enter a valid item number after the command.\n"
                        + "Use command 'r' to view item numbers."); 
    }
}

// function that removes an item object from the general inventory
// prints an error message if the item doesn't exist
public void deleteItem(List<Item> items, String input) 
{
    try 
    {
       int itemNumber = Integer.parseInt(input.substring(2));
       items.remove(itemNumber);
       System.out.println("Item successfully deleted!"); 
    } 
    catch (Exception e) 
    {
       System.out.println("Please enter a valid item number after the command.\n"
                        + "Use command 'r' to view item numbers."); 
    }
}

// function that adds an item object to the general inventory
// prints an error message if no item was given from user
public void addItem(List<Item> items, String input) 
{
    try 
    {
    String itemName = input.substring(2); 
		Item item = new Item(itemName);
		items.add(item);
    System.out.println("Item successfully added!");  
    } 
    catch (Exception e) 
    {
      System.out.println("Please enter an item after the command");  
    }
}

// function that adds a warehouse object to a list of warehouses
public void createWarehouse(List<Warehouse> warehouses, String input) 
{
  String warehouseName = input.substring(2); 
  Warehouse building = new Warehouse(warehouseName);
  warehouses.add(building);
  System.out.println("Warehouse successfully created!");
}

// function that adds a warehouse item object to the corresponding warehouse
// prints an error message if the target warehouse doesn't exist
public void addWarehouseItem(List<Warehouse> warehouses, String input)
{
  try 
  {
  int warehouseNumber = Integer.parseInt(input.substring(2,3)); 
  String newItem = input.substring(4);
  Item warehouseItem = new Item(newItem);
  warehouses.get(warehouseNumber).addItem(warehouseItem); 
  System.out.println("Item successfully added to warehouse"); 
  } 
  catch (Exception e) 
  {
    System.out.println("Please enter a valid warehouse number followed by the item you'd like to add.\n"
                      +"Use command 'v' to view warehouse numbers.");
  }
}

// function that prints the current list of warehouses
public void displayWarehouses(List<Warehouse> warehouses) 
{
  System.out.println("Current Warehouses:"); 
  for(int i = 0; i < warehouses.size(); i++) 
  {
    System.out.println(i + ". " + warehouses.get(i).getName()); 
  }
}

// function that prints the current list of items at at a specific warehouse
// prints an error message if target warehouse doesn't exist
public void displayWarehouseItems(List<Warehouse> warehouses, String input)
{
    try 
    {
       int warehouseNumber = Integer.parseInt(input.substring(2));
       List<Item> targetWarehouse = warehouses.get(warehouseNumber).getItems();
       
       System.out.println("Current Inventory at warehouse '" + warehouses.get(warehouseNumber).getName() + "':"); 

       for(int i = 0; i < targetWarehouse.size(); i++) {
         System.out.println(i + ". " + targetWarehouse.get(i).getItem()); 
      }
    } 
    catch (Exception e) 
    {
       System.out.println("Please enter a valid warehouse number after the command.\n"
                          + "Use command 'v' to view warehouse numbers."); 
    }
}
}

class Main {

  public static void main(String[] args) 
  {
    Utilities util= new Utilities();
		List<Item> items = new ArrayList<>();
    List<Warehouse> warehouses = new ArrayList<>(); 
		Scanner scan = new Scanner(System.in); 
		
		System.out.println("Welcome to the Shopify inventory application!\n"); 
		util.displayMenu();
		
		String input = scan.nextLine();
    
    char cmd = input.charAt(0); 

		while(cmd != 'x') {
			
			if(cmd == 'c') 
      {
        util.addItem(items, input); 
			} 
      else if (cmd == 'r')
      {
        util.displayInventory(items); 
			} 
      else if (cmd == 'u') 
      {
        util.editItem(items, scan, input);
         
      }
      else if (cmd == 'd') 
      {
        util.deleteItem(items, input); 
      } 
      else if(cmd == 'w') {
        util.createWarehouse(warehouses, input);
      }
      else if(cmd == 'v') {
        util.displayWarehouses(warehouses);
      }
      else if (cmd == 'i') {
        util.addWarehouseItem(warehouses, input);
      }
      else if (cmd == 's') {
        util.displayWarehouseItems(warehouses, input);
      }
      else 
      {
        System.out.println("Please enter a valid command"); 
      }
      
			util.displayMenu();
      input = scan.nextLine();
      cmd = input.charAt(0); 
		}
		

	}
}