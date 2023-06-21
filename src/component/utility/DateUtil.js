import moment from 'moment';

export function returnDateToYYYYMMDD24HHMISS(timestamp) {
  return moment(timestamp).format('YYYY/MM/DD HH:mm:ss');
}

export function returnDateToYYYYMMDD(inputDate) {
  return moment(inputDate).format('YYYY-MM-DD');
}

export function returnDateToMMDD(inputDate) {
  return moment(inputDate).format('MM월 DD일');
}

export function returnCurrDateYYYYMMDD(inputDate) {
  return moment(inputDate || new Date()).format('YYYY-MM-DD');
}

export function compareWithCurrentDate(realBirthdate) {
  const birthDate = moment(realBirthdate);
  const currentDate = moment();

  if (currentDate.month() !== birthDate.month()) return false;

  if (currentDate.date() > birthDate.date()) return -1;
  if (currentDate.date() === birthDate.date()) return 0;
  if (currentDate.date() < birthDate.date()) return 1;

  return false;
}

export function returnMMDD(inputDate) {
  return moment(inputDate || new Date()).format('MM월 DD일');
}

export const returnCurrDateYYYYMMDDV2 = (dateStr) => {
  const now = moment(); // Current date
  const inputDate = moment(dateStr); // Your input date

  // Compare days between two dates
  const diffDays = now.diff(inputDate, 'days');

  switch (diffDays) {
    case 0: // If the difference is 0, the date is today. Return time only.
      return inputDate.format('HH:mm');
    case 1: // If the difference is 1, the date was yesterday. Return 'yesterday'.
      return 'Yesterday';
    default: // For any other date, return in format yyyy-mm-dd.
      return inputDate.format('YYYY-MM-DD');
  }
};
