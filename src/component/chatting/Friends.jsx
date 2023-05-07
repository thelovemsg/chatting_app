import {
  faUser,
  faCommentDots,
  faBell,
  faEllipsis,
} from '@fortawesome/free-solid-svg-icons';

import {
  StyleFontAwesomeIcon,
  StyledChattingScreen,
  StyledChattingScreenIconsTop,
  StyledChattingScreenLeft,
  StyledChattingScreenRight,
} from 'styled-components/StyledForm';
import MyProfile from './MyProfile';
import MutiProfile from './MutiProfile';
import BirthdayFriend from './BirthdayFriend';
import UpdatedFriend from './UpdateFriend';
import FriendsItem from './FriendsItem';

const Friends = () => (
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
      <MyProfile />
      <MutiProfile />
      <BirthdayFriend />
      <UpdatedFriend />
      <FriendsItem />
    </StyledChattingScreenRight>
  </StyledChattingScreen>
);
export default Friends;
