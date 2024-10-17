package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action {
	
	private String path;	
	
	public RegisterAction(String path) {
		super();
		this.path = path;
	}



	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1. 가져오기
		//2. Service 호출
		//3. R일 때만 값 전달
		//4. ActionForward 객체
		
		request.getParameter(path);
		
		return new ActionForward(path, true);
	}

}
