package user.action.customer;

import user.action.Action;
import user.dao.customer.OrderDAO;
import user.vo.customer.CustomerVO;
import user.vo.customer.OrderVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeliveryStatusAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        CustomerVO cvo = (CustomerVO) session.getAttribute("customer_info");

        if (cvo == null) {
            request.setAttribute("session_expired", true);
            return "/user/customer/jsp/error/error.jsp";
        }

        String order_id = request.getParameter("order_id");
        String order_code = request.getParameter("order_code");

        List<OrderVO> list = OrderDAO.selectDeliveryStatus(cvo.getId(), order_code);
        request.setAttribute("list", list);

        return "/user/customer/jsp/mypage/deliveryStatus.jsp";
    }
}
