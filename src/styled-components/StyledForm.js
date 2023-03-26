import { Button, Form } from "react-bootstrap";
import styled from "styled-components";

export const StyledForm = styled(Form)`
  margin-top: 5vw;
  margin-bottom: 10vw;
  margin-left: 5vw;
  margin-right: 5vw;
  @media (max-width: 900px) {
    margin-top: 10vh;
    margin-bottom: 20vh;
  }
`;

export const StyledLoginForm = styled(Form)`
  margin-top: 15vh;
  @media (max-width: 768px) {
    margin-top: 10vh;
    margin-bottom: 20vh;
  }
`;

export const StyledGroup = styled("div")`
  padding: 10px;
  @media (min-width: 768px) {
    display: flex;
    align-items: center;
    margin: auto;
    width: 60vw;
  }
`;

export const StyledLabel = styled("label")`
  text-align: left;
  margin-bottom: 10px;
  @media (min-width: 768px) {
    width: 180px;
    margin: auto;
  }
`;

export const StyledMsgLabel = styled("label")`
  text-align: left;
  margin-bottom: 10px;
  margin-right: 10px !important;
  color: red;
  @media (min-width: 768px) {
    width: 180px;
    margin: auto;
  }

  @media (max-width: 768px) {
    margin-left: 10px;
  }
`;

export const StyledMessage = styled("label")`
  width: 200px;
  text-align: center;
  @media (max-width: 768px) {
    display: none;
  }
`;

export const StyledButton = styled(Button)`
  @media (min-width: 768px) {
    margin-top: 1vh;
  }
`;

export const StyledValidationCheck = styled("div")`
  margin: auto;
  color: red;
`;

export const StyledLangButton = styled(Button)`
  font-size: 17px;
  padding-left: 10px;
  padding-right: 10px;
`;
