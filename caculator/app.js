// 변수 선언
var inp = document.forms['cal'];   // 현재 문서에 작성되어 있는 from태그 전부 가져와라(2개 이상이면 배열구조로, 인덱스 번호 말고 name속성 값 넣어도 됨)
var input = document.getElementsByTagName("input");
var cls_btn = document.getElementsByClassName("cls_btn")[0];  // 복수이기 때문에(elements) 배열구조로 담길 것. 그래서 인덱스 번호를 붙여서 어디 꺼를 가져올건지 정해줘야함
var result_btn = document.getElementsByClassName("result_btn")[0];

// 계산기 초기화
function clr() {
    //inp['result'].value = 0;
    inp.result.value = 0;
}

// 계산기 입력 처리
function cal(value) {
    
    if (inp['result'].value == 0) {
        inp['result'].value = '';
    }

    inp['result'].value += value;
}

// 계산 결과 처리
function my_result() {
    var result = document.forms['cal']['result']; // cal 하위 result선택
    var cal = eval(result.value);  // eval() = 문자화된 숫자와 수식을 연산 할 수 있게 변환해주는 함수
    inp['result'].value = cal;
}

for (var i = 0; i < input.length; i++) {

    if (input[i].value != "=" || input[i].value != "clear") {

        input[i].onclick = function() {

            if (inp['result'].value == '입력오류') {
                clr();
            } 
                
            cal(this.value);  // 여기서 this = input[i]
        } 
    }
}

cls_btn.onclick = function() {
    clr();
}

result_btn.onclick = function() {
    try {
        my_result();
    } catch (err) {
        var result = inp['result'];
        result.value = '입력오류';
    }
}
