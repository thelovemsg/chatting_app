import { Form } from "react-bootstrap";
import {
  StyledButton,
  StyledForm,
  StyledGroup,
  StyledLabel,
  StyledMsgLabel,
  StyledValidationCheck,
} from "../styled-components/StyledForm";
import { useDispatch } from "react-redux";
import { REGISTER_REQUEST } from "../reducers/user";
import AddressPopup from "./address/AddressPopup";
import useRegister from "../hooks/useRegister";

const Register = () => {
  const {
    enroll_company,
    setEnroll_company,
    validationStatus,
    phoneNumber,
    emailValidationMsg,
    nicknameValidationMsg,
    phoneNumberValidationMsg,
    handlePhoneNumberFormat,
    handleValidationBlur,
  } = useRegister();

  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();

    const { email, nickname, phoneNumber } = validationStatus;
    if (!email || !nickname || !phoneNumber) {
      alert("check!");
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
          ""
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
          ""
        )}
        <Form.Control
          type="text"
          name="nickname"
          placeholder="nickname"
          onBlur={handleValidationBlur}
          required
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Password</StyledLabel>
        <Form.Control
          type="password"
          name="password"
          placeholder="Password"
          required
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Password Check</StyledLabel>
        <Form.Control
          type="password"
          name="passwordCheck"
          placeholder="Password again"
          required
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Phone Number</StyledLabel>
        {phoneNumberValidationMsg ? (
          <StyledMsgLabel>{phoneNumberValidationMsg}</StyledMsgLabel>
        ) : (
          ""
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
          enroll_company={enroll_company}
          setEnroll_company={setEnroll_company}
        ></AddressPopup>
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
        가입시 뭐 없으면 메시지 표시
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
