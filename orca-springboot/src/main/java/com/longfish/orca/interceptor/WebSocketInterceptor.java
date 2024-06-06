package com.longfish.orca.interceptor;

import com.longfish.orca.context.BaseContext;
import com.longfish.orca.enums.StatusCodeEnum;
import com.longfish.orca.exception.BizException;
import com.longfish.orca.properties.JwtProperties;
import com.longfish.orca.properties.MobileProperties;
import com.longfish.orca.util.AESEncryptUtil;
import com.longfish.orca.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.longfish.orca.constant.CommonConstant.USER_ID;

@Component
@Slf4j
public class WebSocketInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private MobileProperties mobileProperties;

    @Autowired
    private AESEncryptUtil aesEncryptUtil;

    @Override
    @SuppressWarnings("all")
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (((HandlerMethod) handler).getBean().getClass().getName().contains("org.springdoc")) {
            return true;
        }
        String token = req.getHeader(jwtProperties.getTokenName());
        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            Long userId = Long.valueOf(claims.get(USER_ID).toString());
            log.info("current id: {}", userId);
            BaseContext.setCurrentId(userId);
            return true;
        } catch (Exception ignored) {}
        try {
            String data = req.getHeader(mobileProperties.getHeaderIdName());
            String curId = aesEncryptUtil.decrypt(data);
            log.info("current id: {}", curId);
            BaseContext.setCurrentId(Long.valueOf(curId));
        } catch (Exception ignored) {}
        throw new BizException(StatusCodeEnum.NO_LOGIN);
    }
}
