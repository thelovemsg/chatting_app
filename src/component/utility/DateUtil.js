/**
 * 입력한 timestamp를 YYYYMMDD 24HHMISS형식으로 반환
 * @param {*} timestamp
 * @returns
 */
export function returnDateToYYYYMMDD24HHMISS(timestamp) {
  // convert the timestamp to a Date object
  const date = new Date(timestamp);

  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDay();
  const hours = date.getHours();
  const minutes = date.getMinutes();
  const seconds = date.getSeconds();

  // format the time as a string (adjust as needed)
  const formattedTime = `${year}/${month}/${day} ${hours}:${minutes}:${seconds}`;

  return formattedTime;
}

/**
 * 입력한 inputDate를 YYYY-MM-DD형식으로 반환
 * @param {*} timestamp
 * @returns
 */
export function returnDateToYYYYMMDD(inputDate) {
  const date = new Date(inputDate);

  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDay();

  const formattedTime = `${year}-${month >= 10 ? month : `0${month}`}-${
    day >= 10 ? day : `0${day}`
  }`;

  return formattedTime;
}

/**
 * 입력한 inputDate를 MM월 DD일형식으로 반환
 * @param {*} inputDate
 * @param {*} type
 * @returns
 */
export function returnDateToMMDD(inputDate) {
  const date = new Date(inputDate);

  const month = date.getMonth() + 1;
  const day = date.getDay();

  const formattedTime = `${month >= 10 ? month : `0${month}`}월 ${
    day >= 10 ? day : `0${day}일`
  }`;

  return formattedTime;
}

/**
 * 현재 일자를 YYYYMMDD형식으로 반환
 * @param {*} timestamp
 * @returns
 */
export function returnCurrDateYYYYMMDD() {
  const date = new Date();

  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDay();

  const formattedTime = `${year}-${month >= 10 ? month : `0${month}`}-${
    day >= 10 ? day : `0${day}`
  }`;
  return formattedTime;
}

/**
 * Compare the birthdate with the current date
 * @param {string} realBirthdate
 * @returns {string} Returns 'same' if the dates are the same, 'before' if the birthdate is before the current date, and 'after' if the birthdate is after the current date
 */
export function compareWithCurrentDate(realBirthdate) {
  const birthDate = new Date(realBirthdate);
  const currentDate = new Date();

  const currentMonth = currentDate.getMonth();
  const currentDay = currentDate.getDate();
  const birthMonth = birthDate.getMonth();
  const birthDay = birthDate.getDate();

  if (currentMonth === birthMonth && currentDay === birthDay) {
    return 0;
  }
  if (currentMonth === birthMonth && currentDay < birthDay) {
    return -1;
  }
  return 1;
}
