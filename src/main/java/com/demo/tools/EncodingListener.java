package com.demo.tools;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class EncodingListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        try {
            ((HttpServletRequest) servletRequestEvent.getServletRequest())
                    .setCharacterEncoding("utf-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
