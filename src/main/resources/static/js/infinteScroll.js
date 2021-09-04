let today;
let month;
let year;
let day;
var searchDate;
window.onload = function () {
    today = new Date();
    searchDate = today;
    console.log(today);
    month = today.getMonth() + 1;
    year = today.getFullYear();
    day = today.getDate();
    document.getElementById("month").innerHTML = month.toString();
    document.getElementById("year").innerHTML = year.toString();
    document.getElementById("day").innerHTML = day.toString();
}
// 왼쪽, 오른쪽 버튼을 클릭하면 월이 바뀜
function leftClick() {
    searchDate.setDate(searchDate.getDate() - 1)
    console.log(searchDate);
    month = searchDate.getMonth() + 1;
    year = searchDate.getFullYear();
    day = searchDate.getDate();
    document.getElementById("month").innerHTML = month.toString();
    document.getElementById("year").innerHTML = year.toString();
    document.getElementById("day").innerHTML = day.toString();
}

function rightClick() {
    searchDate.setDate(searchDate.getDate() + 1)
    month = searchDate.getMonth() + 1;
    year = searchDate.getFullYear();
    day = searchDate.getDate();
    document.getElementById("month").innerHTML = month.toString();
    document.getElementById("year").innerHTML = year.toString();
    document.getElementById("day").innerHTML = day.toString();
}
// 날짜 계산하는 함수-출처:https://kdinner.tistory.com/68
function timeForToday(value) {
    today = new Date();
    const timeValue = new Date(value);

    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
    if (betweenTime < 1) return '방금전';
    if (betweenTime < 60) {
        return `${betweenTime}분전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일전`;
    }

    return `${Math.floor(betweenTimeDay / 365)}년전`;
}

function customDate() {
    searchDate = document.querySelector("#customDate").value
    let arr = searchDate.split('-')
    year = arr[0];
    month = arr[1];
    day = arr[2];
    if (month[0] === '0') month = month[1];
    if (day[0] === '0') day = day[1];
    document.getElementById("month").innerHTML = month;
    document.getElementById("year").innerHTML = year;
    document.getElementById("day").innerHTML = day;
    searchDate = new Date(parseInt(year, 10), parseInt(month, 10) - 1, parseInt(day, 10));
    console.log(searchDate);
}