import Button from "react-bootstrap/Button";
import { Form, ModalTitle } from "react-bootstrap";
import { useState } from "react";
import {
  StyledButton,
  StyledForm,
  StyledGroup,
  StyledLabel,
} from "../styled-components/StyledForm";
import Post from "./address/Post";

const Register = () => {
  const [enroll_company, setEnroll_company] = useState({
    address: "",
  });

  const [popup, setPopup] = useState(false);

  const handleComplete = (data) => {
    setPopup(!popup);
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
      <StyledGroup>
        <StyledLabel>Address 1</StyledLabel>
        <Form.Control
          className="mb-3 w-40"
          type="text"
          placeholder="Addess"
          value={enroll_company.address}
          required={true}
          readOnly
          onClick={handleComplete}
        />
        {popup && (
          <Post company={enroll_company} setcompany={setEnroll_company}></Post>
        )}
      </StyledGroup>
      <StyledGroup>
        <StyledLabel>Address 2</StyledLabel>
        <Form.Control type="text" placeholder="detailed address" />
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
