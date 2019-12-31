'use strict';

/**
 * @typedef {Object} Friend
 * @property {string} name Имя
 * @property {'male' | 'female'} gender Пол
 * @property {boolean} best Лучший ли друг?
 * @property {string[]} friends Список имён друзей
 */

const compareByName = (f, s) => f.name.localeCompare(s.name);

/**
 * Итератор по друзьям
 * @constructor
 * @param {Friend[]} friends Список друзей
 * @param {Filter} filter Фильтр друзей
 */
function Iterator(friends, filter) {
  if (!(filter instanceof Filter)) {
    throw new TypeError();
  }

  this.friends = friends;
  this.filter = filter;
  this.invitedFriends = this.fillFriends(this.maxLevel);
  this.index = 0;
}

Iterator.prototype.done = function() {
  return this.invitedFriends.length === this.index;
};

Iterator.prototype.next = function() {
  if (this.done()) {
    return null;
  }

  return this.invitedFriends[this.index++];
};

Iterator.prototype.fillFriends = function(maxLevel = Infinity) {
  const res = [];
  const used = new Set();
  let curLevelData = this.friends.filter(friend => friend.best);
  let curLevel = 0;

  while (curLevelData.length !== 0 && curLevel < maxLevel) {
    curLevelData.sort(compareByName).forEach(friend => {
      used.add(friend.name);
      res.push(friend);
    });
    const nextLevelData = new Set();
    curLevelData.forEach(friend => {
      friend.friends.forEach(friendOfFriendName => {
        if (!used.has(friendOfFriendName)) {
          nextLevelData.add(friendOfFriendName);
        }
      });
    });

    curLevelData = this.friends.filter(friend => nextLevelData.has(friend.name));
    curLevel++;
  }

  return res.filter(this.filter.filterFunc);
};

/**
 * Итератор по друзям с ограничением по кругу
 * @extends Iterator
 * @constructor
 * @param {Friend[]} friends Список друзей
 * @param {Filter} filter Фильтр друзей
 * @param {Number} maxLevel Максимальный круг друзей
 */
function LimitedIterator(friends, filter, maxLevel) {
  this.maxLevel = maxLevel;
  Iterator.call(this, friends, filter);
}

LimitedIterator.prototype = Object.create(Iterator.prototype);
LimitedIterator.prototype.constructor = LimitedIterator;

/**
 * Фильтр друзей
 * @constructor
 */
function Filter() {
  this.filterFunc = () => true;
}

/**
 * Фильтр друзей-парней
 * @extends Filter
 * @constructor
 */
function MaleFilter() {
  this.filterFunc = friend => friend.gender === 'male';
}

MaleFilter.prototype = Object.create(Filter.prototype);
MaleFilter.prototype.constructor = MaleFilter;

/**
 * Фильтр друзей-девушек
 * @extends Filter
 * @constructor
 */
function FemaleFilter() {
  this.filterFunc = friend => friend.gender === 'female';
}

FemaleFilter.prototype = Object.create(Filter.prototype);
FemaleFilter.prototype.constructor = FemaleFilter;

module.exports = {
  Iterator,
  LimitedIterator,
  Filter,
  MaleFilter,
  FemaleFilter
};
