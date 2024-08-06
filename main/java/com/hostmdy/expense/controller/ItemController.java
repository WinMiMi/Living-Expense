package com.hostmdy.expense.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.sql.DataSource;

import com.hostmdy.expense.model.Item;
import com.hostmdy.expense.model.ItemDAO;
import com.hostmdy.expense.model.Mode;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/item")
public class ItemController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/living_app")
	private DataSource dataSource;
	
	private ItemDAO itemDAO;
	@Override
	public void init() throws ServletException {
		itemDAO = new ItemDAO(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("mode");
		Mode mode = null;
		if(param == null) {
			mode = Mode.LIST;
		}else {
			mode = Mode.valueOf(param);
		}
		
		switch (mode) {
		case LIST:
			showAllList(req, resp);
			break;
		case SINGLE:
			showItem(req, resp);
			break;
		case ITEM_FORM:
			showNewItemForm(req, resp);
			break;
		case CREATE:
			createItem(req, resp);
			break;
		default:
			showAllList(req, resp);
			break;
		}
	}
	
	private void createItem(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String category = req.getParameter("category");
		Double price = Double.parseDouble(req.getParameter("price"));
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));
		Boolean essential = Boolean.parseBoolean(req.getParameter("essential"));
		String image = req.getParameter("image");
		String description = req.getParameter("description");
		
		Item item = new Item(title, category, price, quantity, essential, description, image, 1L);
		req.setAttribute("insertOk",itemDAO.createItem(item));
		showNewItemForm(req, resp);
	}
	
	private void showNewItemForm(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/add-item.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void showAllList(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		List<Item> itemList = itemDAO.getAllItemsByUser(1L);
		req.setAttribute("itemList", itemList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/home.jsp");
		dispatcher.forward(req, resp);
	}
	
	private void showItem(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		Long itemId = Long.parseLong(req.getParameter("itemId"));
		Item item = itemDAO.getItemById(itemId);
		req.setAttribute("item", item);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/item/item-details.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
