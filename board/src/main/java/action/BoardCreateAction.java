package action;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceimpl;

@AllArgsConstructor
public class BoardCreateAction implements Action {

	private String path;

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDTO insertDto = new BoardDTO();

		insertDto.setTitle(request.getParameter("title"));
		insertDto.setName(request.getParameter("name"));
		insertDto.setContent(request.getParameter("content"));
		insertDto.setPassword(request.getParameter("password"));

		// 첨부파일 가져오기(서블릿 기능 이용)
		Part part = request.getPart("attach");
		String fileName = getFileName(part);
		
		System.out.println(fileName);
		
		// 서버로 전송된 파일 저장(서버의 특정 폴더)
		String saveDir = "c:\\upload";
		if(!fileName.isEmpty()) {
			// 고유의키값_파일명
			UUID uuid = UUID.randomUUID();
			// File.separator : / or \ 운영체제에 맞게 넣어줌
			File f = new File(saveDir + File.separator + uuid + "_" + fileName);
			part.write(f.toString()); //C:\\upload\a4837254-4943-4983-8d5e-7f3250ba2611_image1.jpg
			insertDto.setAttach(f.getName()); //a4837254-4943-4983-8d5e-7f3250ba2611_image1.jpg
		}

		BoardService service = new BoardServiceimpl();
		boolean insertFlag = service.create(insertDto);
		if (insertFlag) {

			// path += "/read.do?bno="+insertDto.getBno();

		} else {
			path = "/board/create.jsp";
		}

		return new ActionForward(path, true);
	}
//성공하면 리드 실패하면 create.jsp

	private String getFileName(Part part) {
		//content-disposition(웹 헤더에서 파일 키네임) : attachment; filename=file.jpg
		String header = part.getHeader("content-disposition");
		String[] arr = header.split(";");
		for(int i = 0; i<arr.length; i++) {
			String temp = arr[i];
			if(temp.trim().startsWith("filename")) {
				return temp.substring(temp.indexOf("=")+2, temp.length()-1);
			}
		} return "";
	} 
}
