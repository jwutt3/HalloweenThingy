/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elon.events;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 *
 * @author jamieutt
 */
@WebServlet(name = "Halloween_Servlet", urlPatterns = {"/goelon"})
public class Halloween_Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/email.html";
        
        
        // get current action
        String action = request.getParameter("action");
        
        //print debugging statement
        System.out.println("EmailListServlet action: " + action);
        log("action=" + action);
        
        if (action == null) {
            action = "join";  // default action
        }

        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/email.html";    // the "join" page
        } 
        else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("FirstName");
            System.out.println("First Name: " + firstName);
            String lastName = request.getParameter("LastName");
            System.out.println("Last Name: " + lastName);
            String email = request.getParameter("Email");
            System.out.println("email: " + email);
            String zipcode = request.getParameter("ZipCode");
            System.out.println("zipcode: " + zipcode);

            // store data in User object
            //User user = new User(firstName, lastName, email);

            // validate the parameters
            String message;
            if (firstName == null || lastName == null || email == null ||
                firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                message = "Please fill out all four text boxes.";
                url = "/email.html";
            } 
            else {
                message = "";
                url = "/subscribe.html";
                //UserDB.insert(user);
            }
            //request.setAttribute("user", user);
            request.setAttribute("message", message);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
