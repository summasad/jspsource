package action;

import java.net.URLEncoder;
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

		// 페이지 나누기
		int page = Integer.parseInt(request.getParameter("page"));
		int amount = Integer.parseInt(request.getParameter("amount"));				
				
		// 검색기능 추가
		String criteria = request.getParameter("criteria");
		String keyword = URLEncoder.encode(request.getParameter("keyword"), "utf-8");		
				
		BoardService service = new BoardServiceimpl();
		boolean updateFlag = service.update(updateDto);
		if(updateFlag){
			//성공시 bno 보내기, read 위해
			path += "?bno="+updateDto.getBno() + "&page="+page+"&amount="+amount+"&criteria="+criteria+"&keyword="+keyword;
			
		}else{
			path = "/modify.do?bno="+updateDto.getBno() + "&page="+page+"&amount="+amount+"&criteria="+criteria+"&keyword="+keyword;
		}
	
		return new ActionForward(path, true);
	}

}
