import { Button, Form, Group } from "react-bootstrap";
import styled, { css } from "styled-components";

// transform: translateY(-100%);

// @media only screen and (max-width: 500px) {
//   transform: translateY(-60%);
// }

export const StyledForm = styled(Form)`
  margin: 5vw;
  @media (max-width: 768px) {
    margin-bottom: 15vh;
  }
`;

export const StyledLoginForm = styled(Form)`
  margin-top: 25vh;
  @media (max-width: 768px) {
    margin-top: 10vh;
    margin-bottom: 15vh;
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
    width: 300px;
  }
`;

export const StyledButton = styled(Button)`
  @media (min-width: 768px) {
    margin-top: 1vh;
  }
`;
