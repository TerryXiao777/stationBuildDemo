package com.demo.webtier;

import com.demo.bean.UserInfo;
import com.demo.dao.UserInfoDao;
import com.demo.tools.FileOperation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserInfoServlet extends HttpServlet {
    private UserInfoDao dao = null;
    private String method = "";
    private UserInfo userInfo = null;
    private FileOperation fileOperation = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        fileOperation = new FileOperation();
        method = request.getParameter("method");
        if (method.equals("userAdd")) {// 用户注册操作
            this.userAdd(request, response);
        }
        if (method.equals("userCheck")) {// 用户登录操作
            this.userCheck(request, response);
        }
        if (method.equals("landOut")) {// 用户安全退出
            this.landOut(request, response);
        }

    }

    // 用户安全退出
    private void landOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // 用户登录操作
    private void userCheck(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dao = new UserInfoDao();
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String result = "";
        userInfo = dao.user_queryObject(account);
        String code = request.getParameter("code");
        String codeSession = (String) request.getSession().getAttribute("rand");
        if (code.equals(codeSession)) {

            if (userInfo == null) {
                result = "您输入的用户名不存在<br>用户登录失败！";
            } else {
                if (!password.equals(userInfo.getPassword())) {
                    result = "您输入的密码有误<br>用户登录失败！";
                }
            }
        }
        else {
            result = "您输入的验证码有误<br>用户登录失败！";
        }

        if (result.equals("")) {
            request.getSession().setAttribute("userInfo", userInfo);
        }
        else {
            request.setAttribute("result", result);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    // 用户注册操作
    private void userAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dao = new UserInfoDao();
        String code = request.getParameter("code");
        String codeSession = (String) request.getSession().getAttribute("rand");
        String result = "您输入的校验码有误<br>注册失败！";
        String account = request.getParameter("account");
        if (code.equals(codeSession)) {
            userInfo = new UserInfo();
            userInfo.setAccount(request.getParameter("account"));
            userInfo.setPassword(request.getParameter("password"));
            userInfo.setRealname(request.getParameter("realname"));
            userInfo.setAge(Integer.valueOf(request.getParameter("age")));
            userInfo.setSex(request.getParameter("sex"));
            userInfo.setProfession(request.getParameter("profession"));

            if (dao.user_save(userInfo)) {
                result = "";
            }
            else {
                result = "您输入用户名重复<br>注册失败!";
            }

        }

        if (result.equals("")) {
            String dummyPath = "netAccount/" + account + "/";
            String realPath = request.getRealPath(dummyPath);
            fileOperation.createFile(realPath);
            dao.user_updateAddress(account, dummyPath);
            userInfo = dao.user_queryObject(account);
            request.getSession().setAttribute("userInfo", userInfo);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else {
            request.setAttribute("result", result);
            request.getRequestDispatcher("user_add.jsp").forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
