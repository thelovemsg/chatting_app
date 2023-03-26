export const isValidEmail = (email) => {
  const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
  return emailRegex.test(email);
};

export const isValidPhoneNumber = (phoneNumber) => {
  const phoneNumeberRegex = /(01[016789])-([1-9]{1}[0-9]{2,3})-([0-9]{4})$/;
  return phoneNumeberRegex.test(phoneNumber);
};

export const isValidPassword = (password) => {
  const passwordRegex =
    /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
  return passwordRegex.test(password);
};
