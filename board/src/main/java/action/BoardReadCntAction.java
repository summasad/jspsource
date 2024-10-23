package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceimpl;

@AllArgsConstructor
public class BoardReadCntAction implements Action {
	
	private String path;

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardService service = new BoardServiceimpl();
		
		// 조회수 업데이트
		service.hitUpdate(bno);
		
		path += "?bno="+bno;
	
		return new ActionForward(path, true);
	}

}
