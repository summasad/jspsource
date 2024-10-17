//목록 버튼 클릭 시 리스트 로 이동
document.querySelector(".btn-primary").addEventListener("click",()=>{
	//페이지 이동
	location.href = "list_pro.jsp"
}); 

document.querySelector("body div:nth-child(3) form").addEventListener("submit",(e)=>{
	//수정 클릭 시 price 의 값이 숫자가 들어있는지 확인
	e.preventDefault();
	const price = document.querySelector("#price");
	const regEx = /^[0-9]{3,10}$/;
	//* :0~무한대 {3, 10} =3자리~10자리
	if(!regEx.test(price.value)){
		alert("가격을 입력하세요")
		price.select(); // focus + 입력값 존재 시 블럭으로 잡아줌
		return;
	} e.target.submit();
});

document.querySelector(".btn-danger").addEventListener("click",()=>{
	location.href = "delete_pro.jsp?code="+code
});