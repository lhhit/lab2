package com.library.action;
//insert
import java.util.List;
import com.library.DataBean.Book_Author;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

public class BA_DataAction  extends ActionSupport{
	
	private String ISBN;
	private String Title;
	private String AuthorID;
	private String Publisher;
	private String PublishDate;
	private double Price;
	private String Name;
	private int Age;
	private String Country;
	private String index;


	public String getdata() {
		if(index==null)
			index=ServletActionContext.getRequest().getSession().getAttribute("index").toString();
		int num=Integer.parseInt(index);
		ServletActionContext.getRequest().getSession().setAttribute("index",index);
		
		List<Book_Author> list = (List<Book_Author>) ServletActionContext.getRequest().getSession().getAttribute("List");
		Book_Author baData = list.get(num);
		this.setISBN(baData.getISBN());
		this.setAge(baData.getAge());
		this.setAuthorID(baData.getAuthorID());
		this.setCountry(baData.getCountry());
		this.setName(baData.getName());
		this.setPrice(baData.getPrice());
		this.setPublishDate(baData.getPublishDate());
		this.setPublisher(baData.getPublisher());
		this.setTitle(baData.getTitle());
		return SUCCESS;
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getAuthorID() {
		return AuthorID;
	}
	public void setAuthorID(String authorID) {
		AuthorID = authorID;
	}
	public String getPublisher() {
		return Publisher;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public String getPublishDate() {
		return PublishDate;
	}
	public void setPublishDate(String publishDate) {
		PublishDate = publishDate;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}	
}
