/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.praktikum1.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
*
* @author asus
*/
@WebServlet(name="HomeServlet", urlPatterns = {"/HomeServlet"})
public class andiyanidika extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
// retrieve the value supplied by the user
String userName = request.getParameter("username");
// retrieve the PrintWriter object and use it to output the greeting
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
try{
out.println("<html>");
out.println("<head><title>GetParameter Servlet</title></head>");
out.println("<body>");
out.println("<h1>");
out.println("HELLO, " + userName + "!");
out.println("</h1>");
out.println("</body>");
out.println("</html>");
}
finally{
out.close();
}
}
}