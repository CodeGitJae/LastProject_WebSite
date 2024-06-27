const apikey = "7I86%2BkwZRg7drfjl1VYsPjf87SUYpH9C8qiinq4yGhtdvzKDP26bRezIP%2FKNbTkTeKerSADF3S0Pxsllv9lS4w%3D%3D";
    let url = 'http://apis.data.go.kr/B090041/openapi/service/LunPhInfoService/getLunPhInfo?';
    url += 'serviceKey=' + apikey + '&numOfRows=35&_type=json';

    document.addEventListener('DOMContentLoaded', async function() {
      var calendarEl = document.getElementById('calendar');
      
      // 월령 데이터를 캐시에 저장할 객체
      const moonPhasesCache = {};

      // 1. 현재 월의 월령 정보를 API로부터 받아옴
      async function fetchMoonPhases(year, month) {
        month = String(month).padStart(2, "0");
        const response = await fetch(`${url}&solYear=${year}&solMonth=${month}`);
        const data = await response.json();
        return data.response.body.items.item; // 각 날짜에 대한 월령 정보를 반환
      }

      // 2. 초기 날짜와 월령 데이터 가져오기
      const today = new Date();
      const initialYear = today.getFullYear();
      const initialMonth = today.getMonth() + 1;
      //const moonData = await fetchMoonPhases(initialYear, initialMonth);
      //moonPhasesCache[`${initialYear}-${initialMonth}`] = moonData;

      // 3. 달력 설정 및 렌더링
      var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ko',
        datesSet: async function(info) {
          const year = info.view.currentStart.getFullYear();
          const month = info.view.currentStart.getMonth() + 1;

          // 캐시에 해당 월의 데이터가 없으면 API 호출
          if (!moonPhasesCache[`${year}-${month}`]) {
            const moonData = await fetchMoonPhases(year, month);
            moonPhasesCache[`${year}-${month}`] = moonData;
            
            calendar.destroy();
            calendar.render();
          }
        },
        dayCellDidMount: function(arg) {
          const date = arg.date;
          const year = date.getFullYear();
          const month = date.getMonth() + 1;
          const day = date.getDate();

          if (moonPhasesCache[`${year}-${month}`]) {
            const moonPhases = moonPhasesCache[`${year}-${month}`];
            const moonPhase = moonPhases.find(item => parseInt(item.solDay) === day);

            if (moonPhase) {
              const lunAge = Math.round(moonPhase.lunAge);
              const moonPhaseImageUrl = `/assets/images/moon/moon_${lunAge}.gif`;

              // 이미지 추가
              const img = document.createElement('img');
              img.src = moonPhaseImageUrl;
              arg.el.appendChild(img);
            }
          }
        }
      });

      calendar.render();
    });