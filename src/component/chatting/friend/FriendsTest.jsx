import {
  faUser,
  faCommentDots,
  faBell,
  faEllipsis,
} from '@fortawesome/free-solid-svg-icons';

import {
  StyleFontAwesomeIcon,
  StyledChattingScreen,
  StyledChattingScreenIconsDown,
  StyledChattingScreenIconsTop,
  StyledChattingScreenLeft,
  StyledChattingScreenRight,
} from 'styled-components/StyledForm';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { FLIP_USER_NOTI_STATUS_REQUEST } from 'reducers/user/userInfoSetting';
import MyProfile from './MyProfile';
import MutiProfile from './MutiProfile';
import BirthdayFriend from './birthday/BirthdayFriend';
import UpdatedFriend from './UpdateFriend';
import FriendsItem from './FriendsItem';

const Friends = () => {
  const { notiStatus } = useSelector((state) => state.user.info);
  const dispatch = useDispatch();

  useEffect(() => {}, [notiStatus]);

  const handleUserClick = () => {
    console.log('handleUser');
  };

  const handleCommentClick = () => {
    console.log('handleCommentClick');
  };

  const handleBellClick = () => {
    dispatch(FLIP_USER_NOTI_STATUS_REQUEST(!notiStatus));
  };

  const handleSettingClick = () => {
    console.log('handleBellClick');
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
            icon={faEllipsis}
            onClick={() => {
              handleSettingClick();
            }}
          />
        </StyledChattingScreenIconsTop>
        <StyledChattingScreenIconsDown>
          <div className={!notiStatus && 'no-noti-status'} />
          <StyleFontAwesomeIcon
            icon={faBell}
            onClick={() => {
              handleBellClick();
            }}
          />
        </StyledChattingScreenIconsDown>
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
