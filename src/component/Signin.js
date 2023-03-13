import Button from "react-bootstrap/Button";
import {
  ButtonToolbar,
  Form,
  OverlayTrigger,
  Spinner,
  Tooltip,
} from "react-bootstrap";
import { useEffect, useState } from "react";
import {
  StyledButton,
  StyledGroup,
  StyledLabel,
  StyledLoginForm,
} from "../styled-components/StyledForm";
import { useDispatch, useSelector } from "react-redux";
import { LOG_IN_REQUEST } from "../reducers/user";

const Signin = () => {
  const dispatch = useDispatch();
  const { loginHandling } = useSelector((state) => state.user);
  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    dispatch(LOG_IN_REQUEST(formData));
  };

  const tooltip = (
    <Tooltip id="tooltip">
      Did you forget your password? <br />
      <strong>Just click here!</strong>
    </Tooltip>
  );

  return (
    <StyledLoginForm className="mb-8" onSubmit={handleSubmit}>
      <StyledGroup className="mb-3">
        <StyledLabel>Email address</StyledLabel>
        <Form.Control
          type="email"
          name="email"
          placeholder="Enter email"
          required
          onSubmit={handleSubmit}
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
      <StyledGroup className="justify-content-center">
        <ButtonToolbar>
          {loginHandling ? (
            <Spinner animation="border" variant="info" className="mt-2" />
          ) : (
            ""
          )}
          <StyledButton variant="primary" type="submit">
            Try Signin!
          </StyledButton>
          <OverlayTrigger placement="top" overlay={tooltip}>
            <StyledButton variant="secondary" type="submit">
              Find Password
            </StyledButton>
          </OverlayTrigger>
        </ButtonToolbar>
      </StyledGroup>
    </StyledLoginForm>
  );
};

export default Signin;
