import {
  ButtonToolbar,
  OverlayTrigger,
  Spinner,
  Tooltip,
} from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import {
  StyledButton,
  StyledContainer,
  StyledForm,
  StyledGroup,
  StyledInputForm,
  StyledLabel,
  StyledValidationCheck,
} from 'styled-components/StyledForm';
import { LOG_IN_REQUEST } from 'reducers/login';
import { Trans } from 'react-i18next';
import { useEffect } from 'react';

const Signin = () => {
  const dispatch = useDispatch();
  const { loginHandling, loginError } = useSelector((state) => state.user);

  useEffect(() => {
    console.log('loginHandling :: ', loginHandling);
  }, [loginHandling]);

  useEffect(() => {
    if (loginError?.message) {
      alert(loginError?.message);
    }
  }, [loginError]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = {
      email: e.target.email.value,
      password: e.target.password.value,
    };
    dispatch(LOG_IN_REQUEST(data));
  };

  const tooltip = (
    <Tooltip id="tooltip">
      Did you forget your password? <br />
      <strong>Just click here!</strong>
    </Tooltip>
  );

  return (
    <StyledContainer>
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
            onSubmit={handleSubmit}
          />
        </StyledGroup>
        <StyledGroup className="mb-3">
          <StyledLabel>
            <Trans i18nKey="register.password" />
          </StyledLabel>
          <StyledInputForm
            type="password"
            name="password"
            placeholder="Password"
            required
          />
        </StyledGroup>
        <StyledValidationCheck>로그인시 뭐 없으면</StyledValidationCheck>
        <StyledGroup className="text-align-center">
          <ButtonToolbar>
            {loginHandling ? (
              <Spinner animation="border" variant="info" className="mt-2" />
            ) : (
              ''
            )}
          </ButtonToolbar>
          <StyledButton variant="primary" type="submit">
            <Trans i18nKey="register.tryLogin" />
          </StyledButton>
          <OverlayTrigger placement="top" overlay={tooltip}>
            <StyledButton variant="secondary" type="submit">
              <Trans i18nKey="register.findPassword" />
            </StyledButton>
          </OverlayTrigger>
          <div>id : test1@naver.com</div>
          <div>password : password1234</div>
        </StyledGroup>
      </StyledForm>
    </StyledContainer>
  );
};

export default Signin;
