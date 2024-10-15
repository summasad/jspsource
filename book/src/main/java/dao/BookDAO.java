package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BookDTO;

public class BookDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	static {
		// 드라이버 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##java";
		String password = "12345";

		return DriverManager.getConnection(url, user, password);

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

	// CRUD 메소드

	// R(read) - 전체조회, 특정(pk) 조회, 제목 조회
	// 조회 메소드 작성
	// 리턴 타입 : List<~~DTO> or ~~DTO ==> sql 구문 보고 결정
	// List<~~DTO> : 값이 복수일때 = where 절 없는 경우, where 절이 pk이 아니면
	// ~~DTO : 값이 하나일때 = where 절이 pk인 경우

	// 전달인자 : () 에 어떻게 작성할 것인가?
	// => sql 구문의 ? 보고 결정
	public BookDTO getRow(int code) {
		BookDTO dto = null;
		try {
			con = getConnection();
			String spl = "SELECT * FROM BOOKTBL WHERE CODE = ?";
			pstmt = con.prepareStatement(spl);

			// sql 구문의 ? 해결
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			// where 전에 pk 사용된 경우 하나만 추출됨
			if (rs.next()) {
				dto = new BookDTO();
				dto.setCode(rs.getInt("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				dto.setDescription(rs.getString("description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return dto;

	}

	public List<BookDTO> getList() {
		List<BookDTO> list = new ArrayList<BookDTO>();

		try {
			con = getConnection();
			String sql = "SELECT * FROM BOOKTBL";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 행 하나씩 DTO에 담기 List 에 추가
				BookDTO dto = new BookDTO();
				dto.setCode(rs.getInt("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}

	// U(update) - 수정 메소드
	// 리턴타입 : int
	// 전달인자 : ?보고 결정
	public int update(BookDTO updateDto) {
		int updateRow = 0;
		try {
			con = getConnection();
			String sql = "UPDATE BOOKTBL SET PRICE = ?, DESCRIPTION = ? WHERE CODE = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, updateDto.getPrice());
			pstmt.setString(2, updateDto.getDescription());
			pstmt.setInt(3, updateDto.getCode());
			
			updateRow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return updateRow;
	}
	// D(delete) - 삭제
	// 리턴 타입 int
	public int delete(int code) {
		int deleteRow = 0;
		try {
			con = getConnection();
			String sql = "DELETE BOOKTBL WHERE CODE = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			deleteRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
			
		}return deleteRow;
	}
	// C(create) - 삽입
	// 리턴 타입 int
	public int insert(BookDTO insertDto) {
		int insertRow = 0; 
		try {
			con = getConnection();
			String sql = "INSERT INTO BOOKTBL VALUES(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,insertDto.getCode());
			pstmt.setString(2,insertDto.getTitle());
			pstmt.setString(3,insertDto.getWriter());
			pstmt.setInt(4,insertDto.getPrice());
			pstmt.setString(5,insertDto.getDescription());
			
			insertRow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return insertRow;
	}
	

}
