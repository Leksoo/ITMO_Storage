'use strict';

global.fetch = require('node-fetch');

const getWeather = geoid => {
  return global
    .fetch(`https://api.weather.yandex.ru/v1/forecast?hours=false&limit=7&geoid=${geoid}`, {
      method: 'get'
    })
    .then(response => response.json())
    .catch(error => {
      throw new Error('API Response error');
    })
    .then(response =>
      response['forecasts']
        .map(forecast => forecast['parts']['day_short']['condition'])
        .map(condition => {
          if (
            condition === 'clear' ||
            condition === 'partly-cloud' ||
            condition === 'partly-cloudy'
          ) {
            return 'sunny';
          } else if (condition === 'cloudy' || condition === 'overcast') {
            return 'cloudy';
          }

          return 'other';
        })
    );
};

const pathNotFoundError = new Error('Не могу построить маршрут!');
/**
 * @typedef {object} TripItem Город, который является частью маршрута.
 * @property {number} geoid Идентификатор города
 * @property {number} day Порядковое число дня маршрута
 */

class TripBuilder {
  constructor(geoids) {
    this.geoids = geoids;
    this.dayConditions = [];
    this.maxDaysInCity = 7;
  }

  /**
   * Метод, добавляющий условие наличия в маршруте
   * указанного количества солнечных дней
   * Согласно API Яндекс.Погоды, к солнечным дням
   * можно приравнять следующие значения `condition`:
   * * `clear`;
   * * `partly-cloudy`.
   * @param {number} daysCount количество дней
   * @returns {object} Объект планировщика маршрута
   */
  sunny(daysCount) {
    for (let i = 0; i < daysCount; i++) {
      this.dayConditions.push('sunny');
    }

    return this;
  }

  /**
   * Метод, добавляющий условие наличия в маршруте
   * указанного количества пасмурных дней
   * Согласно API Яндекс.Погоды, к солнечным дням
   * можно приравнять следующие значения `condition`:
   * * `cloudy`;
   * * `overcast`.
   * @param {number} daysCount количество дней
   * @returns {object} Объект планировщика маршрута
   */
  cloudy(daysCount) {
    for (let i = 0; i < daysCount; i++) {
      this.dayConditions.push('cloudy');
    }

    return this;
  }

  /**
   * Метод, добавляющий условие максимального количества дней.
   * @param {number} daysCount количество дней
   * @returns {object} Объект планировщика маршрута
   */
  max(daysCount) {
    this.maxDaysInCity = daysCount;

    return this;
  }

  /**
   * Метод, возвращающий Promise с планируемым маршрутом.
   * @returns {Promise<TripItem[]>} Список городов маршрута
   */
  build() {
    return Promise.all(this.geoids.map(id => getWeather(id))).then(ids => {
      if (this.dayConditions.length === 0) {
        return [];
      }
      const suitableCities = [];
      for (let day = 0; day < this.dayConditions.length; day++) {
        suitableCities[day] = [];
        for (let city = 0; city < this.geoids.length; city++) {
          if (this.dayConditions[day] === 'sunny' && ids[city][day] === 'sunny') {
            suitableCities[day].push(this.geoids[city]);
          } else if (this.dayConditions[day] === 'cloudy' && ids[city][day] === 'cloudy') {
            suitableCities[day].push(this.geoids[city]);
          }
        }
      }

      if (suitableCities.some(it => it.length === 0)) {
        throw pathNotFoundError;
      }
      let chosenPath;
      let daysSpentInCity = 0;
      const maxDaysInCity = this.maxDaysInCity;

      const findPath = function(cities, day, path) {
        if (cities.length > day) {
          for (let i = 0; i < cities[day].length; i++) {
            const curPath = path.slice();
            curPath.push(cities[day][i]);

            findPath(cities, day + 1, curPath);
          }
        } else {
          const used = new Set();
          used.add(path[0]);
          const daysInCity = {};
          daysInCity[path[0]] = 1;
          for (let i = 1; i < path.length; i++) {
            if (path[i] !== path[i - 1] && used.has(path[i])) {
              return;
            }
            if (!daysInCity[path[i]]) {
              daysInCity[path[i]] = 0;
            }
            daysInCity[path[i]]++;
            used.add(path[i]);
          }
          const maxDays = Math.max.apply(null, Object.values(daysInCity));
          if (maxDays > maxDaysInCity) {
            return;
          }
          if (maxDays > daysSpentInCity) {
            daysSpentInCity = maxDays;
            chosenPath = path;
          }
        }
      };

      findPath(suitableCities, 0, []);

      if (!chosenPath) {
        throw pathNotFoundError;
      }

      return chosenPath.map((city, ind) => {
        return { geoid: city, day: ind + 1 };
      });
    });
  }
}

/**
 * Фабрика для получения планировщика маршрута.
 * Принимает на вход список идентификаторов городов, а
 * возвращает планировщик маршрута по данным городам.
 *
 * @param {number[]} geoids Список идентификаторов городов
 * @returns {TripBuilder} Объект планировщика маршрута
 * @see https://yandex.ru/dev/xml/doc/dg/reference/regions-docpage/
 */
function planTrip(geoids) {
  return new TripBuilder(geoids);
}

module.exports = {
  planTrip,
  getWeather
};
