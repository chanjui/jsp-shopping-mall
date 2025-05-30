package admin.action;

import admin.dao.BoardDao;
import admin.vo.BoardVO;
import user.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminSearchBoardInquiryAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchType = request.getParameter("searchType");
        String searchValue = request.getParameter("searchValue");

        BoardVO[] ar = BoardDao.searchBoard(searchType, searchValue);
        request.setAttribute("biar", ar);
        return "/admin/jsp/ajaxBoardInquiryList.jsp";
    }
}
