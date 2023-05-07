import { useDispatch, useSelector } from 'react-redux';
import { useTranslation, Trans } from 'react-i18next';
import {
  StyledButton,
  StyledForm,
  StyledGroup,
  StyledInputForm,
  StyledLabel,
  StyledMsgLabel,
  StyledValidationCheck,
} from 'styled-components/StyledForm';
import { REGISTER_REQUEST } from 'reducers/user';
import AddressPopup from 'component/address/AddressPopup';
import useRegister from 'hooks/useRegister';

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
        <StyledLabel>
          <Trans i18nKey="register.emailAddress" />
        </StyledLabel>
        <StyledInputForm
          type="email"
          name="email"
          placeholder="Enter email"
          required
          onBlur={handleValidationBlur}
        />
        {emailValidationMsg ? (
          <StyledMsgLabel>{emailValidationMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>
          <Trans i18nKey="register.name" />
        </StyledLabel>
        <StyledInputForm type="text" name="name" placeholder="name" required />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>
          <Trans i18nKey="register.nickname" />
        </StyledLabel>
        <StyledInputForm
          type="text"
          name="nickname"
          autoComplete="username"
          placeholder="nickname"
          onBlur={handleValidationBlur}
          required
        />
        {nicknameValidationMsg ? (
          <StyledMsgLabel>{nicknameValidationMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>
          <Trans i18nKey="register.password" />
        </StyledLabel>
        <StyledInputForm
          type="password"
          name="password"
          autoComplete="new-password"
          placeholder="Password"
          onChange={handlePasswordRegex}
          required
        />
        {passwordRegexMsg ? (
          <StyledMsgLabel>{passwordRegexMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>
          <Trans i18nKey="register.passwordCheck" />
        </StyledLabel>
        <StyledInputForm
          type="password"
          name="passwordCheck"
          autoComplete="new-password"
          placeholder="Password again"
          onChange={handlePasswordValidation}
          required
        />
        {passwordMismatchMsg ? (
          <StyledMsgLabel>{passwordMismatchMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>
          <Trans i18nKey="register.tellphoneNumber" />
        </StyledLabel>
        <StyledInputForm
          type="text"
          name="phoneNumber"
          placeholder="phone number"
          value={phoneNumber}
          onChange={handlePhoneNumberFormat}
          onBlur={handleValidationBlur}
          required
        />
        {phoneNumberValidationMsg ? (
          <StyledMsgLabel>{phoneNumberValidationMsg}</StyledMsgLabel>
        ) : (
          ''
        )}
      </StyledGroup>
      <StyledGroup>
        <StyledLabel>
          <Trans i18nKey="register.address1" />
        </StyledLabel>
        <AddressPopup
          enrollCompany={enrollCompany}
          setEnrollCompany={setEnrollCompany}
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>
          <Trans i18nKey="register.address2" />
        </StyledLabel>
        <StyledInputForm
          type="text"
          name="address2"
          placeholder="detailed address"
          required
        />
      </StyledGroup>
      <StyledValidationCheck>
        {registerError ? <StyledMsgLabel>{registerError}</StyledMsgLabel> : ''}
      </StyledValidationCheck>
      <StyledGroup className="text-align-center">
        <StyledButton variant="primary" type="submit" className="w-40">
          <Trans i18nKey="register.trySignIn" />
        </StyledButton>
        <StyledButton variant="secondary" type="submit" className="w-40">
          <Trans i18nKey="register.findPassword" />
        </StyledButton>
      </StyledGroup>
    </StyledForm>
  );
};

export default Register;
