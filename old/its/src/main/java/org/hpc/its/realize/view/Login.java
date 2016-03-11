package org.hpc.its.realize.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hpc.its.utils.ClientUtil;

/**
 * login system
 * 
 * @timestamp Nov 12, 2015 11:36:29 PM
 * @author smallbug
 */
@SuppressWarnings("serial")
@WebServlet("/login")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		String mapId = req.getParameter("mapId");
		req.getSession().setAttribute("remoteIp", ClientUtil.getRemoteIp(req));
		req.getSession().setAttribute("mapId", mapId);
		req.getRequestDispatcher("/index.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
