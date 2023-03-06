import { Button, Form, Group } from "react-bootstrap";
import styled, { css } from "styled-components";

// transform: translateY(-100%);

// @media only screen and (max-width: 500px) {
//   transform: translateY(-60%);
// }

export const StyledForm = styled(Form)`
  margin: 10vw;
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
