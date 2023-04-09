import {
  faUser,
  faCommentDots,
  faBell,
  faEllipsis,
} from "@fortawesome/free-solid-svg-icons";

import {
  StyleFontAwesomeIcon,
  StyledChattingScreen,
  StyledChattingScreenIconsTop,
  StyledChattingScreenLeft,
  StyledChattingScreenRight,
} from "../../styled-components/StyledForm";
import MyProfile from "./MyProfile";
import MutiProfile from "./MutiProfile";
import BirthdayFriend from "./BirthdayFriend";
import UpdateFriend from "./UpdateFriend";
import FriendsItem from "./FriendItem";
import Accordion from 'react-bootstrap/Accordion';

const Friends = () => {
  return (
    <StyledChattingScreen>
      <StyledChattingScreenLeft>
        <StyledChattingScreenIconsTop>
          <StyleFontAwesomeIcon icon={faUser} />
          <StyleFontAwesomeIcon icon={faCommentDots} />
          <StyleFontAwesomeIcon icon={faBell} />
          <StyleFontAwesomeIcon icon={faEllipsis} />
        </StyledChattingScreenIconsTop>
      </StyledChattingScreenLeft>
      <StyledChattingScreenRight>
        <MyProfile></MyProfile>
        <Accordion>
          <Accordion.Item eventKey="0">
            <Accordion.Header>Accordion Item #1</Accordion.Header>
            <Accordion.Body>
              testf
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
        <MutiProfile></MutiProfile>
        <BirthdayFriend></BirthdayFriend>
        <UpdateFriend></UpdateFriend>
        <FriendsItem></FriendsItem>
      </StyledChattingScreenRight>
    </StyledChattingScreen>
  );
};

export default Friends;
