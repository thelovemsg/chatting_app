import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Button, Form } from 'react-bootstrap';
import styled from 'styled-components';
import TopicComponent from '../component/chatting/TopicComponent';

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

export const StyledGroup = styled('div')`
  padding: 10px;
  @media (min-width: 768px) {
    display: flex;
    align-items: center;
    margin: auto;
    width: 60vw;
  }
`;

export const StyledLabel = styled('label')`
  text-align: left;
  margin-bottom: 10px;
  @media (min-width: 768px) {
    width: 180px;
    margin: auto;
  }
`;

export const StyledMsgLabel = styled('label')`
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

export const StyledMessage = styled('label')`
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

export const StyledValidationCheck = styled('div')`
  margin: auto;
  text-align: center;
  color: red;
`;

export const StyledLangButton = styled(Button)`
  font-size: 17px;
  padding-left: 10px;
  padding-right: 10px;
`;

export const StyledErrorMsg = styled('div')`
  color: red;
  width: 50%;
  margin: auto;
  margin-top: 15px;
  border: solid;
  border-radius: 18px;
`;

/* Friends.js styles */
export const StyledChattingScreen = styled('div')`
  display: flex;
  justify-content: center;
  align-items: center;
  max-width: 100%;
  height: 80vh;
  margin: auto;
  @media (max-width: 1350px) {
    width: 500px;
  }

  @media (max-height: 1350px) {
    height: 83vh;
    margin-bottom: 50px;
  }
`;

export const StyledChattingScreenLeft = styled('div')`
  flex-shrink: 0;
  width: 80px;
  height: 90%;
  background-color: #d2d2d2;
  border-top-left-radius: 5px 5px;
  border-bottom-left-radius: 5px 5px;
  border: 1px solid #dcdcdc;
  box-shadow: 5px 5px 5px 2px gray;
`;
export const StyledChattingScreenIconsTop = styled('div')`
  padding: 10px;
  margin: auto;
  text-align: center;
`;

export const StyleFontAwesomeIcon = styled(FontAwesomeIcon)`
  padding: 20px;
  width: 20px;
  height: 20px;
  &:hover {
    background: #cacfd2;
    cursor: pointer;
    border-radius: 15px;
  }
`;

export const StyledChattingScreenRight = styled('div')`
  flex-grow: 1;
  min-width: 0;
  max-width: 800px; // Adjust this value to your desired maximum width
  height: 90%;
  padding: 20px;
  background-color: white;
  border-top-right-radius: 5px 5px;
  border-bottom-right-radius: 5px 5px;
  box-shadow: 5px 5px 5px 2px gray;
  border: 1px solid #dcdcdc;
`;

export const StyledTopicComponent = styled(TopicComponent)`
  padding: 10px;
`;
