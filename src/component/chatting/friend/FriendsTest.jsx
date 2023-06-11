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
import BirthdayFriend from './birthday/BirthdayFriend';
import UpdatedFriend from './UpdateFriend';
import FriendsItem from './FriendsItem';

const Friends = () => {
  console.log('tests');

  const handleUserClick = () => {
    console.log('handleUser');
  };

  const handleCommentClick = () => {
    console.log('handleCommentClick');
  };

  const handleBellClick = () => {
    console.log('handleBellClick');
  };

  const handleSettingClick = () => {
    console.log('handleSettingClick');
  };

  return (
    <StyledChattingScreen>
      <StyledChattingScreenLeft>
        <StyledChattingScreenIconsTop>
          <StyleFontAwesomeIcon
            icon={faUser}
            onClick={() => {
              handleUserClick();
            }}
          />
          <StyleFontAwesomeIcon
            icon={faCommentDots}
            onClick={() => {
              handleCommentClick();
            }}
          />
          <StyleFontAwesomeIcon
            icon={faBell}
            onClick={() => {
              handleBellClick();
            }}
          />
          <StyleFontAwesomeIcon
            icon={faEllipsis}
            onClick={() => {
              handleSettingClick();
            }}
          />
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
};
export default Friends;
