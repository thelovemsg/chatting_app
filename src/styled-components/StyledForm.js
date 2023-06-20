import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Accordion, Button, Form } from 'react-bootstrap';
import styled from 'styled-components';

export const StyledForm = styled(Form)`
  width: 700px;
  margin: auto;
  margin-bottom: 100px;
  margin-top: 30px;
  padding-top: 20px;
  padding-bottom: 20px;
  background-color: #f0e8e6;
  border-radius: 30px;
  @media (min-width: 500px) {
    widthL 500px;
  }
`;

export const StyledContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
`;

export const StyledLogoutButton = styled(Button)`
  margin: 0px 0px;
  color: none;
  background-color: none !important;
  border: none;
  active-color: none;
  padding: 0px;
  font-size: 0.97em;
`;

export const StyledGroup = styled('div')`
  padding: 10px;
  margin: auto;
  @media (min-width: 350px) {
    width: 450px;
  }
`;

export const StyledInputForm = styled(Form.Control)`
  width: 450px;
`;

export const StyledLabel = styled('label')`
  text-align: left;
  margin-bottom: 10px;
`;

export const StyledMsgLabel = styled('label')`
  text-align: left;
  margin-top: 10px;
  color: red;
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
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const StyledChattingScreenIconsTop = styled('div')`
  padding: 10px;
  text-align: center;
`;

export const StyledChattingScreenIconsDown = styled('div')`
  padding: 10px;
  text-align: center;
`;

export const StyledChattingIntroLabel = styled('div')`
  font-size: 25px;
  margin-left: 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const StyledChattingItem = styled('div')`
  padding: 10px;
  margin-left: 10px;
  display: flex;
  align-items: center;
  &:hover {
    background-color: var(--main-profile-hover-color);
  }
`;

export const StyledChattingItemNoPadding = styled('div')`
  display: flex;
  align-items: center;
  width: 100%;
  &:hover {
    background-color: var(--main-friend-right-tag-hover-color);
  }
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
  overflow-y: auto;
  &::-webkit-scrollbar {
    width: 10px;
  }
  &::-webkit-scrollbar-thumb {
    background-color: #2f3542;
    border-radius: 10px;
    background-clip: padding-box;
    border: 1.5px solid transparent;
  }
  &::-webkit-scrollbar-track {
    background-color: grey;
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
  }
`;

export const StyledAccordionBody = styled(Accordion.Body)`
  padding: 5px 0px 5px 10px !important;
`;

export const StyledAccordionBodyMultiProfile = styled(Accordion.Body)`
  padding: 5px 0px 5px 15px !important;
  margin-left: 10px;
  display: flex;
  align-items: center;
`;

export const StyledFontAwesomeIconPlus = styled(FontAwesomeIcon)`
  width: 30px;
  height: 30px;
  border: 1px solid black;
  border-radius: 15px;
  padding: 5px;
  border-color: #dccecb;
  &:hover {
    /* Add hover styles here, e.g., change background color, border color, etc. */
  }
`;
