package user.action;

import user.dao.CustomerDAO;
import user.vo.CustomerVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PwConfirmAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        String viewPage = null;
        if (action != null) {
            switch (action) {
                case "select":
                    HttpSession session = request.getSession();
                    CustomerVO cvo = (CustomerVO) session.getAttribute("customer_info");

                    if (cvo == null) {
                        return "/user/jsp/error/error.jsp";
                    }

                    String cus_pw = request.getParameter("cus_pw");

                    CustomerVO vo = new CustomerVO();
                    vo.setCus_id(cvo.getCus_id());
                    vo.setCus_pw(cus_pw);

                    CustomerVO res = CustomerDAO.login(vo);
                    if (res != null) {
                        request.setAttribute("valid", "true");
                    } else {
                        request.setAttribute("valid", "false");
                    }

                    viewPage = "/user/jsp/mypage/pwConfirm.jsp";
                    break;
                case "update":
                    viewPage = "/user/jsp/mypage/profileEdit.jsp";
                    break;
            }
        } else {
            viewPage = "/user/jsp/mypage/pwConfirm.jsp";
        }

        return viewPage;
    }
}
