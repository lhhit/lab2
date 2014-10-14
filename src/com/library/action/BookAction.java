package com.library.action;
//first import te package
import com.library.DataBean.*;
import com.library.connDatabase.BookDB;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import java.sql.*;
import java.util.*;

import org.apache.struts2.ServletActionContext;


public class BookAction extends ActionSupport {
	
	private String author;
	private String index;
	private String tip;
	private String tipQuery;
	
	public String getTipQuery() {
		return tipQuery;
	}
	public void setTipQuery(String tipQuery) {
		this.tipQuery = tipQuery;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	private String bookTitle;
	private String newPublisher;
	private String newPublishDate;
	private double newPrice;
	private String newName;
	private String newISBN;
	private String newCountry;
	private String newAuthorID;
	private String newAge;

	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getNewPublisher() {
		return newPublisher;
	}
	public void setNewPublisher(String newPublisher) {
		this.newPublisher = newPublisher;
	}
	public String getNewPublishDate() {
		return newPublishDate;
	}
	public void setNewPublishDate(String newPublishDate) {
		this.newPublishDate = newPublishDate;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	
	public String getNewISBN() {
		return newISBN;
	}
	public void setNewISBN(String newISBN) {
		this.newISBN = newISBN;
	}
	public String getNewCountry() {
		return newCountry;
	}
	public void setNewCountry(String newCountry) {
		this.newCountry = newCountry;
	}
	public String getNewAuthorID() {
		return newAuthorID;
	}
	public void setNewAuthorID(String newAuthorID) {
		this.newAuthorID = newAuthorID;
	}
	public String getNewAge() {
		return newAge;
	}
	public void setNewAge(String newAge) {
		this.newAge = newAge;
	}
	//删除
	public String delete() {
		@SuppressWarnings("unchecked")
		List<Book_Author> list = (List<Book_Author>) ServletActionContext.getRequest().getSession().getAttribute("List");
		int num = Integer.parseInt(index);
		Book_Author ba = list.get(num);
		try {
			_delete(ba);
		} catch (SQLException e) {
			tipQuery="删除失败";
			e.printStackTrace();
		}
		list.remove(num);
		ServletActionContext.getRequest().getSession().setAttribute("List",list);
		ServletActionContext.getRequest().setAttribute("list",list);
		tipQuery="删除成功";
		return SUCCESS;
	}
	public void _delete(Book_Author ba) throws SQLException { 
		Connection conn = new BookDB().getConnection();
		String sql="delete from Book where ISBN=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ba.getISBN());
		pstmt.executeUpdate();
	}

	// 查询
	public String query() {
		tipQuery="";
		if(author=="") {
			tipQuery="您要查找的书目不存在，请重试！";
			return "input";
		}
		BookDB bookdb = new BookDB();
		Connection conn = bookdb.getConnection();
		List _list = new ArrayList();
		List<Book_Author> list = new ArrayList<Book_Author>();
		
		String sql = "select * from  Author where Name = " + "\"" + author
				+ "\"";
		Statement stmt;
		ResultSet rs;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next())
				_list.add(rs.getString("AuthorID"));
			while (!_list.isEmpty()) {
				sql = "select * from  Book,Author where Book.AuthorID =? and Book.AuthorID=Author.AuthorID";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,(String)_list.remove(0));
				rs=pstmt.executeQuery();
				while (rs.next()) {
					Book_Author book = new Book_Author();
					book.setAuthorID(rs.getString("Book.AuthorID"));
					book.setISBN(rs.getString("ISBN"));
					book.setPrice(rs.getDouble("Price"));
					book.setPublishDate(rs.getString("PublishDate"));
					book.setPublisher(rs.getString("Publisher"));
					book.setTitle(rs.getString("Title"));
					book.setName(rs.getString("Name"));
					book.setAge(rs.getInt("Age"));
					book.setCountry(rs.getString("Country"));
					
					list.add(book);
				}

			}
		} catch (SQLException e) {
			tipQuery="查询失败，请重试！";
			//e.printStackTrace();
		}
	
		bookdb.closeDatabase(conn);
		ServletActionContext.getRequest().getSession().setAttribute("List",list);
		ServletActionContext.getRequest().setAttribute("list",list);
		
		if(list.isEmpty()){
			tipQuery="您要查找的书目不存在，请重试！";
			return "input";
		}
		else
			return SUCCESS;
	}
	
	public String update() {
		tip="";
		try {
			if(!hasBook(bookTitle)) {
				tip="您要修改的书目不存在";
				return "input";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		BookDB bookdb = new BookDB();
		Connection conn = bookdb.getConnection();
		try {
			if(hasName(newName)) {
				String sql = "update Book set ISBN=?,Publisher=?,PublishDate=?,Price=?,AuthorID=?  where Title=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newISBN);
				pstmt.setString(2,newPublisher);
				pstmt.setString(3, newPublishDate);
				pstmt.setDouble(4,newPrice);
				pstmt.setString(5,getAuthorID(newName));
				pstmt.setString(6,bookTitle);
				pstmt.executeUpdate();	
				bookdb.closeDatabase(conn);
				return SUCCESS;
			}else {
				String sql2="insert into Author values(?,?,?,?)";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, newAuthorID);
				pstmt2.setString(2, newName);
				pstmt2.setString(3, newAge);
				pstmt2.setString(4,newCountry);
				pstmt2.executeUpdate();
				String sql = "update Book set ISBN=?,Publisher=?,"
					+"PublishDate=?,Price=?,AuthorID=? where Title=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,newISBN);
				pstmt.setString(2,newPublisher);
				pstmt.setString(3,newPublishDate);
				pstmt.setDouble(4,newPrice);
				pstmt.setString(5,newAuthorID);
				pstmt.setString(6,bookTitle);
				pstmt.executeUpdate();
				bookdb.closeDatabase(conn);
				return SUCCESS;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			tip="您输入的信息不完整";
			return "input";
		}	
	}
	private String getAuthorID(String s) {
		BookDB bookdb= new BookDB();
		Connection conn = bookdb.getConnection();
		String sql="select * from Author where Name="+"\""+s+"\"";
		Statement stmt;
		String ss="";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				ss=rs.getString("AuthorID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bookdb.closeDatabase(conn);
		return ss;
	}
	
	private boolean hasBook(String Title) throws SQLException {
		BookDB bookdb=new BookDB();
		Connection conn = bookdb.getConnection();
		String sql = "select * from Book where Title="+"\""+Title+"\"";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			bookdb.closeDatabase(conn);
		
			return true;
		}
		bookdb.closeDatabase(conn);
		return false;
	}
	private boolean hasName(String Title) throws SQLException {
		BookDB bookdb=new BookDB();
		Connection conn = bookdb.getConnection();
		String sql = "select * from Author where Name="+"\""+Title+"\"";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			bookdb.closeDatabase(conn);		
			return true;
		}
		bookdb.closeDatabase(conn);
		return false;
	}

	
	public String add() {
		tip="";
		try {
			if(hasBook(bookTitle)) {
				tip="您要添加的书目已存在";
				return "input";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		BookDB bookdb = new BookDB();
		Connection conn = bookdb.getConnection();
		try {
			if(hasName(newName)) {
				String sql = "insert into Book values(?,?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newISBN);
				pstmt.setString(2,bookTitle);
				pstmt.setString(3, getAuthorID(newName));
				pstmt.setString(4,newPublisher);
				pstmt.setString(5,newPublishDate);
				pstmt.setDouble(6,newPrice);
				pstmt.executeUpdate();	
				bookdb.closeDatabase(conn);
				return SUCCESS;
			}else {
				String sql2="insert into Author values(?,?,?,?)";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, newAuthorID);
				pstmt2.setString(2, newName);
				pstmt2.setString(3, newAge);
				pstmt2.setString(4,newCountry);
				pstmt2.executeUpdate();
				String sql = "insert into Book(ISBN,Publisher,PublishDate," +
						"Price,AuthorID,Title) values(?,?,?,?,?,?)";
				System.out.println("运行到这里");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,newISBN);
				pstmt.setString(2,newPublisher);
				pstmt.setString(3,newPublishDate);
				pstmt.setDouble(4,newPrice);
				pstmt.setString(5,newAuthorID);
				pstmt.setString(6,bookTitle);
				pstmt.executeUpdate();
				bookdb.closeDatabase(conn);
				return SUCCESS;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			tip="您输入的信息不完整";
			return "input";
		}	
	}
}
