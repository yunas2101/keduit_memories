// HTML 렌더링 이후 동작하는 js code
$(function() {
	let prev = document.getElementById("prev");
	let next = document.getElementById("next");
	let list = document.getElementsByClassName("list")[0];
	//next 다음 버튼 클릭 이벤트
	next.addEventListener("click", function () {
	  let items = document.querySelectorAll(".item");
	  //배열의 첫 번째 요소를 .list의 맨 마지막 자식으로 이동
	  list.appendChild(items[0]);
	});
	//prev 이전 버튼 클릭 이벤트
	prev.addEventListener("click", function () {
	  let items = document.querySelectorAll(".item");
	  //배열의 마지막 요소를 .list의 맨 앞에 자식으로 이동
	  list.prepend(items[items.length - 1]);
	});
	
	
	
	// 페이지 로드 시 초기 모드에 따라 버튼 아이콘을 설정
	document.addEventListener("DOMContentLoaded", function() {
	    let body = document.body;
	    let darkIcon = document.querySelector(".fa-regular.fa-lightbulb");
	    let lightIcon = document.querySelector(".fa-solid.fa-lightbulb");
	
	    if (body.classList.contains("light")) {
	        darkIcon.style.display = "none"; // 어두운 모드 아이콘 숨기기
	        lightIcon.style.display = "inline-block"; // 밝은 모드 아이콘 표시
	    } else {
	        darkIcon.style.display = "inline-block"; // 어두운 모드 아이콘 표시
	        lightIcon.style.display = "none"; // 밝은 모드 아이콘 숨기기
	    }
	});
	
	
	
	// 모드 변경 시 호출되는 함수
	function toggleMode() {
	    let body = document.body;
	
	    // <body>에 'light' 클래스 토글하기
	    body.classList.toggle("light");
	    // 아이콘 토글하기
	    let darkIcon = document.querySelector(".fa-regular.fa-lightbulb");
	    let lightIcon = document.querySelector(".fa-solid.fa-lightbulb");
	
	    if (body.classList.contains("light")) {
	        darkIcon.style.display = "none"; // 어두운 모드 아이콘 숨기기
	        lightIcon.style.display = "inline-block"; // 밝은 모드 아이콘 표시
	    } else {
	        darkIcon.style.display = "inline-block"; // 어두운 모드 아이콘 표시
	        lightIcon.style.display = "none"; // 밝은 모드 아이콘 숨기기
	    }
	
	    // <body>에 'light' 클래스가 있을 때 색상을 화이트로 변경
	    if (body.classList.contains("light")) {
	        document.documentElement.style.setProperty('--color-black', 'black');
	    } else {
	        document.documentElement.style.setProperty('--color-black', 'white');
	    }
	}
	
	// 모드 변경 이벤트 리스너 등록
	let mode = document.getElementById("mode");
	mode.addEventListener("click", toggleMode);
});


