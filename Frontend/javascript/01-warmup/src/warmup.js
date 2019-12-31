'use strict';

/**
 * Складывает два целых числа
 * @param {Number} a Первое целое
 * @param {Number} b Второе целое
 * @throws {TypeError} Когда в аргументы переданы не числа
 * @returns {Number} Сумма аргументов
 */
function abProblem(a, b) {
  if (!(typeof a === 'number') || !(typeof b === 'number')) {
    throw new TypeError();
  }

  return a + b;
}

/**
 * Определяет век по году
 * @param {Number} year Год, целое положительное число
 * @throws {TypeError} Когда в качестве года передано не число
 * @throws {RangeError} Когда год – отрицательное значение
 * @returns {Number} Век, полученный из года
 */
function centuryByYearProblem(year) {
  if (!(typeof year === 'number')) {
    throw new TypeError();
  }
  if (!Number.isInteger(year) || year <= 0) {
    throw new RangeError();
  }

  return Math.ceil(year / 100);
}

/**
 * Переводит цвет из формата HEX в формат RGB
 * @param {String} hexColor Цвет в формате HEX, например, '#FFFFFF'
 * @throws {TypeError} Когда цвет передан не строкой
 * @throws {RangeError} Когда значения цвета выходят за пределы допустимых
 * @returns {String} Цвет в формате RGB, например, '(255, 255, 255)'
 */
function colorsProblem(hexColor) {
  if (typeof hexColor !== 'string') {
    throw new TypeError();
  }
  if (!new RegExp('^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$').test(hexColor)) {
    throw new RangeError();
  }

  const res = [];

  if (hexColor.length === 4) {
    for (let i = 1; i < 4; i++) {
      res.push(parseInt('0x' + hexColor[i] + hexColor[i], 16));
    }
  } else {
    for (let i = 1; i < 7; i += 2) {
      res.push(parseInt('0x' + hexColor[i] + hexColor[i + 1], 16));
    }
  }

  return `(${res[0]}, ${res[1]}, ${res[2]})`;
}

/**
 * Находит n-ое число Фибоначчи
 * @param {Number} n Положение числа в ряде Фибоначчи
 * @throws {TypeError} Когда в качестве положения в ряде передано не число
 * @throws {RangeError} Когда положение в ряде не является целым положительным числом
 * @returns {Number} Число Фибоначчи, находящееся на n-ой позиции
 */
function fibonacciProblem(n) {
  if (typeof n !== 'number') {
    throw new TypeError();
  }
  if (!Number.isInteger(n) || n <= 0) {
    throw new RangeError();
  }
  if (n === 1 || n === 2) {
    return 1;
  }

  let fib1 = 1;
  let fib2 = 1;

  for (let i = 0; i < n - 2; i++) {
    const fib3 = fib1 + fib2;
    fib1 = fib2;
    fib2 = fib3;
  }

  return fib2;
}

/**
 * Транспонирует матрицу
 * @param {(Any[])[]} matrix Матрица размерности MxN
 * @throws {TypeError} Когда в функцию передаётся не двумерный массив
 * @returns {(Any[])[]} Транспонированная матрица размера NxM
 */
function matrixProblem(matrix) {
  if (!Array.isArray(matrix)) {
    throw new TypeError();
  }
  if (!matrix.every(it => Array.isArray(it))) {
    throw new TypeError();
  }

  const w = matrix.length;
  const h = matrix.length > 0 ? matrix[0].length : 0;

  if (!matrix.every(it => it.length === h)) {
    throw new TypeError();
  }

  const res = [];

  for (let i = 0; i < h; i++) {
    res[i] = [];

    for (let j = 0; j < w; j++) {
      res[i][j] = matrix[j][i];
    }
  }

  return res;
}

/**
 * Переводит число в другую систему счисления
 * @param {Number} n Число для перевода в другую систему счисления
 * @param {Number} targetNs Система счисления, в которую нужно перевести (Число от 2 до 36)
 * @throws {TypeError} Когда переданы аргументы некорректного типа
 * @throws {RangeError} Когда система счисления выходит за пределы значений [2, 36]
 * @returns {String} Число n в системе счисления targetNs
 */
function numberSystemProblem(n, targetNs) {
  if (typeof n !== 'number' || typeof targetNs !== 'number') {
    throw new TypeError();
  }
  if (!Number.isInteger(targetNs) || !(targetNs >= 2 && targetNs <= 36)) {
    throw new RangeError();
  }

  return n.toString(targetNs);
}

/**
 * Проверяет соответствие телефонного номера формату
 * @param {String} phoneNumber Номер телефона в формате '8–800–xxx–xx–xx'
 * @returns {Boolean} Если соответствует формату, то true, а иначе false
 */
function phoneProblem(phoneNumber) {
  if (typeof phoneNumber !== 'string') {
    throw new TypeError();
  }

  return new RegExp('^8-800-\\d\\d\\d-\\d\\d-\\d\\d$').test(phoneNumber);
}

/**
 * Определяет количество улыбающихся смайликов в строке
 * @param {String} text Строка в которой производится поиск
 * @throws {TypeError} Когда в качестве аргумента передаётся не строка
 * @returns {Number} Количество улыбающихся смайликов в строке
 */
function smilesProblem(text) {
  if (typeof text !== 'string') {
    throw new TypeError();
  }

  const res = text.match(/\(-:|:-\)/g);

  return res === null ? 0 : res.length;
}

/**
 * Определяет победителя в игре "Крестики-нолики"
 * Тестами гарантируются корректные аргументы.
 * @param {(('x' | 'o')[])[]} field Игровое поле 3x3 завершённой игры
 * @returns {'x' | 'o' | 'draw'} Результат игры
 */
function ticTacToeProblem(field) {
  const X = 'x';
  const O = 'o';
  let winO = false;
  let winX = false;

  for (let i = 0; i < 3; i++) {
    winO |= checkWinnerRow(field, i, O);
    winX |= checkWinnerRow(field, i, X);
    winO |= checkWinnerColumn(field, i, O);
    winX |= checkWinnerColumn(field, i, X);
  }

  winO |= checkWinnerDiag(field, O);
  winX |= checkWinnerDiag(field, X);

  if (winX && !winO) {
    return X;
  } else if (winO && !winX) {
    return O;
  } else {
    return 'draw';
  }
}

function checkWinnerRow(field, i, letter) {
  return field[i][0] === letter && field[i][1] === letter && field[i][2] === letter;
}

function checkWinnerColumn(field, i, letter) {
  return field[0][i] === letter && field[1][i] === letter && field[2][i] === letter;
}

function checkWinnerDiag(field, letter) {
  return (
    (field[0][0] === letter && field[1][1] === letter && field[2][2] === letter) ||
    (field[0][2] === letter && field[1][1] === letter && field[2][0] === letter)
  );
}

module.exports = {
  abProblem,
  centuryByYearProblem,
  colorsProblem,
  fibonacciProblem,
  matrixProblem,
  numberSystemProblem,
  phoneProblem,
  smilesProblem,
  ticTacToeProblem
};
