package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceimpl;

@AllArgsConstructor
public class BoardUpdateAction implements Action {
	
	private String path;

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDTO updateDto = new BoardDTO();
		updateDto.setBno(Integer.parseInt(request.getParameter("bno")));
		updateDto.setTitle(request.getParameter("title"));
		updateDto.setContent(request.getParameter("content"));
		updateDto.setPassword(request.getParameter("password"));
		
		
		BoardService service = new BoardServiceimpl();
		boolean updateFlag = service.update(updateDto);
		if(updateFlag){
			//성공시 bno 보내기, read 위해
			path += "?bno="+updateDto.getBno();
			
		}else{
			path = "/modify.do?bno="+updateDto.getBno();
		}
	
		return new ActionForward(path, true);
	}

}
