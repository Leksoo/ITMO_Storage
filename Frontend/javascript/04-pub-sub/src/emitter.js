'use strict';

/**
 * Сделано дополнительное задание: реализованы методы several и through.
 */
const isExtraTaskSolved = true;

/**
 * Получение нового Emitter'а
 * @returns {Object}
 */
function getEmitter() {
  const eventActions = {};

  return {
    /**
     * Подписка на событие
     * @param {string} event
     * @param {Object} context
     * @param {Function} handler
     */
    on: function(event, context, handler, times = -1, frequency = 1) {
      if (!(event in eventActions)) {
        eventActions[event] = [];
      }

      eventActions[event].push({ context, handler, times, frequency, counter: 0 });

      return this;
    },

    /**
     * Отписка от события
     * @param {string} event
     * @param {Object} context
     */
    off: function(event, context) {
      for (const ev of Object.keys(eventActions)) {
        if (event === ev || ev.startsWith(`${event}.`)) {
          eventActions[ev] = eventActions[ev].filter(action => action.context !== context);
        }
      }

      return this;
    },

    /**
     * Уведомление о событии
     * @param {string} event
     */
    emit: function(event) {
      let eventCopy = event;
      let sliceIndex = 0;

      while (sliceIndex !== -1) {
        if (eventCopy in eventActions) {
          const actions = eventActions[eventCopy];

          actions.forEach(action => {
            if (Number.isInteger(action.counter / action.frequency)) {
              if (action.counter < action.times || action.times === -1) {
                action.handler.call(action.context);
              }
            }

            action.counter++;
          });
        }

        sliceIndex = eventCopy.lastIndexOf('.');
        eventCopy = eventCopy.substr(0, sliceIndex);
      }

      return this;
    },

    /**
     * Подписка на событие с ограничением по количеству отправляемых уведомлений
     * @param {string} event
     * @param {Object} context
     * @param {Function} handler
     * @param {number} times Сколько раз отправить уведомление
     */
    several: function(event, context, handler, times) {
      this.on(event, context, handler, times > 0 ? times : -1);

      return this;
    },

    /**
     * Подписка на событие с ограничением по частоте отправки уведомлений
     * @param {string} event
     * @param {Object} context
     * @param {Function} handler
     * @param {number} frequency Как часто уведомлять
     */
    through: function(event, context, handler, frequency) {
      this.on(event, context, handler, -1, frequency > 0 ? frequency : 1);

      return this;
    }
  };
}

module.exports = {
  getEmitter,

  isExtraTaskSolved
};
