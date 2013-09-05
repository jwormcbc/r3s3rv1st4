/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets.usuarios;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DellXps15
 */
public class TMServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean proceder=false;
        int opc=0;
        String errExceptions="";
        try {
            /* TODO output your page here. You may use following sample code. */
        
            String opcion=request.getParameter("opc");
            if( opcion != null && opcion.length()>0){
                    try{
                        opc=Integer.parseInt(opcion);
                        proceder=true;
                    }catch(Exception e){
                        System.out.println(" " + e.toString());
                        errExceptions=e.toString();
                    }
            }else
            proceder=false;
            
            
            
            String nom=request.getParameter("nom");
            if( nom != null && nom.length()>0)
            proceder=true;
            else
            proceder=false;
            
          
             
            switch(opc){
                case 2:
                    if(proceder)
                    out.print(new com.reservaciones.controladores.ControladorReservaciones().consultaHistoricoAJsonTimeline(nom));
                    else
                    out.println("Err. proceder:" + proceder);
                    break;
                default:
                     out.println("[{\"content\":\"Err, opcion invalida en TMServlet. checar opc var \",\"start\":\"new Date(2013,1,1)\",\"end\":\"new Date(2013,5,5)\"}]");
                    break;
            }
            
        
        
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
