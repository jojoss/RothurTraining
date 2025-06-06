package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dao.pg.jdbc.UserPgJdbcDao;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {

    private final UserPgJdbcDao userDao = new UserPgJdbcDao();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo(); // e.g. /3, /stats, /count, /with-orders
        resp.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            // GET /users - return all users
            List<User> users = userDao.getAllUsers();
            objectMapper.writeValue(resp.getWriter(), users);
            return;
        }

        String[] parts = pathInfo.substring(1).split("/");

        try {
            if (parts.length == 1) {
                if (parts[0].equals("count")) {
                    // GET /users/count
                    int count = userDao.getUserCount();
                    objectMapper.writeValue(resp.getWriter(), Map.of("totalUsers", count));
                } else if (parts[0].equals("stats")) {
                    // GET /users/stats
                    Map<String, Integer> stats = userDao.countUsersByDomain();
                    objectMapper.writeValue(resp.getWriter(), stats);
                } else if (parts[0].equals("with-orders")) {
                    // GET /users/with-orders
                    List<User> users = userDao.findUsersWithOrders();
                    objectMapper.writeValue(resp.getWriter(), users);
                } else {
                    // GET /users/{id}
                    int id = Integer.parseInt(parts[0]);
                    User user = userDao.getUserById(id);
                    if (user != null) {
                        objectMapper.writeValue(resp.getWriter(), user);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        resp.getWriter().write("{\"error\":\"User not found\"}");
                    }
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Invalid user request path\"}");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Invalid user ID\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User newUser = objectMapper.readValue(req.getReader(), User.class);
        userDao.insertUser(newUser);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("{\"message\":\"User created successfully\"}");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User updatedUser = objectMapper.readValue(req.getReader(), User.class);
        userDao.updateUser(updatedUser);
        resp.getWriter().write("{\"message\":\"User updated successfully\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo(); // e.g. /3
        try {
            int id = Integer.parseInt(pathInfo.substring(1));
            userDao.deleteUser(id);
            resp.getWriter().write("{\"message\":\"User deleted successfully\"}");
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Invalid user ID\"}");
        }
    }
}