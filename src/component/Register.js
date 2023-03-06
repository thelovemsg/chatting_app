import Button from "react-bootstrap/Button";
import { Form } from "react-bootstrap";
import { useState } from "react";
import {
  StyledButton,
  StyledForm,
  StyledGroup,
  StyledLabel,
} from "../styled-components/StyledForm";
import PopupPostCode from "./address/PopupPostCode";
import PopupDom from "./address/PopupDom";

const Register = () => {
  // 팝업창 상태 관리
  const [isPopupOpen, setIsPopupOpen] = useState(false);

  // 팝업창 열기
  const openPostCode = () => {
    setIsPopupOpen(true);
  };

  // 팝업창 닫기
  const closePostCode = () => {
    setIsPopupOpen(false);
  };

  return (
    <StyledForm>
      <StyledGroup className="mb-3">
        <StyledLabel>Email address</StyledLabel>
        <Form.Control type="email" placeholder="Enter email" />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Password</StyledLabel>
        <Form.Control type="password" placeholder="Password" />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Password Check</StyledLabel>
        <Form.Control type="password" placeholder="Password again" />
      </StyledGroup>
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
