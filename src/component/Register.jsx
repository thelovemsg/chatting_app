import { Form } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { useTranslation } from 'react-i18next';
import {
  StyledButton,
  StyledForm,
  StyledGroup,
  StyledLabel,
  StyledMsgLabel,
  StyledValidationCheck,
} from '../styled-components/StyledForm';
import { REGISTER_REQUEST } from '../reducers/user';
import AddressPopup from './address/AddressPopup';
import useRegister from '../hooks/useRegister';

const Register = () => {
  const { t } = useTranslation();
  const {
    enrollCompany,
    setEnrollCompany,
    validationStatus,
    phoneNumber,
    emailValidationMsg,
    nicknameValidationMsg,
    phoneNumberValidationMsg,
    passwordRegexMsg,
    passwordMismatchMsg,
    handlePasswordRegex,
    handlePasswordValidation,
    handlePhoneNumberFormat,
    handleValidationBlur,
  } = useRegister();

  const dispatch = useDispatch();
  const { registerError } = useSelector((state) => state.user);
  const handleSubmit = (e) => {
    e.preventDefault();

    const { emailCheck, nicknameCheck, phoneNumberCheck } = validationStatus;

    if (!emailCheck || !nicknameCheck || !phoneNumberCheck) {
      alert(`${t('register.errorFound')}`);
      return;
    }

    const data = {
      email: e.target.email.value,
      name: e.target.name.value,
      nickname: e.target.nickname.value,
      password: e.target.password.value,
      passwordCheck: e.target.passwordCheck.value,
      phoneNumber: e.target.phoneNumber.value,
      address1: e.target.address1.value,
      address2: e.target.address2.value,
    };

    dispatch(REGISTER_REQUEST(data));
  };

  return (
    <StyledForm onSubmit={handleSubmit}>
      <StyledGroup className="mb-3">
        <StyledLabel>Email address</StyledLabel>
        {emailValidationMsg ? (
          <StyledMsgLabel>{emailValidationMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
        <Form.Control
          type="email"
          name="email"
          placeholder="Enter email"
          required
          onBlur={handleValidationBlur}
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Name</StyledLabel>
        <Form.Control type="text" name="name" placeholder="name" required />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Nickname</StyledLabel>
        {nicknameValidationMsg ? (
          <StyledMsgLabel>{nicknameValidationMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
        <Form.Control
          type="text"
          name="nickname"
          autoComplete="username"
          placeholder="nickname"
          onBlur={handleValidationBlur}
          required
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Password</StyledLabel>
        {passwordRegexMsg ? (
          <StyledMsgLabel>{passwordRegexMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
        <Form.Control
          type="password"
          name="password"
          autoComplete="new-password"
          placeholder="Password"
          onChange={handlePasswordRegex}
          required
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Password Check</StyledLabel>
        {passwordMismatchMsg ? (
          <StyledMsgLabel>{passwordMismatchMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
        <Form.Control
          type="password"
          name="passwordCheck"
          autoComplete="new-password"
          placeholder="Password again"
          onChange={handlePasswordValidation}
          required
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Phone Number</StyledLabel>
        {phoneNumberValidationMsg ? (
          <StyledMsgLabel>{phoneNumberValidationMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
        <Form.Control
          type="text"
          name="phoneNumber"
          placeholder="phone number"
          value={phoneNumber}
          onChange={handlePhoneNumberFormat}
          onBlur={handleValidationBlur}
          required
        />
      </StyledGroup>
      <StyledGroup>
        <StyledLabel>Address 1</StyledLabel>
        <AddressPopup
          enrollCompany={enrollCompany}
          setEnrollCompany={setEnrollCompany}
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Address 2</StyledLabel>
        <Form.Control
          type="text"
          name="address2"
          placeholder="detailed address"
          required
        />
      </StyledGroup>
      <StyledValidationCheck>
        {registerError ? <StyledMsgLabel>{registerError}</StyledMsgLabel> : ''}
      </StyledValidationCheck>
      <StyledGroup className="justify-content-center">
        <StyledButton variant="primary" type="submit">
          Try Signin!
        </StyledButton>
        <StyledButton variant="secondary" type="submit">
          Find Password
        </StyledButton>
      </StyledGroup>
    </StyledForm>
  );
};

export default Register;
