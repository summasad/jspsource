package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
