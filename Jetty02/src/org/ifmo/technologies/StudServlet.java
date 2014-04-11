//====================================================================
// Задача: HttpServlet должен сохранять список студентов. Студенты
// идентифицируются уникальным "именем" и, в качестве атрибута, имеют 
// номер группы. HttpServlet должен обеспечивать обработку следующих
// запросов:
// - GET /students
//   выдает сохраненный список студентов в виде таблицы; 
// - POST /students?name=<имя студента>&group=<номер группы>
//   заносит нового (ранее не существовавшего) студента в таблицу
//   с указанным номером группы;
// - PUT /students/<имя студента>?group=<номер группы>
//   изменяет номер группы у существующего студента с заданным именем;
// - DELETE /students/<имя студента>
//   удаляет из списка студента с заданным именем.
// Таблица существует только на время жизни сервера (хранится в его
// оперативной памяти), при рестарте сервера таблица обнуляется. Если
// список студентов пуст, то запрос GET выдает сообщение об этом.
//====================================================================

package org.ifmo.technologies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Реализация описанного сервлета. Адрес "/students" считается базовым для него. 
 */
public class StudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, String> students = new TreeMap<>();

    @Override
    /**
     * Обработка запроса GET - выдача списка студентов
     * Формат запроса:
     * GET /students
     */
    protected void doGet(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	String path = request.getPathInfo();
    	try {
    		if (path != null && !"/".equals(path)) {
    			// Должен быть указан пустой ресурс
    			response.sendError(HttpServletResponse.SC_NOT_FOUND);
    		} else {
    			// Формируем список
    			response.setContentType("text/html; charset=utf-8");
    			PrintWriter writer = response.getWriter();
    			writer.println("<title>Список студентов</title>");
    			if (students.isEmpty()) {
    				// Пустой список студентов
    				writer.println("<h2>Список студентов пуст</h2>");
    			} else {
    				writer.println("<h2>Список студентов</h2>");
    				// Формируем таблицу
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
    /**
     * Обработка запроса POST - занесение в таблицу нового студента
     * Формат запроса:
     * POST /students?name=<имя студента>&group=<номер группы>
     */
    protected void doPost(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	String path = request.getPathInfo();
    	String name = request.getParameter("name");		// Имя студента
    	String group = request.getParameter("group");	// Номер группы
    	try {
    		if (path != null && !"/".equals(path)) {
    			// Должен быть указан пустой ресурс
    			response.sendError(HttpServletResponse.SC_NOT_FOUND);
    		} else if (name == null || group == null) {
    			// По крайней мере один из обязательных параметров не найден
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
    					"\'name\' or \'group\' parameter(s) missed");
    		} else if (students.get(name) != null) {
    			// Такой студент уже существует - не добавляем
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
    					"student \'" + name + "\' already exists");
    		} else {
    			// Ошибок в запросе нет, добавляем студента
    			students.put(name, group);
    		}
    	} catch (IOException e) {
    	}
    }

    @Override
    /**
     * Обработка запроса PUT - изменение группы у существующего студента
     * Формат запроса:
     * PUT /students/<имя студента>?group=<номер группы>
     */
    protected void doPut(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	String path = request.getPathInfo();
    	String group = request.getParameter("group");	// Номер группы
    	try {
    		if (path == null) {
    			// Имя студента (ресурс) не задано
    			response.sendError(HttpServletResponse.SC_NOT_FOUND,
    					"Empty resource");
    		} else if (group == null) {
    			// Номер группы не задан
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
    					"\'group\' parameter missed");
    		} else {
    			String name = path.substring(1);	// Имя студента
    			if (students.get(name) == null) {
    				// Студент с заданным именем не найден
    				response.sendError(HttpServletResponse.SC_NOT_FOUND,
    						"Student " + name + " not found");
    			} else {
    				// Все нормально, записываем информацию о группе
    				students.put(name, group);
    			}
    		}
    	} catch (IOException e) {
    	}
    }

    @Override
    /**
     * Обработка запроса DELETE - удаление студента с заданным именем.
     * Формат запроса:
     * DELETE /students/<имя студента>
     */
    protected void doDelete(
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	String path = request.getPathInfo();
    	try {
    		if (path == null) {
    			// Имя студента (ресурс) не задано
    			response.sendError(HttpServletResponse.SC_NOT_FOUND,
    					"Empty resource");
    		} else {
    			String name = path.substring(1);
    			if (students.get(name) == null) {
    				// Студент с заданным именем не найден
    				response.sendError(HttpServletResponse.SC_NOT_FOUND,
    						"Student " + name + " not found");
    			} else {
    				// Все нормально, удаляем студента
    				students.remove(name);
    			}
    		}
    	} catch (IOException e) {
    	}
    }
}
