package com.yuanhao.seckill;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @author Yuanhao
 */
public class SecKillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SecKillServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userid = new Random().nextInt(50000) + "";
        String productId = request.getParameter("productId");
//        System.out.println(userid + " " + productId);

        boolean isSuccess = SecKillRedisByScript.doSecKill(userid, productId);
        response.getWriter().print(isSuccess);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(123);
        resp.getWriter().println(123);
    }
}
