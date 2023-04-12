// useRegister.js
import { useState, useCallback } from 'react';
import { useTranslation } from 'react-i18next';
import {
  isValidEmail,
  isValidPassword,
  isValidPhoneNumber,
} from '../function/ValidationUtils';
import api from '../function/ApiUtils';

const useRegister = () => {
  const { t } = useTranslation();

  const [enrollCompany, setEnrollCompany] = useState({
    address: '',
  });

  const [validationStatus, setValidationStatus] = useState({
    emailCheck: false,
    nicknameCheck: false,
    phoneNumberCheck: false,
  });

  const [phoneNumber, setPhoneNumber] = useState('');
  const [emailValidationMsg, setEmailValidationMsg] = useState('');
  const [nicknameValidationMsg, setNicknameValidationMsg] = useState('');
  const [phoneNumberValidationMsg, setPhoneNumberValidationMsg] = useState('');
  const [passwordRegexMsg, setPasswordRegexMsg] = useState('');
  const [passwordMismatchMsg, setPasswordMismatchMsg] = useState('');

  const handlePhoneNumberFormat = useCallback((e) => {
    const { value } = e.target;
    if (value.length === 10) {
      setPhoneNumber(value.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3'));
    } else if (value.length === 13) {
      setPhoneNumber(
        value.replace(/-/g, '').replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3')
      );
    } else {
      if (!isValidPhoneNumber(value)) {
        setPhoneNumberValidationMsg(`${t('register.wrongPhoneNumberFormat')}`);
      } else {
        setPhoneNumberValidationMsg('');
      }
      setPhoneNumber(value);
    }
  }, []);

  const handlePasswordRegex = useCallback((e) => {
    const password = e.target.value;
    if (!isValidPassword(password)) {
      setPasswordRegexMsg(`${t('register.passwordRegexCheck')}`);
    } else {
      setPasswordRegexMsg('');
    }
  }, []);

  const handlePasswordValidation = useCallback((e) => {
    const password = e.target.form.password.value;
    const passwordCheck = e.target.form.passwordCheck.value;
    if (password !== passwordCheck) {
      setPasswordMismatchMsg(`${t('register.passwordMismatch')}`);
    } else {
      setPasswordMismatchMsg();
    }
  }, []);

  const handleValidation = async (name, value, validator, errorMsgKey) => {
    if (!validator(value)) {
      setValidationStatus({ ...validationStatus, [name]: false });
      return t(`register.${errorMsgKey}`);
    }
    const response = await api.findMemberByTarget(name, value);
    if (response === name) {
      setValidationStatus({ ...validationStatus, [name]: false });
      return `${response} ${t('register.alreadyExists')}`;
    }
    setValidationStatus({ ...validationStatus, [name]: true });
    return '';
  };

  const handleEmailValidation = async (value) => {
    const errorMsg = await handleValidation(
      'email',
      value,
      isValidEmail,
      'checkEmail'
    );
    setEmailValidationMsg(errorMsg);
  };

  const handlePhoneNumberValidation = async (value) => {
    const errorMsg = await handleValidation(
      'phoneNumber',
      value,
      isValidPhoneNumber,
      'wrongPhoneNumberFormat'
    );
    setPhoneNumberValidationMsg(errorMsg);
  };

  const handleNicknameValidation = async (value) => {
    const errorMsg = await handleValidation(
      'nickname',
      value,
      () => value.length > 0,
      'nicknameEmpty'
    );
    setNicknameValidationMsg(errorMsg);
  };

  const handleValidationBlur = useCallback(
    async (e) => {
      const { name } = e.target;
      const { value } = e.target;
      if (name === 'email') {
        handleEmailValidation(value);
      } else if (name === 'phoneNumber') {
        handlePhoneNumberValidation(value);
      } else if (name === 'nickname') {
        handleNicknameValidation(value);
      } else if (name === 'password') {
        handlePasswordValidation(e);
      }
    },
    [validationStatus]
  );

  return {
    enrollCompany,
    setEnrollCompany,
    validationStatus,
    phoneNumber,
    emailValidationMsg,
    passwordMismatchMsg,
    nicknameValidationMsg,
    phoneNumberValidationMsg,
    passwordRegexMsg,
    handlePasswordRegex,
    handlePasswordValidation,
    handlePhoneNumberFormat,
    handleValidationBlur,
  };
};

export default useRegister;
