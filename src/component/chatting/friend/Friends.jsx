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
import { useState, useEffect } from 'react';
import { FLIP_USER_NOTI_STATUS_REQUEST } from 'reducers/user/userInfoSetting';
import { useDispatch, useSelector } from 'react-redux';
import MyProfile from './MyProfile';
import MutiProfile from './MutiProfile';
import BirthdayFriend from './birthday/BirthdayFriend';
import UpdatedFriend from './UpdateFriend';
import FriendsItem from './FriendsItem';

const Friends = () => {
  const dispatch = useDispatch();
  const { notiStatus } = useSelector((state) => state.user.info);
  const [showSearchBox, setShowSearchBox] = useState(false);
  const [searchInput, setSearchInput] = useState('');
  const [activeScreen, setActiveScreen] = useState('');

  useEffect(() => {
    console.log('searchInput :: ', searchInput);
  }, [searchInput]);

  const handleSearchBox = () => {
    setShowSearchBox(!showSearchBox);
  };

  const handleSearchInputChange = (e) => {
    setSearchInput(e.target.value);
  };

  const handleSearchBoxClose = () => {
    setShowSearchBox(false);
    setSearchInput('');
  };

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

  const screenMap = {
    profile: (
      <>
        <MyProfile
          showSearchBox={showSearchBox}
          handleSearchBox={handleSearchBox}
          handleSearchBoxClose={handleSearchBoxClose}
          searchInput={searchInput}
          handleSearchInputChange={handleSearchInputChange}
        />
        {searchInput === '' && (
          <>
            <MutiProfile />
            <BirthdayFriend />
            <UpdatedFriend />
            <FriendsItem />
          </>
        )}
        <FriendsItem searchInput={searchInput} />
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
