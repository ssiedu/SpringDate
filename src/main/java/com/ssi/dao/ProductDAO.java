package com.ssi.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssi.entities.Product;

@Component
public class ProductDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Product> getExpiryAfter(String expdate){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		Date edate=null;
		try{
			edate=df.parse(expdate);
		}catch(Exception e) {}
		
		Session session=sessionFactory.openSession();
		/*
		Using HQL
		Query query=session.createQuery("from Product where expiry>:expdate");
		query.setParameter("expdate",edate);
		List<Product> productList=query.list();
		*/
		Criteria cr=session.createCriteria(Product.class);
		Criterion crt=Restrictions.ge("expiry", edate);
		cr.add(crt);
		List<Product> productList=cr.list();
		session.close();
		return productList;
	}
	public Product addProduct(Product product) {
		Session session=sessionFactory.openSession();
		session.save(product);
		session.beginTransaction().commit();
		session.close();
		return product;
	}
	public Product removeProduct(int pcode) {
		Session session=sessionFactory.openSession();
		Product product=searchProduct(pcode);
		session.delete(product);
		session.beginTransaction().commit();
		session.close();
		return product;
	}
	public Product updateProduct(Product product) {
		Session session=sessionFactory.openSession();
		session.update(product);
		session.beginTransaction().commit();
		session.close();
		return product;
	}
	public Product searchProduct(int id) {
		Session session=sessionFactory.openSession();
		Product product= session.get(Product.class,id);
		session.close();
		return product;
	}
	public List<Product> getAllProducts(){
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Product");
		List<Product> productList=query.list();
		session.close();
		return productList;
	}
	
}
