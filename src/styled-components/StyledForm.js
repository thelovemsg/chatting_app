import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button, Form } from "react-bootstrap";
import styled from "styled-components";
import TopicComponent from "../component/chatting/StyledTopicTag";

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
  text-align: center;
  color: red;
`;

export const StyledLangButton = styled(Button)`
  font-size: 17px;
  padding-left: 10px;
  padding-right: 10px;
`;

export const StyledErrorMsg = styled("div")`
  color: red;
  width: 50%;
  margin: auto;
  margin-top: 15px;
  border: solid;
  border-radius: 18px;
`;

/***** 프로필 *****/
export const StyledChattingScreen = styled("div")`
  margin: auto;
  width: 50vw;
  height: 83vh;
  padding: 20px;
  @media (max-width: 1350px) {
    width: 500px;
  }
`;

export const StyledChattingScreenLeft = styled("div")`
  width: 15%;
  height: 100%;
  background-color: green;
  border-top-left-radius: 5px 5px;
  border-bottom-left-radius: 5px 5px;
  float: left;
`;

export const StyledChattingScreenIcons = styled("div")`
  padding: 3px;
  margin: auto;
  text-align: center;
`;

export const StyleFontAwesomeIcon = styled(FontAwesomeIcon)`
  padding: 20px;
  margin-top: 20px;
  width: 30%;
  height: 30%;
`;

export const StyledChattingScreenRight = styled("div")`
  width: 85%;
  height: 100%;
  background-color: black;
  border-top-right-radius: 5px 5px;
  border-bottom-right-radius: 5px 5px;
  float: right;
`;

export const StyledTopicComponent = styled(TopicComponent)`
  padding: 10px;
`;
