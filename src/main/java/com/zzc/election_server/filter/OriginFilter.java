package com.zzc.election_server.filter;



import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author caopengflying
 * @time 2019/4/18 13:27
 */
@Component
public class OriginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rep = (HttpServletResponse) servletResponse;
        rep.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        rep.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, GET");
        rep.setHeader("Access-Control-Max-Age", "3600");
        rep.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        rep.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(req, rep);
    }
}
