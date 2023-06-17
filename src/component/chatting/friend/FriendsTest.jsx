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
import { useEffect, useState } from 'react';
import { FLIP_USER_NOTI_STATUS_REQUEST } from 'reducers/user/userInfoSetting';
import MyProfile from './MyProfile';
import MutiProfile from './MutiProfile';
import BirthdayFriend from './birthday/BirthdayFriend';
import UpdatedFriend from './UpdateFriend';
import FriendsItem from './FriendsItem';

const Friends = () => {
  const [activeScreen, setActiveScreen] = useState('profile');

  const { notiStatus } = useSelector((state) => state.user.info);
  const dispatch = useDispatch();

  useEffect(() => {}, [notiStatus]);

  const handleProfileIconClick = () => {
    setActiveScreen('profile');
  };

  const handleChattingIconClick = () => {
    setActiveScreen('chatting');
  };

  const handleSettingClick = () => {
    setActiveScreen('setting');
  };

  const handleBellClick = () => {
    dispatch(FLIP_USER_NOTI_STATUS_REQUEST(!notiStatus));
  };

  // object mapping screens
  const screenMap = {
    profile: (
      <>
        <MyProfile />
        <MutiProfile />
        <BirthdayFriend />
        <UpdatedFriend />
        <FriendsItem />
      </>
    ),
    chatting: <div>ㅎㅎ... 채팅방 클릭시</div>,
    setting: <div>setting screen ggg..</div>,
  };

  return (
    <StyledChattingScreen>
      <StyledChattingScreenLeft>
        <StyledChattingScreenIconsTop>
          <StyleFontAwesomeIcon
            icon={faUser}
            onClick={() => {
              handleProfileIconClick();
            }}
          />
          <StyleFontAwesomeIcon
            icon={faCommentDots}
            onClick={() => {
              handleChattingIconClick();
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
        {screenMap[activeScreen] || null}
      </StyledChattingScreenRight>
    </StyledChattingScreen>
  );
};
export default Friends;
