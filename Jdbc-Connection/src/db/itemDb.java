package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import business.Item;
import interfaces.DAO;

public class itemDb implements DAO<Item>{
	
	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/item_db";
		String username = "item_user";
		String pwd = "sesame";
		Connection conn = DriverManager.getConnection(dbUrl, username, pwd);
		return conn;
	}

	@Override
	public Item get(int id) {
		Item item = null;
		String sql = "SELECT * FROM item WHERE id = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				item = getItemFromRow(rs);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public List<Item> getAll() {
		
		List<Item> items = new ArrayList<>();
		
		try (Statement stmt = getConnection().createStatement()){	// Try with resources statement
			ResultSet rs = stmt.executeQuery("SELECT * FROM item order by id");
			
			while (rs.next()) { // While there is a next line to get. For each row parse an item.
				Item item = getItemFromRow(rs);
				items.add(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
	}

	private Item getItemFromRow(ResultSet rs) throws SQLException {
		int id = rs.getInt(1); // Column 1
		String desc = rs.getString(2);	// Column 2
		Item item = new Item(id,desc);
		return item;
	}

	@Override
	public boolean add(Item item) {
		boolean success = false;
		String sql = "INSERT INTO item (description) values (?)";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
			stmt.setString(1, item.getDescription());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 1) {
				success = true;
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean update(Item item) {
		boolean success = false;
		String sql = "UPDATE item SET description = ? where id = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
			stmt.setString(1, item.getDescription());
			stmt.setInt(2, item.getId());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 1) {
				success = true;
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delete(Item item) {
		boolean success = false;
		String sql = "DELETE FROM item WHERE id = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
			stmt.setInt(1, item.getId());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 1) {
				success = true;
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

}
