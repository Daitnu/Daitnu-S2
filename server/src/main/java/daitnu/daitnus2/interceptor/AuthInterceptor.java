package daitnu.daitnus2.interceptor;

import daitnu.daitnus2.accounts.AccountsSession;
import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {

    HttpSession session = request.getSession();
    AccountsSession sessionUser = (AccountsSession) session.getAttribute("user");

    if (sessionUser == null) {
      throw new BusinessException(ErrorCode.UNAUTHORIZED);
    }

    return true;
  }
}
