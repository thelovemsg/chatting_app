// useRegister.js
import { useState, useCallback } from "react";
import { isValidEmail, isValidPhoneNumber } from "../function/ValidationUtils";
import { findMemberByTarget } from "../function/ApiUtils";

const useRegister = () => {
  const [enroll_company, setEnroll_company] = useState({
    address: "",
  });

  const [validationStatus, setValidationStatus] = useState({
    email: false,
    nickname: false,
    phoneNumber: false,
  });

  const [phoneNumber, setPhoneNumber] = useState("");
  const [emailValidationMsg, setEmailValidationMsg] = useState("");
  const [nicknameValidationMsg, setNicknameValidationMsg] = useState("");
  const [phoneNumberValidationMsg, setPhoneNumberValidationMsg] = useState("");

  const handlePhoneNumberFormat = useCallback((e) => {
    const value = e.target.value;
    if (value.length === 10) {
      setPhoneNumber(value.replace(/(\d{3})(\d{3})(\d{4})/, "$1-$2-$3"));
    } else if (value.length === 13) {
      setPhoneNumber(
        value.replace(/-/g, "").replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3")
      );
    } else {
      if (!isValidPhoneNumber(value)) {
        setPhoneNumberValidationMsg("wrong phone number format");
      } else {
        setPhoneNumberValidationMsg("");
      }
      setPhoneNumber(value);
    }
  }, []);

  const handleEmailValidation = async (value) => {
    if (!isValidEmail(value)) {
      setEmailValidationMsg("check email");
      setValidationStatus({ ...validationStatus, email: false });
    } else {
      const response = findMemberByTarget("email", value);
      const result = response?.data;
      if (result === "email") {
        setEmailValidationMsg(`${response.data} already exists`);
        setValidationStatus({ ...validationStatus, email: false });
      } else {
        setEmailValidationMsg("");
        setValidationStatus({ ...validationStatus, email: true });
      }
    }
  };

  const handlePhoneNumberValidation = async (value) => {
    if (!isValidPhoneNumber(value)) {
      setPhoneNumberValidationMsg("wrong phone number format");
      setValidationStatus({ ...validationStatus, phoneNumber: false });
    } else {
      const response = findMemberByTarget("email", value);
      const result = response?.data;
      if (result === "phoneNumber") {
        setPhoneNumberValidationMsg(`${response.data} already exists`);
        setValidationStatus({ ...validationStatus, phoneNumber: false });
      } else {
        setPhoneNumberValidationMsg("");
        setValidationStatus({ ...validationStatus, phoneNumber: true });
      }
    }
  };

  const handleNicknameValidation = async (value) => {
    const response = findMemberByTarget("email", value);
    const result = response?.data;
    if (result === "nickname") {
      setNicknameValidationMsg(`${response.data} already exists`);
      setValidationStatus({ ...validationStatus, nickname: false });
    } else {
      setNicknameValidationMsg("");
      setValidationStatus({ ...validationStatus, nickname: true });
    }
  };

  const handleValidationBlur = useCallback(
    async (e) => {
      const name = e.target.name;
      const value = e.target.value;

      if (name === "email") {
        handleEmailValidation(value);
      } else if (name === "phoneNumber") {
        handlePhoneNumberValidation(value);
      } else if (name === "nickname") {
        handleNicknameValidation(value);
      }
    },
    [validationStatus]
  );

  return {
    enroll_company,
    setEnroll_company,
    validationStatus,
    setValidationStatus,
    phoneNumber,
    setPhoneNumber,
    emailValidationMsg,
    setEmailValidationMsg,
    nicknameValidationMsg,
    setNicknameValidationMsg,
    phoneNumberValidationMsg,
    setPhoneNumberValidationMsg,
    handlePhoneNumberFormat,
    handleEmailValidation,
    handlePhoneNumberValidation,
    handleNicknameValidation,
    handleValidationBlur,
  };
};

export default useRegister;
