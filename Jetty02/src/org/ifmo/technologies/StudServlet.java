package org.ifmo.technologies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, String> students = new TreeMap<>();

    @Override
    protected void doGet(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	String path = request.getPathInfo();
    	try {
    		if (path != null && !"/".equals(path)) {
    			response.sendError(HttpServletResponse.SC_NOT_FOUND);
    		} else {
    			// Ignore parameters
    			response.setContentType("text/html; charset=utf-8");
    			PrintWriter writer = response.getWriter();
    			writer.println("<title>Список студентов</title>");
    			if (students.isEmpty()) {
    				writer.println("<h2>Список студентов пуст</h2>");
    			} else {
    				writer.println("<h2>Список студентов</h2>");
    				writer.println("<table style=\'width: 50%; border: 1px solid black; border-collapse: collapse\'>");
    				writer.println("<col style=\'width: 60%; border: 1px solid black\'/><col/>");
    				for (Map.Entry<String, String> entry : students.entrySet()) {
    					writer.format("<tr><td>%s</td><td>%s</td></tr>\n",
    							entry.getKey(), entry.getValue());
    				}
    				writer.println("</table>");
    			}
    		}
    	} catch (IOException e) {
    	}
    }

    @Override
    protected void doPost(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	String path = request.getPathInfo();
    	String name = request.getParameter("name");
    	String group = request.getParameter("group");
    	try {
    		if (path != null && !"/".equals(path)) {
    			response.sendError(HttpServletResponse.SC_NOT_FOUND);
    		} else if (name == null || group == null) {
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
    					"\'name\' or \'group\' parameter(s) missed");
    		} else if (students.get(name) != null) {
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
    					"student \'" + name + "\' already exists");
    		} else {
    			students.put(name, group);
    		}
    	} catch (IOException e) {
    	}
    }

    @Override
    protected void doPut(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	String path = request.getPathInfo();
    	String group = request.getParameter("group");
    	try {
    		if (path == null) {
    			response.sendError(HttpServletResponse.SC_NOT_FOUND,
    					"Empty resource");
    		} else if (group == null) {
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
    					"\'group\' parameter missed");
    		} else {
    			String name = path.substring(1);
    			if (students.get(name) == null) {
    				response.sendError(HttpServletResponse.SC_NOT_FOUND,
    						"Student " + name + " not found");
    			} else {
    				students.put(name, group);
    			}
    		}
    	} catch (IOException e) {
    	}
    }

    @Override
    protected void doDelete(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	String path = request.getPathInfo();
    	try {
    		if (path == null) {
    			response.sendError(HttpServletResponse.SC_NOT_FOUND,
    					"Empty resource");
    		} else {
    			String name = path.substring(1);
    			if (students.get(name) == null) {
    				response.sendError(HttpServletResponse.SC_NOT_FOUND,
    						"Student " + name + " not found");
    			} else {
    				students.remove(name);
    			}
    		}
    	} catch (IOException e) {
    	}
    }
}
