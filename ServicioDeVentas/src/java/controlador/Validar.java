/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;

/**
 *
 * @author 57321
 */
public class Validar extends HttpServlet {

    EmpleadoDAO edao = new EmpleadoDAO();
    Empleado em = new Empleado();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             String accion=request.getParameter("accion");
             if(accion.equalsIgnoreCase("Ingresar")){
                 String user=request.getParameter("txtuser");
                  String pass=request.getParameter("txtpass");
                  em=edao.validar(user, pass);
                  if(em.getUser()!=null){
                   request.setAttribute("usuario", em);
                   System.out.println("entra al if");
                   request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                    }else{
                      request.getRequestDispatcher("index.jsp").forward(request, response);
                      System.out.println("esta mal el usuario o contraseña");
                     }
             }else{
                 request.getRequestDispatcher("index.jsp").forward(request, response);
             }
             
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

