//새로고침 시 현재 날짜의 연, 월 불러와서 span tag에 쓰기
// 목록 불러올 때 사용할 변수 year, month
let today;
let month;
let year;
window.onload = function () {
    today = new Date();
    month = today.getMonth() + 1;
    year = today.getFullYear();
    console.log(month);
    console.log(year);
    document.getElementById("month").innerHTML = month.toString();
    document.getElementById("year").innerHTML = year.toString();
}
// 왼쪽, 오른쪽 버튼을 클릭하면 월이 바뀜
function leftClick(){
    if(month===1){
        month=12;
        year-=1;
    }
    else{
        month-=1;
    }
    document.getElementById("month").innerHTML = month.toString();
    document.getElementById("year").innerHTML = year.toString();
}
function rightClick(){
    if(month===12){
        month=1;
        year+=1;
    }
    else{
        month+=1;
    }
    document.getElementById("month").innerHTML = month.toString();
    document.getElementById("year").innerHTML = year.toString();
}