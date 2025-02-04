package user.action.customer;

import user.action.Action;
import user.dao.customer.OrderDAO;
import user.vo.customer.CustomerVO;
import user.vo.customer.OrderVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WriteReviewAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        String prod_no = request.getParameter("prod_no");
        String order_code = request.getParameter("order_code");
        HttpSession session = request.getSession();
        CustomerVO cvo = (CustomerVO) session.getAttribute("customer_info");

        if (cvo == null) {
            request.setAttribute("session_expired", true);
            return "/user/customer/jsp/error/error.jsp";
        }

        String viewPath = null;
        if(action != null){
            switch(action){
                case "select":
                    OrderVO o_vo = OrderDAO.selectReviewProduct(cvo.getId(), prod_no, order_code);
                    request.setAttribute("o_vo", o_vo);
                    viewPath = "/user/customer/jsp/mypage/writeReview.jsp";
                    break;
            }
        }

        return viewPath;
    }
}
