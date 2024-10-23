package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceimpl;

@AllArgsConstructor
public class BoardDeleteAction implements Action {
	
	private String path;

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDTO deleteDto = new BoardDTO();
		
		deleteDto.setBno(Integer.parseInt(request.getParameter("bno")));
		deleteDto.setPassword(request.getParameter("password"));
		
		BoardService service = new BoardServiceimpl();
		boolean deleteFlag = service.delete(deleteDto);
		
		if(deleteFlag){
			//성공시 bno 보내기, read 위해
			//path += "";
			
		}else{
			path = "/modify.do?bno="+deleteDto.getBno();
		}
	
		return new ActionForward(path, true);
	}

}
