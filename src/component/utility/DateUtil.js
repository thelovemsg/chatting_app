/**
 * 입력한 timestamp를 YYYY/MM/DD 24HHMISS형식으로 반환
 * @param {*} timestamp
 * @returns string
 */
export function returnDateToYYYYMMDD24HHMISS(timestamp) {
  const date = new Date(timestamp);
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const hours = date.getHours();
  const minutes = date.getMinutes();
  const seconds = date.getSeconds();
  const formattedTime = `${year}/${month < 10 ? `0${month}` : month}/${
    day < 10 ? `0${day}` : day
  } ${hours}:${minutes}:${seconds}`;
  return formattedTime;
}

/**
 * 입력한 inputDate를 YYYY-MM-DD형식으로 반환
 * @param {*} inputDate
 * @returns string
 */
export function returnDateToYYYYMMDD(inputDate) {
  const date = new Date(inputDate);
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const formattedTime = `${year}-${month < 10 ? `0${month}` : month}-${
    day < 10 ? `0${day}` : day
  }`;
  return formattedTime;
}

/**
 * 입력한 inputDate를 MM월 DD일형식으로 반환
 * @param {*} inputDate
 * @returns string
 */
export function returnDateToMMDD(inputDate) {
  const date = new Date(inputDate);
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const formattedTime = `${month < 10 ? `0${month}` : month}월 ${
    day < 10 ? `0${day}` : day
  }일`;
  return formattedTime;
}

/**
 * 현재 일자 혹은 입력한 날짜를 YYYY-MM-DD형식으로 반환
 * @param {*} inputDate
 * @returns string
 */
export function returnCurrDateYYYYMMDD(inputDate) {
  const date = inputDate ? new Date(inputDate) : new Date();
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const formattedTime = `${year}-${month < 10 ? `0${month}` : month}-${
    day < 10 ? `0${day}` : day
  }`;
  return formattedTime;
}

/**
 * 입력한 birthdate와 현재 날짜를 비교하여 이전/동일/이후를 판별
 * @param {string} realBirthdate
 * @returns number -1 if birthdate is before the current date, 0 if same, 1 if after
 */
export function compareWithCurrentDate(realBirthdate) {
  const birthDate = new Date(realBirthdate);
  const currentDate = new Date();
  const currentMonth = currentDate.getMonth();
  const currentDay = currentDate.getDate();
  const birthMonth = birthDate.getMonth();
  const birthDay = birthDate.getDate();
  if (currentMonth !== birthMonth) return false;
  if (currentDay > birthDay) return -1;
  if (currentDay === birthDay) return 0;
  if (currentDay < birthDay) return 1;
  return false;
}

/**
 * 입력한 날짜 또는 현재 날짜를 MM월 DD일 형식으로 반환
 * @param {string} inputDate
 * @returns string
 */
export function returnMMDD(inputDate) {
  const targetDate = inputDate ? new Date(inputDate) : new Date();
  const month = targetDate.getMonth() + 1;
  const day = targetDate.getDate();
  return `${month < 10 ? `0${month}` : month}월 ${
    day < 10 ? `0${day}` : day
  }일`;
}
