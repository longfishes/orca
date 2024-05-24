package com.longfish.orca.interceptor;

import com.longfish.orca.annotation.NoLogin;
import com.longfish.orca.context.BaseContext;
import com.longfish.orca.enums.StatusCodeEnum;
import com.longfish.orca.exception.BizException;
import com.longfish.orca.properties.MobileProperties;
import com.longfish.orca.util.AESEncryptUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@SuppressWarnings("all")
public class MobileCurrentIdInterceptor implements HandlerInterceptor {

    @Autowired
    private MobileProperties mobileProperties;

    @Autowired
    private AESEncryptUtil aesEncryptUtil;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        if (((HandlerMethod) handler).getMethodAnnotation(NoLogin.class) != null) {
            return true;
        }
        if (handler instanceof HandlerMethod handlerMethod) {
            try {
                String data = req.getHeader(mobileProperties.getHeaderIdName());
                String curId = aesEncryptUtil.decrypt(data);
                log.info("current id: {}", curId);
                BaseContext.setCurrentId(Long.valueOf(curId));
            } catch (Exception e) {
                throw new BizException(StatusCodeEnum.NO_LOGIN);
            }
        }
        return true;
    }
}
