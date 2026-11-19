<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#-- 要替换的参数: title, rows, footer_var -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <base target="_blank" />
    <title>${title}</title>
    <script src="https://cdn.jsdelivr.net/npm/lunar-javascript/lunar.js"></script>
    <style>
        * {
            box-sizing: border-box;
        }
        body {
            margin: 0;
            padding: 24px 24px 48px;
            background: #f4f6f9;
            color: #263238;
            font-family: "Microsoft YaHei", sans-serif;
        }
        .hide {
            display: none;
        }

        /* 页面主容器 */
        .page-container {
            max-width: 1320px;
            margin: 0 auto;
        }

        /* 顶部搜索区 */
        .search-section {
            background: #fff;
            padding: 20px 24px;
            border-radius: 14px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.05);
            margin-bottom: 24px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .search-logos {
            margin-bottom: 12px;
            height: 44px;
            display: flex;
            align-items: center;
        }
        .search-logos img {
            max-height: 44px;
        }
        .search-bar {
            display: flex;
            gap: 8px;
            width: 100%;
            max-width: 660px;
            margin-bottom: 10px;
        }
        .search-input {
            flex: 1;
            height: 38px;
            padding: 0 12px;
            border: 1px solid #f5b7c2;
            border-radius: 6px;
            background-color: #FDD6DC;
            font-size: 15px;
            outline: none;
        }
        .search-input:focus {
            border-color: #e87a90;
        }
        .search-btn {
            height: 38px;
            padding: 0 18px;
            border: 1px solid #f5b7c2;
            border-radius: 6px;
            background-color: #FDD6DC;
            cursor: pointer;
            font-weight: bold;
            color: #333;
        }
        .search-btn:hover {
            background-color: #fbc3cc;
        }
        .clear-btn {
            height: 38px;
            padding: 0 14px;
            border: 1px solid #dce3ec;
            border-radius: 6px;
            background: #f4f6f9;
            cursor: pointer;
            color: #555;
        }
        .clear-btn:hover {
            background: #e8eef5;
        }
        .search-options {
            display: flex;
            align-items: center;
            gap: 16px;
            font-size: 14px;
            color: #546e7a;
        }
        .search-options label {
            cursor: pointer;
            display: inline-flex;
            align-items: center;
            gap: 4px;
        }
        .version-tag {
            font-size: 12px;
            color: #90a4ae;
            margin-right: 8px;
        }

        /* 下方左右分栏布局 */
        .main-grid {
            display: flex;
            align-items: flex-start;
            gap: 24px;
        }

        /* 左侧栏：实用查询 + 便签 */
        .sidebar {
            width: 340px;
            flex-shrink: 0;
            display: flex;
            flex-direction: column;
            gap: 24px;
        }
        .card {
            background: #fff;
            padding: 20px;
            border-radius: 14px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.05);
        }
        .card h2 {
            margin: 0 0 14px;
            font-size: 18px;
            color: #263238;
            border-bottom: 1px solid #edf0f2;
            padding-bottom: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        /* 实用查询网格 */
        .links-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 10px 8px;
        }
        .links-grid a {
            text-decoration: none;
            color: #37474f;
            font-size: 13px;
            padding: 6px 4px;
            border-radius: 6px;
            text-align: center;
            background: #f8fafc;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            transition: background 0.15s, color 0.15s;
        }
        .links-grid a:hover {
            background: #eaf3ff;
            color: #1976d2;
        }

        /* 临时便签 */
        .memo-status {
            font-size: 12px;
            font-weight: normal;
            color: #4caf50;
        }
        .memo-box {
            width: 100%;
            height: 240px;
            padding: 12px;
            border: 1px solid #c8e6c9;
            border-radius: 8px;
            background: #fafffa;
            font-family: inherit;
            font-size: 15px;
            line-height: 1.6;
            resize: vertical;
            outline: none;
        }
        .memo-box:focus {
            border-color: #66bb6a;
        }

        /* 右侧主栏：日历组件 */
        .calendar {
            flex: 1;
            min-width: 0;
            padding: 24px;
            background: #fff;
            border-radius: 14px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.05);
        }
        .calendar header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 12px;
            margin-bottom: 20px;
        }
        .calendar h1 {
            margin: 0;
            font-size: 22px;
        }
        .calendar button {
            padding: 8px 14px;
            border: 0;
            border-radius: 6px;
            background: #e8eef5;
            cursor: pointer;
        }
        .calendar button:hover {
            background: #d7e3ef;
        }
        .weekdays, .days {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
        }
        .weekdays div {
            padding: 12px 4px;
            text-align: center;
            color: #65727e;
            font-weight: bold;
        }
        .day {
            min-height: 88px;
            padding: 8px;
            border: 1px solid #edf0f2;
            cursor: pointer;
        }
        .day:hover {
            background: #f5f9ff;
        }
        .day.other-month {
            color: #b7bec5;
            background: #fafbfc;
        }
        .day.today {
            outline: 2px solid #4788e8;
            outline-offset: -2px;
        }
        .day.selected {
            background: #eaf3ff;
        }
        .solar-day {
            font-size: 20px;
            font-weight: bold;
        }
        .lunar-day {
            margin-top: 5px;
            color: #77838d;
            font-size: 12px;
        }
        .event {
            margin-top: 4px;
            overflow: hidden;
            color: #d65b45;
            font-size: 11px;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .detail {
            margin-top: 18px;
            padding: 14px;
            border-radius: 8px;
            background: #f7f9fb;
            line-height: 1.8;
        }

        /* 底部状态栏 */
        #footer {
            height: 24px;
            line-height: 24px;
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            padding-right: 12px;
            text-align: right;
            background: rgba(187, 51, 51, 0.2);
            color: #7766ff;
            font-family: Arial, sans-serif;
            font-size: 12px;
            letter-spacing: 1px;
        }

        /* 响应式适配：小屏幕下自动转为上下单栏 */
        @media (max-width: 900px) {
            .main-grid {
                flex-direction: column;
            }
            .sidebar {
                width: 100%;
            }
            .calendar {
                width: 100%;
                padding: 16px;
            }
        }
        @media (max-width: 600px) {
            body { padding: 12px 12px 40px; }
            .day { min-height: 72px; padding: 5px; }
            .event { font-size: 10px; }
        }
    </style>
</head>

<body>
    <div class="page-container">
        <!-- 顶部搜索引擎区域 -->
        <form action="https://www.baidu.com/s" method="get" target="_blank" id="form1" class="search-section">
            <div class="search-logos">
                <a href="https://www.baidu.com/" id="s1" style="display: block;"><img src="image/baidu2.png" alt="百度" /></a>
                <a href="https://www4.bing.com/" id="s2" style="display: none;"><img src="image/binglog.jpeg" alt="Bing" /></a>
                <a href="https://www.google.com/" id="s3" style="display: none;"><img src="image/google.png" alt="Google" /></a>
            </div>

            <div class="search-bar">
                <input type="text" name="word" id="word" class="search-input" placeholder="输入搜索关键词..." />
                <input type="submit" id="search_btn" class="search-btn" value="搜 索" />
                <input type="button" class="clear-btn" value="清空" onclick="cls()" />
            </div>

            <div class="search-options">
                <span class="version-tag">ver 20261115</span>
                <label><input type="radio" id="r1" name="engine" onclick="radioClicked(1)" checked="checked" /> 百度</label>
                <label><input type="radio" id="r2" name="engine" onclick="radioClicked(2)" /> Bing</label>
                <label><input type="radio" id="r3" name="engine" onclick="radioClicked(3)" /> Google</label>
            </div>
        </form>

        <!-- 下方左右分栏主体 -->
        <div class="main-grid">
            <!-- 左侧边栏：实用查询 + 临时便签 -->
            <aside class="sidebar">
                <div class="card">
                    <h2>实用查询</h2>
                    <div class="links-grid">
                        <a href="https://www.12306.cn/index/" title="定机票火车票酒店">12306</a>
                        <a href="https://www.lddgo.net/index">在线工具</a>
                        <a href="https://www.bootschool.net/ascii-art">ascii艺术</a>
                        <a href="http://tools.2345.com/weizhang.htm">交通违章</a>
                        <a href="http://tools.2345.com/carlist.htm">查车牌号</a>
                        <a href="https://www.lddgo.net/index">ascii艺术</a>
                        <a href="https://www.congminglingxi.com/" title="AI写作">聪明灵犀</a>
                        <a href="http://tools.2345.com/shenfenzheng.htm">查身份证</a>
                        <a href="https://web.shiguangxu.com/">时光序</a>
                        <a href="http://tools.2345.com/kuaidi.htm">快递查询</a>
                        <a href="http://tools.2345.com/youbian.htm">邮编查询</a>
                        <a href="http://tools.2345.com/huafcx.htm">话费查询</a>
                        <a href="http://dict.youdao.com/">有道英汉</a>
                        <a href="http://www.jd.com">京东</a>
                        <a href="http://www.taobao.com">淘宝</a>
                        <a href="https://www.processon.com/diagrams">在线 uml</a>
                        <a href="https://mvnrepository.com/">maven仓库</a>
                        <a href="http://sucicada.cf:800/">sucicada</a>
                        <a href="http://fanyi.youdao.com/">在线翻译</a>
                        <a href="https://www.cilixiong.com/">磁力熊</a>
                        <a href="http://tools.2345.com/jishunqi.htm">在线计算</a>
                        <a href="https://www.shiguangxu.com/">时光序</a>
                        <a href="http://blog.chinaunix.net/" title="chinaunix">我的博客</a>
                        <a href="http://www.xnote.cn/note/category/" title="xnote">在线笔记</a>
                        <a href="https://www.speedtest.cn" title="speedtest">在线测速</a>
                        <a href="https://www.cnblogs.com/" title="cnblogs">我的博客</a>
                        <a href="https://home.zoho.com.cn/home">zoho笔记</a>
                        <#if rows??>
                            <#list rows as bookmark>
                                <a href="${bookmark.url}" title="${bookmark.name}">${bookmark.name}</a>
                            </#list>
                        </#if>
                    </div>
                </div>

                <div class="card">
                    <h2>
                        <span>临时便签</span>
                        <span id="memoStatus" class="memo-status">已同步本地</span>
                    </h2>
                    <textarea id="memoInput" class="memo-box" placeholder="在此输入临时笔记，内容会自动保存在浏览器本地..."></textarea>
                </div>
            </aside>

            <!-- 右侧主栏：农历日历 -->
            <main class="calendar">
                <header>
                    <button id="prev" type="button">上个月</button>
                    <h1 id="monthTitle"></h1>
                    <button id="next" type="button">下个月</button>
                </header>

                <div class="weekdays">
                    <div>星期一</div><div>星期二</div><div>星期三</div>
                    <div>星期四</div><div>星期五</div><div>星期六</div><div>星期日</div>
                </div>
                <div class="days" id="days"></div>
                <section class="detail" id="detail"></section>
            </main>
        </div>
    </div>

    <div class="hide"></div>
    <div id="footer">${footer_var}</div>

    <script>
    <#noparse>
        // 1. 搜索引擎切换与清空
        function cls() {
            const wordInput = document.getElementById("word");
            wordInput.value = "";
            wordInput.focus();
        }

        function radioClicked(engineIndex) {
            const configs = {
                1: { logoId: "s1", paramName: "word", action: "https://www.baidu.com/s" },
                2: { logoId: "s2", paramName: "q", action: "https://www4.bing.com/search" },
                3: { logoId: "s3", paramName: "q", action: "https://www.google.com/search" }
            };

            const current = configs[engineIndex];
            if (!current) return;

            ["s1", "s2", "s3"].forEach(id => {
                document.getElementById(id).style.display = (id === current.logoId) ? "block" : "none";
            });

            document.getElementById("word").name = current.paramName;
            document.getElementById("form1").action = current.action;
        }

        // 2. 临时便签 localStorage 自动保存
        const memoInput = document.getElementById("memoInput");
        const memoStatus = document.getElementById("memoStatus");
        const MEMO_STORAGE_KEY = "nav_page_temp_memo";
        let saveTimer = null;

        const savedMemo = localStorage.getItem(MEMO_STORAGE_KEY);
        if (savedMemo !== null) {
            memoInput.value = savedMemo;
        }

        memoInput.addEventListener("input", () => {
            memoStatus.textContent = "保存中...";
            memoStatus.style.color = "#ff9800";

            clearTimeout(saveTimer);
            saveTimer = setTimeout(() => {
                localStorage.setItem(MEMO_STORAGE_KEY, memoInput.value);
                memoStatus.textContent = "已自动保存";
                memoStatus.style.color = "#4caf50";
            }, 300);
        });

        // 3. 农历日历渲染逻辑
        const daysElement = document.getElementById("days");
        const titleElement = document.getElementById("monthTitle");
        const detailElement = document.getElementById("detail");

        const today = new Date();
        let viewYear = today.getFullYear();
        let viewMonth = today.getMonth();
        let selectedDate = new Date(today.getFullYear(), today.getMonth(), today.getDate());

        const solarFestivals = {
            "1-1": "元旦",
            "2-14": "情人节",
            "3-8": "妇女节",
            "3-12": "植树节",
            "4-1": "愚人节",
            "5-1": "劳动节",
            "5-4": "青年节",
            "6-1": "儿童节",
            "7-1": "建党节",
            "8-1": "建军节",
            "9-10": "教师节",
            "10-1": "国庆节",
            "12-24": "平安夜",
            "12-25": "圣诞节"
        };

        function dateKey(date) {
            return `${date.getFullYear()}-${date.getMonth()}-${date.getDate()}`;
        }

        function getLunarInfo(date) {
            const solar = Solar.fromDate(date);
            const lunar = solar.getLunar();

            const lunarDay = lunar.getDayInChinese();
            const lunarText = lunar.getDay() === 1
                ? `${lunar.getMonthInChinese()}月`
                : lunarDay;

            const festivals = [
                ...(solar.getFestivals?.() || []),
                ...(lunar.getFestivals?.() || []),
                ...(lunar.getOtherFestivals?.() || [])
            ];

            const solarFestival = solarFestivals[`${date.getMonth() + 1}-${date.getDate()}`];
            if (solarFestival) festivals.unshift(solarFestival);

            const jieQi = lunar.getJieQi?.() || "";
            if (jieQi) festivals.unshift(jieQi);

            return {
                solar,
                lunar,
                lunarText,
                jieQi,
                festivals: [...new Set(festivals)]
            };
        }

        function renderCalendar() {
            titleElement.textContent = `${viewYear}年${viewMonth + 1}月`;
            daysElement.replaceChildren();

            const firstDay = new Date(viewYear, viewMonth, 1);
            const offset = (firstDay.getDay() + 6) % 7;
            const gridStart = new Date(viewYear, viewMonth, 1 - offset);

            for (let i = 0; i < 42; i++) {
                const date = new Date(gridStart);
                date.setDate(gridStart.getDate() + i);

                const info = getLunarInfo(date);
                const cell = document.createElement("div");
                cell.className = "day";

                if (date.getMonth() !== viewMonth) cell.classList.add("other-month");
                if (dateKey(date) === dateKey(today)) cell.classList.add("today");
                if (dateKey(date) === dateKey(selectedDate)) cell.classList.add("selected");

                const solarDay = document.createElement("div");
                solarDay.className = "solar-day";
                solarDay.textContent = date.getDate();

                const lunarDay = document.createElement("div");
                lunarDay.className = "lunar-day";
                lunarDay.textContent = info.lunarText;

                cell.append(solarDay, lunarDay);

                if (info.festivals.length) {
                    const event = document.createElement("div");
                    event.className = "event";
                    event.textContent = info.festivals[0];
                    cell.appendChild(event);
                }

                cell.addEventListener("click", () => {
                    selectedDate = new Date(date);
                    renderCalendar();
                    renderDetail(date);
                });

                daysElement.appendChild(cell);
            }

            renderDetail(selectedDate);
        }

        function renderDetail(date) {
            const info = getLunarInfo(date);
            const weekDay = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];

            detailElement.replaceChildren();

            const lines = [
                `公历：${date.getFullYear()}年${date.getMonth() + 1}月${date.getDate()}日 ${weekDay[date.getDay()]}`,
                `农历：${info.lunar.getYearInChinese()}年${info.lunar.getMonthInChinese()}月${info.lunar.getDayInChinese()}`,
                `干支：${info.lunar.getYearInGanZhi()}年 ${info.lunar.getMonthInGanZhi()}月 ${info.lunar.getDayInGanZhi()}日`
            ];

            for (const text of lines) {
                const div = document.createElement("div");
                div.textContent = text;
                detailElement.appendChild(div);
            }
        }

        document.getElementById("prev").addEventListener("click", () => {
            viewMonth--;
            if (viewMonth < 0) { viewMonth = 11; viewYear--; }
            renderCalendar();
        });

        document.getElementById("next").addEventListener("click", () => {
            viewMonth++;
            if (viewMonth > 11) { viewMonth = 0; viewYear++; }
            renderCalendar();
        });

        renderCalendar();
        </#noparse>
    </script>
</body>
</html>