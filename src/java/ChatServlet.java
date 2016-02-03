/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Al-Jazayeerly
 */
public class ChatServlet extends HttpServlet {

    static int id = 0;
    Vector<Message> ms_vector = new Vector();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<title>Servlet ChatServlet</title>");
            out.print("</head>");
            out.print("<body>");
            out.print("<h1>Servlet ChatServlet at " + request.getContextPath() + "</h1>");
            out.print("</body>");
            out.print("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet reques
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        System.out.println("inside do get");
        String name = request.getParameter("username");
        String message = request.getParameter("message");
        Date date = new Date();
        Message msg = new Message(name, message, date, id++);
        ms_vector.add(msg);
        System.out.println("name:" + msg.getUserName() + " message: " + msg.getMessage());

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            System.out.println("doPost");
            response.setContentType("text/xml");
            PrintWriter out = response.getWriter();
            String reply="<msgs>";
            for(int i=0;i<ms_vector.size();i++){
                reply+="<msg>";
                reply+="<name>"+ms_vector.get(i).getUserName()+"</name>";
                reply+="<text>"+ms_vector.get(i).getMessage()+"</text>";
                reply+="<date>"+ms_vector.get(i).getDate()+"</date>";
                reply+="<id>"+ms_vector.get(i).getDate()+"</id>";
                reply+="</msg>";
            }
            reply=reply+"</msgs>";
            
            
//            out.print("<msgs>");
//            
//            for(int i=0;i<ms_vector.size();i++){
//                out.print("<msg>");
//                out.print("<name>"+ms_vector.get(i).getUserName()+"</name>");
//                System.out.println("name user in vector:" + ms_vector.get(i).getUserName());
//                out.print("<text>"+ms_vector.get(i).getMessage()+"</text>");
//                out.print("<date>"+ms_vector.get(i).getDate()+"</date>");
//                out.print("<id>"+ms_vector.get(i).getId()+"</id>");
//                out.print("</msg>");
//                
//            
//            }
           out.println(reply);
           System.out.println(reply);
//      

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
