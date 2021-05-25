// 소리 on/off(오디오객체 이용)
// 배너 열고 닫기
// 랜덤으로 떨어지게 하기

// html에 있던 태그를 타겟잡아 스크립트로 가져오는 부분
// 왜? 나중에 쓸거니까
var banner = document.getElementById('banner');
var imgs = document.getElementsByTagName('img');
var toggle = document.getElementById('toggle');
var sound_btn = document.getElementById('sound_btn');

// css 속성 준 것중에 banner를 타겟으로 잡은 애의 높이
var banner_height = getComputedStyle(banner).height;

var cast = [];

// {} : 객체를 만드는 방법 중 하나(이전에는 객체 생성 함수를 만들어서 new로 객체를 생성했죠)
// 풍선의 위치가 어떻게 될건지에 대한 부분
function set_balloon(num) {

    var x = Math.floor(Math.random() * (500 - 10) + 10),
        y = Math.floor(Math.random() * (400 - 120) + 120),
        size = Math.floor(Math.random() * (200 - 100) + 100),
        angle = Math.floor(Math.random() * (360 - 0) + 0),
        speed = Math.random() * (2 - 0) + 0;

    cast[num] = {
        x: x,  //앞에 x가 변수, 뒤에 x가 값
        y: -y,  // 왜 -y냐면 아래로 떨어져야 하니까(위로 가면 +)   
        size: size,
        angle: angle,
        speed: speed
    }
}

// 풍선의 시작지점 정해주는 함수
function ball_init() {

    for (i = 0; i < imgs.length; i++) {
        set_balloon(i);

        imgs[i].style.left = '-9999px';
        imgs[i].style.top = '9999px';

    }
}

// 움직임을 위한 함수
function animate_balloon() {

    for (i = 0; i < imgs.length; i++) {
        imgs[i].style.left = cast[i].x + 'px';  // css니까 단위값을 꼭 적어줘야함
        imgs[i].style.top = cast[i].y + 'px';
        imgs[i].style.transform = 'rotate(' + cast[i].angle + 'deg)';   // deg 각도의 단위임(px처럼)

        if (cast[i].y < parseInt(banner_height)) {

            // 떨어질 때 y축의 좌표를 찍으면서 내려오는건데 그 좌표값이 작으면 통통통 찍으니까 늦게 내려오는것처럼 보일테고, 좌표값 사이의 텀이 크면 퉁퉁퉁 찍으니까 빨리 내려오는 것처럼 보일테고
            cast[i].y += 1 + cast[i].speed; // 그럼 여기 -하면 올라감?, 어떻게 떨어질거냐
            cast[i].angle += cast[i].speed; // 회전의 속도?
        } else {    // 배너에서 사라지면
            set_balloon(i);  // 다시 위로 올라가야하니까
        }

    }
}

function bgm_init() {
    var bgm = new Audio();
    bgm.src = 'bgm.mp3';
    bgm.loop = true;
    // bgm.play(); 해당 코드는 그동안 축적된 데이터를 바탕으로 사용자들이 화면 실행하자마자 켜는 거 싫어하더라 라는 의견을 수용해 바로 플레이 하는 걸 막아놨다

    // 아래 코드는 스크립트의 객체를 html에 넣을 때 사용하는 것이다
    // 바디태그의 자식으로 bgm을 맨 마지막에 붙여줘라(append는 현재 요소의 가장 뒤쪽에 붙는다)
    document.body.appendChild(bgm);
}

ball_init();    // 풍선 위치 잡기 시작

setInterval(() => { // 0.3초마다 반복
    animate_balloon();
}, 30);

bgm_init(); // 음악 시작

// class속성을 기준(조건)을 주기 위해서도 쓰인다
// class속성이 active일 때 src의 값이 변경된다 뭐 이런 식으로
sound_btn.onclick = function (event) {

    var attr = sound_btn.getAttribute('class');
    var bgm = document.getElementsByTagName('audio');

    // 지금 소리가 나오있는 상황에서 버튼을 클릭하면 멈춰야함
    if (attr == 'active') {
        sound_btn.removeAttribute('class');
        sound_btn.setAttribute('src', 'images/sound_on.png')
        bgm[0].pause();

    } else {
        sound_btn.setAttribute('class', 'active');
        sound_btn.setAttribute('src', 'images/sound_off.png');
        bgm[0].play();
    }

    // event.stopPropagation()는 해당 이벤트가 전파되는 것을 막습니다.
    // 즉, 이벤트 버블링의 경우 클릭한 요소의 이벤트를 발생시키고 상위 요소로 이벤트가 전달되는 것을 막고,
    // 캡쳐링의 경우에는 최상위 요소의 이벤트만 발생시키고 하위 요소들로 이벤트를 전달하지 않습니다.
    // event.stopPropagation();
}

toggle.onclick = function() {

    var attr = banner.getAttribute('class');

    if (attr == 'active') {
        banner.removeAttribute('class');
        toggle.innerHTML = '배너열기';
         return false;
    } else {
        banner.setAttribute('class', 'active');
        toggle.innerHTML = '배너닫기';
         return false;
    }

}

banner.onclick = function() {
    window.open("http://www.naver.com", "");
}