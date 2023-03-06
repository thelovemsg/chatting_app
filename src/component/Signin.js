import Button from "react-bootstrap/Button";
import { Form } from "react-bootstrap";
import { useState } from "react";
import {
  StyledButton,
  StyledForm,
  StyledGroup,
  StyledLabel,
} from "../styled-components/StyledForm";

const Signin = () => {
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

export default Signin;
