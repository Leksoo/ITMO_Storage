'use strict';

/**
 * Флаг решения дополнительной задачи
 * @see README.md
 */
const isExtraTaskSolved = true;

/**
 * @param {Object} schedule Расписание Банды
 * @param {number} duration Время на ограбление в минутах
 * @param {Object} workingHours Время работы банка
 * @param {string} workingHours.from Время открытия, например, "10:00+5"
 * @param {string} workingHours.to Время закрытия, например, "18:00+5"
 * @returns {Object}
 */
function getAppropriateMoment(schedule, duration, workingHours) {
  const MINUTES_IN_HOUR = 60;
  const HOURS_IN_DAY = 24;
  const MINUTES_IN_DAY = MINUTES_IN_HOUR * HOURS_IN_DAY;
  const DAYS = ['ПН', 'ВТ', 'СР'];
  const bankTimeZone = parseInt(workingHours.from.slice(workingHours.from.indexOf('+') + 1));

  function parseDate(date) {
    let [timeZone, minutes, hours, day] = date.split(/[ :+]/).reverse();

    if (!day) {
      // bank date passed, start from 0 day
      day = 'ПН';
    }

    day = DAYS.indexOf(day);
    hours = parseInt(hours);
    minutes = parseInt(minutes);
    timeZone = parseInt(timeZone);
    hours -= timeZone - bankTimeZone;

    return { day, hours, minutes };
  }

  function formatDate(date) {
    date.hours += Math.floor(date.minutes / MINUTES_IN_HOUR);
    date.minutes = date.minutes % MINUTES_IN_HOUR;
    date.day += Math.floor(date.hours / HOURS_IN_DAY);
    date.hours = date.hours % HOURS_IN_DAY;

    return date;
  }

  function toMinutes(date) {
    return date.day * MINUTES_IN_DAY + date.hours * MINUTES_IN_HOUR + date.minutes;
  }

  function addBankClosedTime(busyTimes) {
    const from = toMinutes(formatDate(parseDate(workingHours.from)));
    const to = toMinutes(formatDate(parseDate(workingHours.to)));

    for (let i = 0; i < 3; i++) {
      busyTimes.push({ from: MINUTES_IN_DAY * i, to: from + MINUTES_IN_DAY * i });
      busyTimes.push({ from: to + MINUTES_IN_DAY * i, to: MINUTES_IN_DAY + MINUTES_IN_DAY * i });
    }
  }

  function addGuysBusyTime(busyTimes) {
    Object.values(schedule).forEach(times => {
      times.forEach(time => {
        busyTimes.push({
          from: toMinutes(formatDate(parseDate(time.from))),
          to: toMinutes(formatDate(parseDate(time.to)))
        });
      });
    });
  }

  function findTimeToRob() {
    let result;
    let current;
    let busyTimes = [];
    addBankClosedTime(busyTimes);
    addGuysBusyTime(busyTimes);

    busyTimes = busyTimes.sort((time1, time2) => {
      return time1.from - time2.from;
    });

    for (let i = 0; i < busyTimes.length - 1; i++) {
      const time1 = busyTimes[i];
      const time2 = busyTimes[i + 1];
      let delay = 0;

      // time1.from <= time1.to <= time2.from <= time2.to -> check duration
      // time1.from <= time2.from <= time1.to time2.to -> bad
      while (time2.from - (time1.to + delay) >= duration) {
        const timeFound = formatDate({ day: 0, hours: 0, minutes: time1.to + delay });

        if (current) {
          current.nextTry = timeFound;
          current = timeFound;
        } else {
          result = timeFound;
          current = timeFound;
        }

        delay += 30;
      }

      // time1.from <= time2.from <= time2.to <= time1.to -> update time2
      if (time1.from <= time2.from && time2.to <= time1.to) {
        busyTimes[i + 1] = time1;
      }
    }

    return result;
  }

  let timesAvailable = findTimeToRob();

  return {
    /**
     * Найдено ли время
     * @returns {boolean}
     */
    exists() {
      return timesAvailable !== undefined;
    },

    /**
     * Возвращает отформатированную строку с часами
     * для ограбления во временной зоне банка
     *
     * @param {string} template
     * @returns {string}
     *
     * @example
     * ```js
     * getAppropriateMoment(...).format('Начинаем в %HH:%MM (%DD)') // => Начинаем в 14:59 (СР)
     * ```
     */
    format(template) {
      if (!timesAvailable) {
        return '';
      }

      function formatTime(time) {
        return time < 10 ? `0${time}` : time;
      }

      return template
        .replace('%DD', DAYS[timesAvailable.day])
        .replace('%HH', formatTime(timesAvailable.hours))
        .replace('%MM', formatTime(timesAvailable.minutes));
    },

    /**
     * Попробовать найти часы для ограбления позже [*]
     * @note Не забудь при реализации выставить флаг `isExtraTaskSolved`
     * @returns {boolean}
     */
    tryLater() {
      if (timesAvailable && timesAvailable.nextTry) {
        timesAvailable = timesAvailable.nextTry;

        return true;
      }

      return false;
    }
  };
}

module.exports = {
  getAppropriateMoment,

  isExtraTaskSolved
};
