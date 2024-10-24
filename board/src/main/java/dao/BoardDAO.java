package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;
import dto.SearchDTO;

public class BoardDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

//	static {
//		// 드라이버 로드
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	public Connection getConnection() {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/oracle");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<BoardDTO> getList(SearchDTO searchDTO){
		List<BoardDTO> list = new ArrayList<>();
		//시작번호
		int start = searchDTO.getPage() * searchDTO.getAmount();
		//끝번호
		int end = (searchDTO.getPage()-1) * searchDTO.getAmount();
		try {
			con = getConnection();
			String sql = "SELECT bno,name,title,readcnt,regdate,re_lev ";
			sql += "FROM (SELECT rownum rnum, bno,name,title,readcnt,regdate,re_lev ";
			sql += "FROM (SELECT bno,name,title,readcnt,regdate,re_lev FROM BOARD ";
			if(!searchDTO.getCriteria().isBlank()) {				
				sql += "WHERE "+searchDTO.getCriteria()+" LIKE ? ";
				sql += "order by RE_REF DESC, RE_SEQ ASC) ";		
				sql += "WHERE rownum <= ?) ";
				sql += "WHERE rnum > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+searchDTO.getKeyword()+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}else {									
				sql += "order by RE_REF DESC, RE_SEQ ASC) ";
				sql += "WHERE rownum <= ?) ";
				sql += "WHERE rnum > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}										
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setReLev(rs.getInt("re_lev"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}
	
	public BoardDTO read(int bno) {
		BoardDTO dto = null;
		try {
			con = getConnection();
			String sql = "SELECT * FROM board WHERE bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setAttach(rs.getString("attach"));
				dto.setName(rs.getString("name"));
				
				//댓글에 필요한 정보
				dto.setReRef(rs.getInt("re_ref"));
				dto.setReLev(rs.getInt("re_lev"));
				dto.setReSeq(rs.getInt("re_seq"));
				}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return dto;
	}
	
	public int update(BoardDTO updateDto) {
		int updateRow = 0;
		try {
			con = getConnection();
			String sql = "UPDATE BOARD SET title=?, content=? WHERE BNO=? AND PASSWORD=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateDto.getTitle());
			pstmt.setString(2, updateDto.getContent());
			pstmt.setInt(3, updateDto.getBno());
			pstmt.setString(4, updateDto.getPassword());
			
			updateRow= pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		} return updateRow; 
	} 
	
	public int delete(BoardDTO deleteDto) {
		int deleteRow = 0;
		try {
			//비밀번호가 일치하면
			con = getConnection();
			String sql = "DELETE BOARD WHERE BNO = ? AND PASSWORD=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deleteDto.getBno());
			pstmt.setString(2, deleteDto.getPassword());
			deleteRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		} return deleteRow; 
	}
	
	public int insert(BoardDTO insertDto) {
		int insertRow = 0;
		//int bno = 0;
		try {
			con = getConnection();
//			String sql = "SELECT BOARD_SEQ.nextval, From DUAL";
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				bno = rs.getInt(1);
//			}
			
			String sql = "INSERT INTO BOARD(BNO,NAME,PASSWORD,title,content,attach,re_ref,re_lev,re_seq) ";
			sql += "VALUES(board_seq.nextval,?,?,?,?,?,board_seq.currval,0,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, insertDto.getName());
			pstmt.setString(2, insertDto.getPassword());
			pstmt.setString(3, insertDto.getTitle());
			pstmt.setString(4, insertDto.getContent());
			pstmt.setString(5, insertDto.getAttach());
			insertRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		} return insertRow;
	}
	
	//조회수 증가
	public int updateReadCnt(int bno) {
		int updateRow=0;	
		try {
			con = getConnection();
			String sql = "UPDATE BOARD SET readcnt = READCNT + 1 WHERE BNO =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			updateRow= pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		} return updateRow;  
	}
	
	public int replyInsert(BoardDTO replyDto) {
		int insertRow = 0;
		//부모글 정보
		int bno = replyDto.getBno();
		int reRef = replyDto.getReRef();
		int reLev = replyDto.getReLev();
		int reSeq = replyDto.getReSeq();
		
		try {
			con = getConnection();
			
			String sql = "UPDATE BOARD SET RE_SEQ =RE_SEQ +1 WHERE RE_REF = ? AND RE_SEQ >?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reRef);
			pstmt.setInt(2, reSeq);
			
			pstmt.executeUpdate();
			
			sql = "INSERT INTO BOARD(BNO,NAME,PASSWORD,title,content,re_ref,re_lev,re_seq) ";
			sql += "VALUES(board_seq.nextval,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, replyDto.getName());
			pstmt.setString(2, replyDto.getPassword());
			pstmt.setString(3, replyDto.getTitle());
			pstmt.setString(4, replyDto.getContent());
			pstmt.setInt(5, reRef);
			pstmt.setInt(6, reLev + 1);
			pstmt.setInt(7, reSeq + 1);
			
			insertRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		} return insertRow;
	}
	
	//전체 게시물 개수 리턴 메소드
	public int getTotalRows(SearchDTO searchDTO){		
		int total = 0;
		try {
			con = getConnection();
			
			if (!searchDTO.getCriteria().isBlank()) {
				String sql = "SELECT count(*) FROM board WHERE "+searchDTO.getCriteria()+" LIke ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+searchDTO.getKeyword()+"%");
			} else {
				String sql = "SELECT count(*) FROM board";
				pstmt = con.prepareStatement(sql);
			}

			rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		} return total;
		
	}
	
}
