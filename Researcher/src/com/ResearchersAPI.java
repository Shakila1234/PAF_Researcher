package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResearchersAPI
 */
@WebServlet("/ResearchersAPI")
public class ResearchersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ResearchersAPI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Researcher researcherObj = new Researcher();
		String output = researcherObj.insertResearcher(request.getParameter("rName"), 
				 request.getParameter("pName"), 
				request.getParameter("rDate"), 
				request.getParameter("rDes")); 
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Researcher researcherObj = new Researcher();
		
		Map paras = getParasMap(request);
		String output = researcherObj.updateResearcher(paras.get("hidResearcherIDSave").toString(),
		paras.get("rName").toString(),
		paras.get("pName").toString(),
		paras.get("rDate").toString(),
		paras.get("rDes").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Researcher researcherObj = new Researcher();
		
		Map paras = getParasMap(request);
		String output = researcherObj.deleteResearcher(paras.get("rId").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
									scanner.useDelimiter("\\A").next() : "";
						scanner.close();
						String[] params = queryString.split("&");
						for (String param : params)
						{
							String[] p = param.split("=");
							map.put(p[0], p[1]);
						}
			}
			catch (Exception e)
			{
			}
			return map;
		}

}
