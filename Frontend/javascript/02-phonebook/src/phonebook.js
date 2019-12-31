'use strict';

/**
 * Если вы решили сделать дополнительное задание и реализовали функцию importFromDsv,
 * то выставьте значение переменной isExtraTaskSolved в true.
 */
const isExtraTaskSolved = true;

/**
 * Телефонная книга
 */
const phoneBook = {};

/**
 * Добавление записи в телефонную книгу
 * @param {string} phone
 * @param {string} [name]
 * @param {string} [email]
 * @returns {boolean}
 */
function add(phone, name, email) {
  if (!isPhoneAndNameValid(phone, name)) {
    return false;
  }

  const validEmail = validateEmail(email);

  if (validEmail === 'undefined') {
    return false;
  }
  if (phoneBook[phone]) {
    return false;
  }

  phoneBook[phone] = [name, validEmail];

  return true;
}

/**
 * Обновление записи в телефонной книге
 * @param {string} phone
 * @param {string} [name]
 * @param {string} [email]
 * @returns {boolean}
 */
function update(phone, name, email) {
  if (!isPhoneAndNameValid(phone, name)) {
    return false;
  }

  const validEmail = validateEmail(email);

  if (validEmail === 'undefined') {
    return false;
  }
  if (!phoneBook[phone]) {
    return false;
  }

  phoneBook[phone] = [name, validEmail];

  return true;
}

/**
 * Поиск записей по запросу в телефонной книге
 * @param {string} query
 * @returns {string[]}
 */
function find(query) {
  if (!isQueryValid(query)) {
    return [];
  }

  const pickedContacts = getContacts(query);
  const res = [];

  for (const contact of pickedContacts) {
    const phoneParts = contact[0].match(/^(\d{3})(\d{3})(\d{2})(\d{2})$/);
    const phone = `+7 (${phoneParts[1]}) ${phoneParts[2]}-${phoneParts[3]}-${phoneParts[4]}`;
    let contactResult = `${contact[1]}, ${phone}`;
    if (contact[2] !== '') {
      contactResult += `, ${contact[2]}`;
    }
    res.push(contactResult);
  }

  res.sort();

  return res;
}

/**
 * Удаление записей по запросу из телефонной книги
 * @param {string} query
 * @returns {number}
 */
function findAndRemove(query) {
  if (!isQueryValid(query)) {
    return 0;
  }

  let removed = 0;
  const pickedContacts = getContacts(query);

  for (const contact of pickedContacts) {
    delete phoneBook[contact[0]];
    removed++;
  }

  return removed;
}

function getContacts(query) {
  const pickedContacts = [];

  for (const entry of Object.entries(phoneBook)) {
    const [phone, [name, email]] = entry;

    if (query === '*' || phone.includes(query) || name.includes(query) || email.includes(query)) {
      pickedContacts.push([phone, name, email]);
    }
  }

  return pickedContacts;
}

function isPhoneAndNameValid(phone, name) {
  return (
    typeof phone === 'string' &&
    /^\d{10}$/.test(phone) &&
    typeof name === 'string' &&
    !/^$/.test(name)
  );
}

function validateEmail(email) {
  if (typeof email === 'string') {
    return email;
  } else if (typeof email === 'undefined') {
    return '';
  }

  return undefined;
}

function isQueryValid(query) {
  return typeof query === 'string' && !/^$/.test(query);
}

/**
 * Импорт записей из dsv-формата
 * @param {string} dsv
 * @returns {number} Количество добавленных и обновленных записей
 */
function importFromDsv(dsv) {
  if (typeof dsv !== 'string') {
    return 0;
  }

  let added = 0;

  dsv.split('\n').forEach(line => {
    const [name, phone, email] = line.split(';');

    if (add(phone, name, email) || update(phone, name, email)) {
      added++;
    }
  });

  return added;
}

module.exports = {
  add,
  update,
  find,
  findAndRemove,
  importFromDsv,

  isExtraTaskSolved
};
