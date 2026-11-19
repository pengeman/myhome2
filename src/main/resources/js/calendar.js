document.addEventListener('DOMContentLoaded', function () {
    const dateElement = document.getElementById('date');
    const gregorianElement = document.getElementById('gregorian');
    const lunarElement = document.getElementById('lunar');
    const solarTermsElement = document.getElementById('solarTerms');
    const festivalElement = document.getElementById('festival');

    // 假设这里有一个LunarCalendar的库
    function getLunarInfo(year, month, day) {
        // 伪代码，使用第三方库处理农历
        return {
            lunarDate: '八月初二',
            solarTerm: '白露',
            festival: '教师节'
        };
    }

    function updateCalendar() {
        const today = new Date();
        const year = today.getFullYear();
        const month = today.getMonth() + 1; // 月份从0开始
        const day = today.getDate();

        dateElement.innerText = `${year}年${month}月${day}日`;

        const lunarInfo = getLunarInfo(year, month, day);

        gregorianElement.innerText = `公历：${year}年${month}月${day}日`;
        lunarElement.innerText = `农历：${lunarInfo.lunarDate}`;
        solarTermsElement.innerText = `节气：${lunarInfo.solarTerm}`;
        festivalElement.innerText = `节日：${lunarInfo.festival}`;
    }

    updateCalendar();
});
