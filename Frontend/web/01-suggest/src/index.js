const URL_AIRPORT_DATA_REQUEST =
  'https://cometari-airportsfinder-v1.p.rapidapi.com/api/airports/by-text';
const API_HOST_NAME = 'x-rapidapi-host';
const API_HOST_VALUE = 'cometari-airportsfinder-v1.p.rapidapi.com';
const API_KEY_NAME = 'x-rapidapi-key';
const API_KEY_VALUE = '969eac250dmsh614e5b68e1afd70p1f08afjsn9c189e783d3d';
const REQUEST_HEADER = {
  [API_HOST_NAME]: API_HOST_VALUE,
  [API_KEY_NAME]: API_KEY_VALUE
};
const REQUEST_DELAY_MS = 500;

let abortController = new AbortController();

const requestAirportsThrottled = throttle(requestAirports, REQUEST_DELAY_MS);

function onInputChange(input) {
  abortController.abort();
  abortController = new AbortController();

  if (input.value !== '') {
    requestAirportsThrottled(input.value);
  } else {
    removeSuggests();
  }
}

function createSuggest(city, code) {
  const elem = document.createElement('option');

  elem.value = `${city} · ${code}`;
  document.getElementById('airports-list').appendChild(elem);
}

function removeSuggests() {
  const datalist = document.getElementById('airports-list');

  datalist.innerHTML = '';
}

async function requestAirports(query) {
  removeSuggests();

  try {
    const response = await fetch(URL_AIRPORT_DATA_REQUEST + `?text=${query}`, {
      signal: abortController.signal,
      method: 'GET',
      headers: REQUEST_HEADER
    });

    if (response.ok) {
      const json = await response.json();

      handleAirportData(json);
    } else {
      alert('load airports data failed');
    }
  } catch (e) {
    if (e.name !== 'AbortError') {
      throw e;
    }
  }
}

function handleAirportData(data) {
  let count = 0;

  for (const airport of data) {
    if (count === 5) {
      break;
    }

    createSuggest(airport.city, airport.code);
    count++;
  }
}

function throttle(func, ms) {
  let isThrottled = false;
  let savedArgs;
  let savedThis;

  function wrapper() {
    if (isThrottled) {
      savedArgs = arguments;
      savedThis = this;

      return;
    }

    func.apply(this, arguments);

    isThrottled = true;

    setTimeout(function() {
      isThrottled = false;

      if (savedArgs) {
        wrapper.apply(savedThis, savedArgs);
        savedArgs = null;
        savedThis = null;
      }
    }, ms);
  }

  return wrapper;
}
