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
	
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private static final String EMPTY_LIST = 
			"<title>Список студентов</title>\n" +
			"<h2>Список студентов пуст</h2>\n";
	private static final String STUD_TABLE = 
			"<title>Список студентов</title>\n" +
			"<h2>Список студентов</h2>\n" +
			"<table style=\'width: 50%%; border: 1px solid black; border-collapse: collapse\'>\n" +
			"  <col style=\'width: 60%%; border: 1px solid black\'/><col/>\n" +
			"  %s" +
			"</table>\n";
	private static final String STUD_ROW =
			"<tr><td>%s</td><td>%s</td></tr>\n";
	
	//============ Errors ===============
	
	private static final String ERR_EMPTY_RESOURCE = "Не задано имя студента";
	private static final String ERR_NAME_PAR_MISSED = "\'name\' параметр не задан";
	private static final String ERR_GROUP_PAR_MISSED = "\'group\' параметр не задан";
	private static final String ERR_STUDENT_EXISTS = "Студент %s уже существует; задайте уникальное имя";
	private static final String ERR_STUDENT_NOT_FOUND = "Студент %s не существует";
	
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
		response.setContentType(CONTENT_TYPE);
    	String path = request.getPathInfo();
    	try {
    		if (path != null && !"/".equals(path)) {
    			// Должен быть указан пустой ресурс
    			response.sendError(HttpServletResponse.SC_NOT_FOUND);
    		} else {
    			// Формируем список
    			response.getWriter().print(students.isEmpty() ? EMPTY_LIST : studList());
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
		response.setContentType(CONTENT_TYPE);
    	try {
    		if (path != null && !"/".equals(path)) {
    			// Должен быть указан пустой ресурс
    			response.sendError(HttpServletResponse.SC_NOT_FOUND);
    		} else if (name == null) {
    			// Параметр 'name' не найден
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERR_NAME_PAR_MISSED);
    		} else if (group == null) {
    			// Параметр 'group' не найден
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERR_GROUP_PAR_MISSED);
    		} else if (students.get(name) != null) {
    			// Такой студент уже существует - не добавляем
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST, String.format(ERR_STUDENT_EXISTS, name));
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
		response.setContentType(CONTENT_TYPE);
    	try {
    		if (path == null) {
    			// Имя студента (ресурс) не задано
    			response.sendError(HttpServletResponse.SC_NOT_FOUND,
    					ERR_EMPTY_RESOURCE);
    		} else if (group == null) {
    			// Номер группы не задан
    			response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERR_GROUP_PAR_MISSED);
    		} else {
    			String name = path.substring(1);	// Имя студента
    			if (students.get(name) == null) {
    				// Студент с заданным именем не найден
    				response.sendError(HttpServletResponse.SC_NOT_FOUND,
    						String.format(ERR_STUDENT_NOT_FOUND, name));
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
		response.setContentType(CONTENT_TYPE);
    	try {
    		if (path == null) {
    			// Имя студента (ресурс) не задано
    			response.sendError(HttpServletResponse.SC_NOT_FOUND,
    					ERR_EMPTY_RESOURCE);
    		} else {
    			String name = path.substring(1);
    			if (students.get(name) == null) {
    				// Студент с заданным именем не найден
    				response.sendError(HttpServletResponse.SC_NOT_FOUND,
    						String.format(ERR_STUDENT_NOT_FOUND, name));
    			} else {
    				// Все нормально, удаляем студента
    				students.remove(name);
    			}
    		}
    	} catch (IOException e) {
    	}
    }
    
    private String studList() {
    	StringBuilder rows = new StringBuilder();
    	for (Map.Entry<String, String> entry : students.entrySet()) {
    		rows.append(String.format(STUD_ROW, entry.getKey(), entry.getValue()));
    	}
    	return String.format(STUD_TABLE, rows.toString());
    }
}
