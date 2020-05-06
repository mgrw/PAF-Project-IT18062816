package com.hcs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Servlet implementation class LabTestAPI
 */
@WebServlet("/LabTestAPI")
public class LabTestAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	LabTest LabTestObj = new LabTest();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LabTestAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		 String output = LabTestObj.insertLabTest(
				request.getParameter("PatientID"),
				request.getParameter("TestName"),
				request.getParameter("TestType"),
				request.getParameter("TestDescription"),
				request.getParameter("LabDate"));
				response.getWriter().write(output);
			
	}
	
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
		
	{
		Map paras = getParasMap(request);
		
		System.out.println("ID: ");
		System.out.println("ID: "+paras.get("hidLabTestIDSave").toString());
		
		String output = LabTestObj.updateLabTest(
				paras.get("hidLabTestIDSave").toString(),
				paras.get("PatientID").toString(),
				paras.get("TestName").toString(),
				paras.get("TestType").toString(),
				paras.get("TestDescription").toString(),
				paras.get("LabDate").toString());
				response.getWriter().write(output);
		}

	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	// TODO Auto-generated method stub
	{
		Map paras = getParasMap(request);
		String output = LabTestObj.deleteLabTest(paras.get("LabTestID").toString());
		response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
