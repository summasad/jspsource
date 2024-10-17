package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter

public class MemberDTO {
	// 테이블과 동일한 모양
	
	private String userid;
	private String name;	
	private String password;
}
