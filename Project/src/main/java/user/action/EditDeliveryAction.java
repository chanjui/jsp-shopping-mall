package user.action;

import user.dao.DeliveryDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditDeliveryAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        //파라미터 받기
        String id = request.getParameter("id");
        String cus_no = (String) session.getAttribute("customer_info.id");
        System.out.println("cus_no" + cus_no);
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String pos_code = request.getParameter("pos_code");
        String addr1 = request.getParameter("addr1");
        String addr2 = request.getParameter("addr2");
        String chkDefault = request.getParameter("chkDefault") != null ? "1" : "0";
        String deli_request = request.getParameter("deli_request");

        int cnt = DeliveryDAO.updateDeliInfo(id, cus_no, name, phone, pos_code, addr1, addr2, chkDefault, deli_request);

        // 결과 처리
        if (cnt > 0) {
            request.setAttribute("success", true);

            // log -> target
            // 배송지 정보가 성공적으로 추가되었습니다.

        } else {
            request.setAttribute("success", false);

            // log -> target
            // 배송지 추가에 실패했습니다. 다시 시도해주세요.
        }

        return "/user/jsp/mypage/mypage.jsp";
    }
}
