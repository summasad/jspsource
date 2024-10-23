// 목록 클릭시 actionForm submit
const actionForm = document.querySelector("#actionForm");

document.querySelector("#reply .btn-success").addEventListener("click", () => {
	actionForm.submit();
});