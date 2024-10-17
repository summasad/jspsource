package service;

import dto.MemberDTO;
import dao.MemberDAO;


public class MemberServiceImpl implements MemberService {
	
	MemberDAO dao = new MemberDAO();

	@Override
	public int create(MemberDTO memberDTO) {
		
		return dao.create(memberDTO);
	}

	@Override
	public MemberDTO read(MemberDTO memberDTO) {
		
		return dao.read(memberDTO);
	}

	@Override
	public int update(MemberDTO memberDTO) {
		
		return dao.update(memberDTO);
	}

	@Override
	public int delete(MemberDTO memberDTO) {
		
		return dao.delete(memberDTO);
	}

}
