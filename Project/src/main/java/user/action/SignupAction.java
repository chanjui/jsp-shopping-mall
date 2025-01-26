package user.action;

import user.dao.CustomerDAO;
import user.dao.LogDAO;
import user.vo.CustomerVO;
import user.vo.LogVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        String viewPath = null;
        if (action != null) {
            switch (action) {
                case "insert":
                    try {
                        // 한글 처리
                        request.setCharacterEncoding("UTF-8");

                        // 모든 입력 데이터를 가져오기
                        String cusId = request.getParameter("cus_id");
                        String cusPw = request.getParameter("cus_pw");
                        String cusName = request.getParameter("cus_name");
                        String cusNickname = request.getParameter("cus_nickname");
                        String cusGender = request.getParameter("cus_gender");
                        String cusBirth = request.getParameter("cus_birth");
                        String cusPhone = request.getParameter("cus_phone");
                        String cusEmail = request.getParameter("cus_email");

                        CustomerVO vo = new CustomerVO();
                        vo.setCus_id(cusId);
                        vo.setCus_pw(cusPw);
                        vo.setName(cusName);
                        vo.setNickname(cusNickname);
                        vo.setGender(cusGender);
                        vo.setBirth_date(cusBirth);
                        vo.setPhone(cusPhone);
                        vo.setEmail(cusEmail);

                        int cnt = CustomerDAO.insertCustomer(vo);

                        if (cnt > 0) {
                            // 추가 로그
                            CustomerVO cvo = CustomerDAO.selectCustomerByCusId(vo.getCus_id());

                            LogVO lvo = new LogVO();
                            StringBuffer sb = new StringBuffer();
                            lvo.setCus_no(cvo.getId());
                            lvo.setTarget("customer 추가");
                            sb.append("cus_id : " + vo.getCus_id() + ", ");
                            sb.append("name : " + cusName + ", ");
                            sb.append("nickname : " + cusNickname + ", ");
                            sb.append("gender : " + cusGender + ", ");
                            sb.append("birth_date : " + cusBirth + ", ");
                            sb.append("phone : " + cusPhone + ", ");
                            sb.append("email : " + cusEmail);
                            lvo.setCurrent(sb.toString());
                            LogDAO.insertLog(lvo);
                        }

                        request.setAttribute("response", "true");

                        viewPath = "/user/jsp/login/login.jsp";
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    break;
            }
        } else {
            viewPath = "/user/jsp/signup/signup.jsp";
        }

        return viewPath;
    }
}
