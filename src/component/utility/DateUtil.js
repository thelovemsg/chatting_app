export default function returnCurrentDate(timestamp) {
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
