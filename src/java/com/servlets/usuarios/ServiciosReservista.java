/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets.usuarios;

import com.reservaciones.controladores.ControladorUsuarios;
import com.reservaciones.mapeos.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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
public class ServiciosReservista extends HttpServlet {
private ControladorUsuarios cu=new ControladorUsuarios();
private static final int campos=11;
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
        
        String matricula="";
        if(request.getParameter("matricula")!=null)
            matricula=request.getParameter("matricula");
        
        
        
        try {
            /* TODO output your page here. You may use following sample code. */

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String mensajeFinal="";
            
    if(matricula.length()>5 && matricula!=null && !matricula.contains("_")){
        Usuarios u=cu.buscarUsuario(matricula);
        mensajeFinal+=u.getNombre()+",";
        mensajeFinal+=u.getApellido1()+",";;
        mensajeFinal+=u.getApellido2()+",";;
        mensajeFinal+=u.getSexo()+",";;
        mensajeFinal+=dateFormat.format(u.getFechanacimiento())+",";;
        mensajeFinal+=u.getPais()+",";;
        mensajeFinal+=u.getEstado()+",";;
        mensajeFinal+=u.getPuesto()+",";;
        mensajeFinal+=u.getDescripcion()+",";;
        mensajeFinal+=cu.buscarRolDeUsuario(matricula)+",";;
        mensajeFinal+=""+dateFormat.format(u.getFechacreacion());
        out.println(mensajeFinal);
    }else{
    
        for(int i=0;i<campos;i++)
            if(i<campos-2)
            mensajeFinal+="-,";
            else
            mensajeFinal+="-"; 
        
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
