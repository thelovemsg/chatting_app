import Button from "react-bootstrap/Button";
import { ButtonToolbar, Form, OverlayTrigger, Tooltip } from "react-bootstrap";
import { useState } from "react";
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

  const tooltip = (
    <Tooltip id="tooltip">
      Did you forget your password? <br />
      <strong>Just click here!</strong>
    </Tooltip>
  );

  const onFinish = (values) => {
    dispatch(LOG_IN_REQUEST(values));
  };

  return (
    <StyledLoginForm className="mb-8" onFinish={onFinish}>
      <StyledGroup className="mb-3">
        <StyledLabel>Email address</StyledLabel>
        <Form.Control type="email" placeholder="Enter email" />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Password</StyledLabel>
        <Form.Control type="password" placeholder="Password" />
      </StyledGroup>
      <StyledGroup className="justify-content-center">
        <ButtonToolbar>
          <StyledButton variant="primary" type="submit">
            Try Signin!
          </StyledButton>
          <OverlayTrigger placement="top" overlay={tooltip}>
            <StyledButton
              variant="secondary"
              type="submit"
              loading={loginHandling}
            >
              Find Password
            </StyledButton>
          </OverlayTrigger>
        </ButtonToolbar>
      </StyledGroup>
    </StyledLoginForm>
  );
};

export default Signin;
