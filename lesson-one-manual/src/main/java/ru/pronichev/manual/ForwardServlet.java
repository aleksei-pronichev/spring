package ru.pronichev.manual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ForwardServlet", urlPatterns = "/forward")
public class ForwardServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(ForwardServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New Get request with forward");

        getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
    }
}
