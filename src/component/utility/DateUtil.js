export function returnCurrentDate(timestamp) {
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
 * 입력한 년월일과 현재 날짜와 차이나는 일수를 반환
 * @param {*} timestamp
 * @returns 입력 timestamp와 입력 날짜를 계산한 일수 / 입력일의 년월일(YYYMMDD) 반환
 */
export function returnDateYYYYMMDD(timestamp, targetDate) {
  const date = new Date(timestamp);

  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDay();

  // format the time as a string (adjust as needed)
  const formattedTime = `${year}년 ${month}월 ${day}일`;

  // calculate difference in days
  const oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
  const diffDays = Math.round(Math.abs((date - targetDate) / oneDay));

  return {
    YYYYMMDD: formattedTime,
    difference: diffDays,
  };
}
