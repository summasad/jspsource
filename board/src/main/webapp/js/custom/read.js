// 목록 클릭 시
// actionForm action="/list.do" 수정 후 submit
const actionForm = document.querySelector("#actionForm");
const readForm = document.querySelector("#readForm");

document.querySelector("#readForm .btn-success").addEventListener("click", () => {
	actionForm.querySelector("[name='bno']").remove();
	actionForm.action = "/list.do"
	actionForm.submit();
});

// 수정 클릭시
// actionForm action="/modify.do" 수정 후 submit
document.querySelector("#readForm .btn-primary").addEventListener("click", () => {
	//값이 있다면
	actionForm.action = "/modify.do"
	actionForm.submit();
});

//modify.jsp에서 수정 클릭시 (submit)
//readForm password 값이 있는 지 확인하고
// 없다면 msg 띄우고 있으면 submit
readForm.addeaddEventListener("submit", (e) => {
	e.preventDefault();
	const title = readForm.querySelector("#title");
	const content = readForm.querySelector("#content");
	const password = readForm.querySelector("#password");

	if (title.value === "") {
		alert("제목을 입력하세요")
		title.focus();
		return;
	} else if (content.value === "") {
		alert("내용을 입력하세요")
		content.focus();
		return;
	} else if (password.value === "") {
		alert("비밀번호를 입력하세요")
		password.focus();
		return;
	}

	readForm.submit();
})


// 삭제 클릭시
// readForm action= /delete.do 변경 후 readform submit
document.querySelector("#readForm .btn-danger").addEventListener("click", () => {
	readForm.action = "/delete.do"
	readForm.submit();
});