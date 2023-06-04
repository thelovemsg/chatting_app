const shortenWords = (str, length = 10) => {
  let result = '';
  if (str.length > length) {
    result = `${str.substr(0, length - 2)}...`;
  } else {
    result = str;
  }
  return result;
};

export default shortenWords;
